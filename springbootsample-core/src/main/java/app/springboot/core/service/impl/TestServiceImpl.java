package app.springboot.core.service.impl;

import app.springboot.core.dto.ResponseObject;
import app.springboot.core.inmemrepo.Repo;
import app.springboot.core.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by: deep.patel on 22/02/19
 */

@Slf4j
@Service
public class TestServiceImpl implements ITestService {

    private final Repo repo;

    @Autowired
    public TestServiceImpl(final Repo repo){
        this.repo = repo;
    }

    @Cacheable("testcache")
    @Override
    public ResponseObject process(int id) {
        log.info("You are here means you didn't find value in Cache: " + id);
        String value = repo.getValue(id);
        if(value == null)
            return new ResponseObject(404, "Not found");
        else
            return new ResponseObject(200, value);
    }
}
