package com.interswitch.employeemicroservices.dto;


import com.interswitch.employeemicroservices.model.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeApiResponse {
    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;

}
