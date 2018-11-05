package com.cg.employee.service.starter.util;

import com.cg.employee.service.starter.constant.EmployeeConstants;
import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;
import com.cg.employee.service.starter.pojo.Address;
import com.cg.employee.service.starter.pojo.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeUtil implements IEmployeeUtil {

    @Override
    public List<Address> getAddress(Employee employee, String addressType, List<Map<String, String>> addressList) throws Exception {
        List<Address> addresses = new ArrayList<>();
        for (Map<String, String> addressMap : addressList) {
            Address address = new Address();
            address.setFlatNo(addressMap.get("flatNo") != null ? addressMap.get("flatNo") : " ");
            address.setArea(addressMap.get("area") != null ? addressMap.get("area") : " ");
            address.setCity(addressMap.get("city") != null ? addressMap.get("city") : " ");
            address.setState(addressMap.get("state") != null ? addressMap.get("state") : " ");
            address.setCountry(addressMap.get("country") != null ? addressMap.get("country") : " ");
            address.setAddressType(addressType);
            address.setEmployee(employee);
            addresses.add(address);
        }
        return addresses;
    }

    @Override
    public Employee getEmployee(Request request, Map<String, Object> map) throws Exception {
        Employee employee = new Employee();
        employee.setId(Integer.valueOf(request.getParam3()));
        employee.setName(request.getParam1());
        employee.setDesignation((map.get("designation") != null ? map.get("designation") : " ") + "");
        return employee;
    }

    @Override
    public Response getSuccessResponse(List<Employee> employees, Employee employee, String name) throws Exception {
        Response response = new Response(EmployeeConstants.S200, EmployeeConstants.S200.getMessage());
        if (employee != null || employees !=null) {
            Map<String, Object> map = new LinkedHashMap<>(2);
            if(employee != null)
                map.put(name, employee);
            else
                map.put(name, employees);
            response.setMap(map);
        }
        return response;
    }

    @Override
    public Response invalidEmployeeIdResponse() throws Exception {
        return new Response(EmployeeConstants.S401, EmployeeConstants.S401.getMessage());
    }
}
