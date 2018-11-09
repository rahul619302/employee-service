package com.cg.employee.service.starter.service;

import com.cg.employee.service.starter.constant.EmployeeConstants;
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
    private Response expectedResponse;

    @BeforeEach
    public void setUp() throws Exception {
        expectedResponse = new Response(EmployeeConstants.S200, EmployeeConstants.S200.getMessage());
    }


    @Test
    void saveEmployee() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("address_list", new ArrayList<Map<String, String>>());
        Request request = new Request("Rahul Singh", "office", "1", map);
        Optional<Employee> employeeOptional = Optional.empty();
        BDDMockito.given(employeeDao.findById(Mockito.anyInt())).willReturn(employeeOptional);
        BDDMockito.given(employeeUtil.getEmployee(Mockito.any(Request.class))).willReturn(new Employee());
        BDDMockito.given(employeeUtil.getAddress(Mockito.any(Employee.class), Mockito.any(List.class))).willReturn(new ArrayList<Address>());
        BDDMockito.given(employeeDao.save(Mockito.any(Employee.class))).willReturn(new Employee());
        BDDMockito.given(employeeUtil.getSuccessResponse(ArgumentMatchers.isNull(), ArgumentMatchers.isNull(), ArgumentMatchers.isNull())).willReturn(expectedResponse);
        Assert.assertThat(expectedResponse, Is.is(employeeService.saveEmployee(request)));
    }

    @Test
    void getEmployee() throws Exception {
        Integer id = 1;
        if (id == null) {
            BDDMockito.given(employeeDao.findAll()).willReturn(new ArrayList<Employee>());
            BDDMockito.given(employeeUtil.getSuccessResponse(Mockito.any(List.class), ArgumentMatchers.isNull(), Mockito.any(String.class))).willReturn(expectedResponse);
        } else {
            Optional<Employee> employeeOptional = Optional.of(new Employee());
            BDDMockito.given(employeeDao.findById(Mockito.anyInt())).willReturn(employeeOptional);
            if (!employeeOptional.isPresent())
                BDDMockito.given(employeeUtil.invalidEmployeeIdResponse()).willReturn(expectedResponse);
            else
                BDDMockito.given(employeeUtil.getSuccessResponse(ArgumentMatchers.isNull(), Mockito.any(Employee.class), Mockito.any(String.class))).willReturn(expectedResponse);
        }
        Assert.assertThat(expectedResponse, Is.is(employeeService.getEmployee(id)));
    }
}