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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (status != response.status) return false;
        if (statusDiscription != null ? !statusDiscription.equals(response.statusDiscription) : response.statusDiscription != null)
            return false;
        return map != null ? map.equals(response.map) : response.map == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (statusDiscription != null ? statusDiscription.hashCode() : 0);
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
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
