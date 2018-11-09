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

import java.io.IOException;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceApplicationTests {

	@Autowired
	private EmployeeController employeeController;
	@InjectMocks
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;
	private Request request;
	private Response expectedResponse;
	private Map<String, Object> requestMap;
	Map<String, Object> responseMap;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		requestMap = new LinkedHashMap<>();
		request = getRequest();
		expectedResponse = new Response(EmployeeConstants.S200, EmployeeConstants.S200.getMessage());
	}

	@Test
	void saveEmployee() throws Exception {
		Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(callPostRequest("/employee/save", request)));
	}

	@Test
	void getEmployee() throws Exception {
		callPostRequest("/employee/save", request);
		buildExpectedResponse();
		String acctualResponse=callGetRequest("/employee/get/1");
		Response actualRes=modifyResponse(acctualResponse);
		Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(objectMapper.writeValueAsString(actualRes)));
	}

	private void buildExpectedResponse() {
		Address address=new Address("A904","Sector-15, Belapur CBD","Navi Mumbai","Maharastra","India","office",null);
		address.setId(0);
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		Employee employee=new Employee(1,"Rahul Singh", "Developer", addresses);
		address.setEmployee(employee);
		Map<String, Object> responseMap=new HashMap<>();
		responseMap.put("employee", employee);
		expectedResponse.setMap(responseMap);
	}

	private Response modifyResponse(String acctualResponse) throws IOException {
		Response actualRes = objectMapper.readValue(acctualResponse, Response.class);
		Map<String, Object> map = actualRes.getMap();
		Map<String, Object> employeeMap = (Map<String, Object>) map.get("employee");
		List<Map<String, Object>> addressMaps = (List<Map<String, Object>>) employeeMap.get("addresses");
		List<Map<String, Object>> addressMapss = modifyAddressId(addressMaps);
		employeeMap.put("addresses", addressMapss);
		map.put("employee", employeeMap);
		return actualRes;
	}

	private List<Map<String, Object>> modifyAddressId(List<Map<String, Object>> addressMaps) {
		List<Map<String, Object>> addressMapss = new LinkedList<>();
		for (Map<String, Object> address : addressMaps) {
			address.put("id", 0);
			addressMapss.add(address);
		}
		return addressMapss;
	}

	private Request getRequest() {
		requestMap.put("designation", "Developer");
		List<Map<String, String>> addressList=new ArrayList<>();
		Map<String, String> addressMap = getAddressMap();
		addressList.add(addressMap);
		requestMap.put("address_list", addressList);
		return new Request("Rahul Singh","employee","1", requestMap);
	}

	private Map<String, String> getAddressMap() {
		Map<String, String> addressMap=new HashMap<>();
		addressMap.put("flatNo", "A904");
		addressMap.put("area", "Sector-15, Belapur CBD");
		addressMap.put("city", "Navi Mumbai");
		addressMap.put("state", "Maharastra");
		addressMap.put("country", "India");
		addressMap.put("addressType", "office");
		return addressMap;
	}

	private String callPostRequest(String url, Request request) throws Exception {
		return  mockMvc.perform(MockMvcRequestBuilders
				.post(url)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString();
	}

	private String callGetRequest(String url) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
				.get(url)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString();
	}

}
