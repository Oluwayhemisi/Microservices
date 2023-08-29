package com.interswitch.departmentmicroservices.exceptions;

public class DepartmentException extends RuntimeException {
    int statusCode;
    String message;

    public DepartmentException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;

    }
}
