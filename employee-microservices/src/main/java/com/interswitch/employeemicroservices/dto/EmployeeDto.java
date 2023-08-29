package com.interswitch.employeemicroservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private  Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String departmentCode;

}
