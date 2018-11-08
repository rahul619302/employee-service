package com.cg.employee.service.starter.controller;

import com.cg.employee.service.starter.constant.EmployeeConstants;
import com.cg.employee.service.starter.payload.Request;
import com.cg.employee.service.starter.payload.Response;
import com.cg.employee.service.starter.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private IEmployeeService employeeService;
    @Mock
    private Logger logger;
    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;
    private Response expectedResponse;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        expectedResponse = new Response(EmployeeConstants.S200, EmployeeConstants.S200.getMessage());
    }

    @Test
    void saveEmployee() throws Exception {
        BDDMockito.given(employeeService.saveEmployee(Mockito.any(Request.class))).willReturn(expectedResponse);
        callPostRequest("/employee/save");
    }

    @Test
    void getEmployee() throws Exception {
        BDDMockito.given(employeeService.getEmployee(Mockito.any(Integer.class))).willReturn(expectedResponse);
        callGetRequest("/employee/get/1");
    }

    private void callPostRequest(String url) throws Exception {
        String acctualResponse = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new Request()))
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