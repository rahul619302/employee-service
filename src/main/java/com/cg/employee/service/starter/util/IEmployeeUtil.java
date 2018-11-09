package com.cg.employee.service.starter.util;

import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;
import com.cg.employee.service.starter.pojo.Address;
import com.cg.employee.service.starter.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeUtil {

    List<Address> getAddress(Employee employee, List<Map<String, String>> addressList) throws Exception;

    Employee getEmployee(Request request) throws Exception;

    Response getSuccessResponse(List<Employee> employees, Employee employee, String name) throws Exception;

    Response invalidEmployeeIdResponse() throws Exception;

    Response duplicateEmployeeResponse();
}
