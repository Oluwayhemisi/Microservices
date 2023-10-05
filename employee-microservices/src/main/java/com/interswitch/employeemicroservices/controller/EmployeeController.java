package com.interswitch.employeemicroservices.controller;

import com.interswitch.employeemicroservices.dto.ApiResponse;
import com.interswitch.employeemicroservices.dto.EmployeeDto;
import com.interswitch.employeemicroservices.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = " Employee Service EmployeeController",
        description = "Employee Controller exposes Rst APIs for Employee Service"
)
@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Operation(
            summary = "Save Employee Rest APIs",
            description = "Save Employee Rest APIs is used to save Employee object in a database"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"

    )
    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.createEmployee(employeeDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(employeeDto1)
                .message("Success")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @Operation(
            summary = "Update Employee Rest APIs",
            description = "Update Employee Rest APIs is used to Update Employee object in a database"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"

    )
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.updateEmployee(id, employeeDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(employeeDto1)
                .message("Success")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @Operation(
            summary = "Get All Employee Rest APIs",
            description = "Get All Employee Rest APIs is used to get all Employee object in a database"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"

    )
    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }



    @Operation(
            summary = "Get Employee by ID Rest APIs",
            description = "Get Employee Rest APIs is used to get Employee object in a database by ID"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"

    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
}