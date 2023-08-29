package com.interswitch.employeemicroservices.exceptions;

public class EmployeeException extends RuntimeException{
    int statusCode;
    String message;

    public EmployeeException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;

    }
}
