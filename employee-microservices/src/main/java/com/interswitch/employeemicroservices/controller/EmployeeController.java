package com.interswitch.employeemicroservices.controller;

import com.interswitch.employeemicroservices.dto.ApiResponse;
import com.interswitch.employeemicroservices.dto.EmployeeDto;
import com.interswitch.employeemicroservices.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.createEmployee(employeeDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(employeeDto1)
                .message("Success")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.updateEmployee(id, employeeDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(employeeDto1)
                .message("Success")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
}