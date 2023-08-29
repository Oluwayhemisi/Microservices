package com.interswitch.employeemicroservices.service;

import com.interswitch.employeemicroservices.dto.EmployeeApiResponse;
import com.interswitch.employeemicroservices.dto.EmployeeDto;
import com.interswitch.employeemicroservices.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Long id,EmployeeDto employeeDto);

    EmployeeApiResponse getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployee();
}
