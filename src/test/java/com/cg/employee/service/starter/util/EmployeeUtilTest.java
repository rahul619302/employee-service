package com.cg.employee.service.starter.util;

import com.cg.employee.service.starter.constant.EmployeeConstants;
import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;
import com.cg.employee.service.starter.pojo.Address;
import com.cg.employee.service.starter.pojo.Employee;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeUtilTest {

    @InjectMocks
    private EmployeeUtil employeeUtil;

    @Test
    void getAddress() throws Exception {
        Address address = new Address("A904", "Sector-15, Belapur CBD", "Navi Mumbai", "Maharastra", "India", "employee", new Employee());
        List<Address> expectedAddresses = Arrays.asList(address);

        List<Map<String, String>> addressList = new ArrayList<>();
        Map<String, String> addressMap = new HashMap<>();
        addressMap.put("flatNo", "A904");
        addressMap.put("area", "Sector-15, Belapur CBD");
        addressMap.put("city", "Navi Mumbai");
        addressMap.put("state", "Maharastra");
        addressMap.put("country", "India");
        addressList.add(addressMap);
        List<Address> acctualAddresses = employeeUtil.getAddress(new Employee(), addressList);

        Assert.assertThat(expectedAddresses, Is.is(acctualAddresses));
    }

    @Test
    void getEmployee() throws Exception {
        Employee expectedEmployee = new Employee(1, "Rahul Singh", "developer", null);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("designation", "developer");
        Request request = new Request("Rahul Singh", "employee", "1", map);
        Employee acctualEmployee = employeeUtil.getEmployee(request);

        Assert.assertThat(expectedEmployee, Is.is(acctualEmployee));
    }

    @Test
    void getSuccessResponse() throws Exception {
        Employee employee = new Employee();
        Response expectedResponse = new Response(EmployeeConstants.S200, EmployeeConstants.S200.getMessage());
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("employee", employee);
        expectedResponse.setMap(map);

        Response acctualResponse = employeeUtil.getSuccessResponse(null, employee, "employee");

        Assert.assertThat(expectedResponse, Is.is(acctualResponse));
    }

    @Test
    void invalidEmployeeIdResponse() throws Exception {
        Response expectedResponse = new Response(EmployeeConstants.S401, EmployeeConstants.S401.getMessage());

        Response acctualResponse = employeeUtil.invalidEmployeeIdResponse();

        Assert.assertThat(expectedResponse, Is.is(acctualResponse));
    }
}