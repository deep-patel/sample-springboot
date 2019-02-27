package app.springboot.core.controller;

import app.springboot.core.dto.ResponseObject;
import app.springboot.core.service.ITestService;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by: deep.patel on 22/02/19
 */

@Controller
public class TestEndpoint {

    private final ITestService testService;

    @Autowired
    public TestEndpoint(final ITestService testService, final MetricRegistry metricRegistry){
        this.testService = testService;
    }

    @Metered(name = "total_request")
    @Timed(name = "latency")
    @GetMapping("/test")
    public ResponseEntity<ResponseObject> test(@RequestParam("id") int id){
        ResponseObject responseObject = testService.process(id);
        return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
    }

    @GetMapping("/notify")
    public ResponseEntity<ResponseObject> test(@RequestParam("message") String message){
        boolean success = testService.notify(message);
        return ResponseEntity.status(success? HttpStatus.CREATED.value(): HttpStatus.BAD_REQUEST.value()).build();
    }
}
