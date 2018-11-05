package com.cg.employee.service.starter.service;

import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;

public interface IEmployeeService {
    Response saveEmployee(Request request) throws Exception;

    Response getEmployee(Integer id) throws Exception;
}
