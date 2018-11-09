package com.cg.employee.service.starter.service;

import com.cg.employee.service.starter.dao.IEmployeeDao;
import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;
import com.cg.employee.service.starter.pojo.Address;
import com.cg.employee.service.starter.pojo.Employee;
import com.cg.employee.service.starter.util.IEmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeUtil employeeUtil;
    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = false)
    public Response saveEmployee(Request request) throws Exception {
        Map<String, Object> map = request.getMap();
        int id = Integer.valueOf(request.getParam3());
        Optional<Employee> optionalEmployee=employeeDao.findById(id);
        if(!optionalEmployee.isPresent()) {
            Employee employee = employeeUtil.getEmployee(request);
            List<Map<String, String>> addressList = (List<Map<String, String>>) map.get("address_list");
            List<Address> addresses = null;
            if (addressList != null) {
                addresses = employeeUtil.getAddress(employee, addressList);
                employee.setAddresses(addresses);
            }
            employee = employeeDao.save(employee);
            return employeeUtil.getSuccessResponse(null, null, null);
        }else{
            return employeeUtil.duplicateEmployeeResponse();
        }
    }

    @Override
    public Response getEmployee(Integer id) throws Exception {
        if (id == null) {
            List<Employee> employees = employeeDao.findAll();
            return employeeUtil.getSuccessResponse(employees, null, "employees");
        } else {
            Optional<Employee> employeeOptional = employeeDao.findById(id);
            if (!employeeOptional.isPresent())
                return employeeUtil.invalidEmployeeIdResponse();
            Employee employee = employeeOptional.get();
            return employeeUtil.getSuccessResponse(null, employee, "employee");
        }
    }
}
