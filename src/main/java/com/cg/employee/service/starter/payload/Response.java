package com.cg.employee.service.starter.payload;

import com.cg.employee.service.starter.constant.EmployeeConstants;

import java.util.Map;

public class Response {

    private EmployeeConstants status;
    private String statusDiscription;
    private Map<String, Object> map;

    public Response() {
    }

    public Response(EmployeeConstants status, String statusDiscription, Map<String, Object> map) {
        this.status = status;
        this.statusDiscription = statusDiscription;
        this.map = map;
    }

    public Response(EmployeeConstants status, String statusDiscription) {
        this.status = status;
        this.statusDiscription = statusDiscription;
    }

    public EmployeeConstants getStatus() {
        return status;
    }

    public void setStatus(EmployeeConstants status) {
        this.status = status;
    }

    public String getStatusDiscription() {
        return statusDiscription;
    }

    public void setStatusDiscription(String statusDiscription) {
        this.statusDiscription = statusDiscription;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", statusDiscription='" + statusDiscription + '\'' +
                ", map=" + map +
                '}';
    }
}
