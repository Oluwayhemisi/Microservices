package com.interswitch.employeemicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "com.interswitch.employeemicroservices.model")
public class EmployeeMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMicroservicesApplication.class, args);
	}

}
