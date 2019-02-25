package app.springboot.core.service;

import app.springboot.core.dto.ResponseObject;

/**
 * Created by: deep.patel on 22/02/19
 */
public interface ITestService {
    ResponseObject process(int id);
    boolean notify(String message);
}
