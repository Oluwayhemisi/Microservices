package com.interswitch.employeemicroservices.client;


import com.interswitch.employeemicroservices.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {

    @GetMapping("api/v1/department/code/{code}")
    DepartmentDto getDepartmentByDepartmentCode(@PathVariable("code") String code);

}
