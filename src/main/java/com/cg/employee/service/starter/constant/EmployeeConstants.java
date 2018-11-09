package com.cg.employee.service.starter.constant;

public enum EmployeeConstants {

    S200("Success"), S400("Excetion Occured"), S401("Invalid Employee Id"), S403("Duplicate Employee Id");

    private String message;

    EmployeeConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
