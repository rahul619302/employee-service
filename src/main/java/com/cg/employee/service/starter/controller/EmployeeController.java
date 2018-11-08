package com.cg.employee.service.starter.controller;

import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;
import com.cg.employee.service.starter.service.IEmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    Logger logger = LogManager.getLogger(EmployeeController.class);

    @PostMapping("/save")
    public Response saveEmployee(@RequestBody Request request) throws Exception {
        Response response = employeeService.saveEmployee(request);
        logger.info(response);
        return response;
    }

    @GetMapping({"/get", "/get/{id}"})
    public Response getEmployee(@PathVariable Optional<Integer> id) throws Exception {
        Response response = null;
        if (id.isPresent())
            response = employeeService.getEmployee(id.get());
        else
            response = employeeService.getEmployee(null);
        logger.info(response);
        return response;
    }
}
