package com.interswitch.departmentmicroservices.service;

import com.interswitch.departmentmicroservices.dto.DepartmentDto;
import com.interswitch.departmentmicroservices.model.Department;

import java.util.List;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto updateDepartment(Long id,DepartmentDto departmentDto);

    DepartmentDto findDeptByDepartmentCode(String departmentCode);
    DepartmentDto findDeptById(Long departmentId);

    List<Department> findAllDepartment();


}
