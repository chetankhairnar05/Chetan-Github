package com.natche.ToDoComplex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class ToDoComplexApplication {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Task & Category REST API")
						.version("1.0")
						.description(
								"A Spring Boot REST API for managing tasks and categories with full CRUD support. \r\n"
										+ //
										"Features include task status tracking, bulk creation, DTO mapping, validation, and centralized error handling.\r\n"
										+ //
										"")
						.contact(new Contact()
								.name("Chetan Khairnar")
								.email("chetankhairnar965@gmail.com")));
	}

	public static void main(String[] args) {
		SpringApplication.run(ToDoComplexApplication.class, args);
	}

}
