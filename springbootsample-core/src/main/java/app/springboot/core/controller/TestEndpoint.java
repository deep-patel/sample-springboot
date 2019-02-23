package app.springboot.core.controller;

import app.springboot.core.dto.ResponseObject;
import app.springboot.core.service.ITestService;

import io.prometheus.client.spring.web.PrometheusTimeMethod;
import org.springframework.beans.factory.annotation.Autowired;
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
    public TestEndpoint(final ITestService testService){
        this.testService = testService;
    }

    @PrometheusTimeMethod(name = "test_endpoint_latency", help = "Some helpful info here")
    @GetMapping("/test")
    public ResponseEntity<ResponseObject> test(@RequestParam("id") int id){
        ResponseObject responseObject = testService.process(id);
        return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
    }
}
