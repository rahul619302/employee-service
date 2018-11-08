package com.cg.employee.service.starter;

import com.cg.employee.service.starter.constant.EmployeeConstants;
import com.cg.employee.service.starter.controller.EmployeeController;
import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;
import com.cg.employee.service.starter.pojo.Address;
import com.cg.employee.service.starter.pojo.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceApplicationTests {

	@Autowired
	private EmployeeController employeeController;
	@InjectMocks
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;
	private Response expectedResponse;
	private Map<String, Object> requestMap;
	Map<String, Object> responseMap;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		expectedResponse = new Response(EmployeeConstants.S200, EmployeeConstants.S200.getMessage());
		requestMap=new HashMap<>();

		Address address=new Address("A904","Sector-15, Belapur CBD","Navi Mumbai","Maharastra","India","office",null);
		address.setId(1);
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		Employee employee=new Employee(1,"Rahul Singh","Developer", addresses);
		address.setEmployee(employee);
		responseMap=new HashMap<>();
		responseMap.put("employee", employee);
		expectedResponse.setMap(responseMap);
	}

	@Test
	void saveEmployee() throws Exception {
		requestMap.put("designation", "Developer");
		List<Map<String, String>> addressList=new ArrayList<>();
		Map<String, String> addressMap=new HashMap<>();
		addressMap.put("flatNo", "A904");
		addressMap.put("area", "Sector-15, Belapur CBD");
		addressMap.put("city", "Navi Mumbai");
		addressMap.put("state", "Maharastra");
		addressMap.put("country", "India");
		addressMap.put("address_type", "office");
		addressList.add(addressMap);
		requestMap.put("address_list", addressList);
		Request request=new Request("Rahul Singh","employee","1", requestMap);

		callPostRequest("/employee/save",request);
	}

	@Test
	void getEmployee() throws Exception {
		saveEmployee();
		callGetRequest("/employee/get/1");
	}

	private void callPostRequest(String url, Request request) throws Exception {
		String acctualResponse = mockMvc.perform(MockMvcRequestBuilders
				.post(url)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString();
		Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(acctualResponse));
	}

	private void callGetRequest(String url) throws Exception {
		String acctualResponse = mockMvc.perform(MockMvcRequestBuilders
				.get(url)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString();
		Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(acctualResponse));
	}

}
