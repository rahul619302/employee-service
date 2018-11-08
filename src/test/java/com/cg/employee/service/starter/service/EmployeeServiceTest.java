package com.cg.employee.service.starter.service;

import com.cg.employee.service.starter.dao.IEmployeeDao;
import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;
import com.cg.employee.service.starter.pojo.Address;
import com.cg.employee.service.starter.pojo.Employee;
import com.cg.employee.service.starter.util.IEmployeeUtil;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private IEmployeeUtil employeeUtil;
    @Mock
    private IEmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void saveEmployee() throws Exception {
        Response expectedResponse = new Response();
        Map<String, Object> map = new HashMap<>();
        map.put("address_list", new ArrayList<Map<String, String>>());
        Request request = new Request("", "office", "", map);
        BDDMockito.given(employeeUtil.getEmployee(Mockito.any(Request.class))).willReturn(new Employee());
        BDDMockito.given(employeeUtil.getAddress(Mockito.any(Employee.class), Mockito.any(List.class))).willReturn(new ArrayList<Address>());
        BDDMockito.given(employeeDao.save(Mockito.any(Employee.class))).willReturn(new Employee());
        BDDMockito.given(employeeUtil.getSuccessResponse(Mockito.any(), Mockito.any(Employee.class), Mockito.anyString())).willReturn(expectedResponse);
        Response acctualResponse = employeeService.saveEmployee(request);
        Assert.assertThat(expectedResponse, Is.is(acctualResponse));
    }

    @Test
    void getEmployee() throws Exception {
        Response expectedResponse = new Response();
        Integer id = null;
        if (id == null) {
            BDDMockito.given(employeeDao.findAll()).willReturn(new ArrayList<Employee>());
            BDDMockito.given(employeeUtil.getSuccessResponse(Mockito.any(), Mockito.any(Employee.class), Mockito.anyString())).willReturn(expectedResponse);
        } else {
            Optional<Employee> employeeOptional = Optional.of(new Employee());
            BDDMockito.given(employeeDao.findById(Mockito.anyInt())).willReturn(employeeOptional);
            if (!employeeOptional.isPresent())
                BDDMockito.given(employeeUtil.invalidEmployeeIdResponse()).willReturn(expectedResponse);
            else
                BDDMockito.given(employeeUtil.getSuccessResponse(Mockito.any(), Mockito.any(Employee.class), Mockito.anyString())).willReturn(expectedResponse);
        }
        Response acctualResponse = employeeService.getEmployee(id);
        Assert.assertThat(expectedResponse, Is.is(acctualResponse));
    }
}