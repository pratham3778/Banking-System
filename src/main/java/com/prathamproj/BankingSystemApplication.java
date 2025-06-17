package com.prathamproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info (
				title = "The Banking System App",
				description = "Bankend REST APIs for Pratham Bank",
				version = "v1.0",
				contact = @Contact(
						name = "Pratham Tanpure",
						email = "pratham3778@gmail.com",
						url = "https://github.com/pratham3778/Banking-System"
						),
				license = @License (
						name = "Pratham Tanpure",
						url = "https://github.com/pratham3778/Banking-System"
						)
				),
		externalDocs = @ExternalDocumentation (
				description = "The Banking System App Documentation",
				url = "https://github.com/pratham3778/Banking-System"
				)
		)
public class BankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

}
