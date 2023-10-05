package com.interswitch.departmentmicroservices.controller;

import com.interswitch.departmentmicroservices.dto.ApiResponse;
import com.interswitch.departmentmicroservices.dto.DepartmentDto;
import com.interswitch.departmentmicroservices.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = " Department Service DepartmentController",
        description = "Department Controller exposes Rst APIs for Department Service"
)
@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;



    @Operation(
            summary = "Save Department Rest APIs",
            description = "Save Department Rest APIs is used to save Department object in a database"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"

    )
    @PostMapping("/create")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = departmentService.createDepartment(departmentDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Success")
                .data(departmentDto1)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


    @Operation(
            summary = "Update Department Rest APIs",
            description = "Update Department Rest APIs is used to Update Department object in a database"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"

    )
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = departmentService.updateDepartment(id,departmentDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Success")
                .data(departmentDto1)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


    @Operation(
            summary = "Get All Department Rest APIs",
            description = "Get All Department Rest APIs is used to get all Department object in a database"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"

    )
    @GetMapping("/departments")
    public ResponseEntity<?> getAllDepartment(){
        return new ResponseEntity<>(departmentService.findAllDepartment(),HttpStatus.OK);
    }


    @Operation(
            summary = "Get Department by ID Rest APIs",
            description = "Get Department Rest APIs is used to get Department object in a database by ID"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"

    )
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getDepartmentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(departmentService.findDeptById(id),HttpStatus.OK);

    }


    @Operation(
            summary = "Get Department by CODE Rest APIs",
            description = "Get Department Rest APIs is used to get Department object in a database by CODE"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"

    )
    @GetMapping("/code/{code}")
    public ResponseEntity<Object> getDepartmentByDepartmentCode(@PathVariable("code") String code){
        return new ResponseEntity<>(departmentService.findDeptByDepartmentCode(code),HttpStatus.OK);

    }

}
