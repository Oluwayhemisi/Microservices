package com.interswitch.departmentmicroservices.controller;

import com.interswitch.departmentmicroservices.dto.ApiResponse;
import com.interswitch.departmentmicroservices.dto.DepartmentDto;
import com.interswitch.departmentmicroservices.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;



    @PostMapping("/create")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = departmentService.createDepartment(departmentDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Success")
                .data(departmentDto1)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = departmentService.updateDepartment(id,departmentDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Success")
                .data(departmentDto1)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/departments")
    public ResponseEntity<?> getAllDepartment(){
        return new ResponseEntity<>(departmentService.findAllDepartment(),HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getDepartmentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(departmentService.findDeptById(id),HttpStatus.OK);

    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Object> getDepartmentByDepartmentCode(@PathVariable("code") String code){
        return new ResponseEntity<>(departmentService.findDeptByDepartmentCode(code),HttpStatus.OK);

    }

}
