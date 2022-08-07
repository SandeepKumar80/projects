package com.org.service.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIDocumentationConfig {
	@Value("${document.contact-email}")
	private String contactEmail;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("userWebService REST Services").contact(new Contact().name("IT").email(contactEmail)));

	}

}