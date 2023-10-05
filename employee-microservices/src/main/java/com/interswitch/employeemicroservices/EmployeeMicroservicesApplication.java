package com.interswitch.employeemicroservices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@OpenAPIDefinition(
		info = @Info(
				title = "Employee Service Rest APIs",
				description = "Employee Service Rest APIs documentation"

		)
)
@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "com.interswitch.employeemicroservices.model")
public class EmployeeMicroservicesApplication {


	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	public static void main(String[] args) {
		SpringApplication.run(EmployeeMicroservicesApplication.class, args);
	}

}
