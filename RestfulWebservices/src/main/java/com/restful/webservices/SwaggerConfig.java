 package com.restful.webservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// http://localhost:8080/v2/api-docs -> Gives all the services documentation in json format
	// http://localhost:8080/swagger-ui.html -> visual representation of restful services
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
	
}
