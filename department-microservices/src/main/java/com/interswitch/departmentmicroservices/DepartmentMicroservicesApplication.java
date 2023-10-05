package com.interswitch.departmentmicroservices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
		info = @Info(
				title = "Department Service Rest APIs",
				description = "Department Service Rest APIs documentation"

		)
)
@SpringBootApplication
public class DepartmentMicroservicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(DepartmentMicroservicesApplication.class, args);
	}

}
