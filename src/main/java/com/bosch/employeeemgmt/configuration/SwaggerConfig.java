package com.bosch.employeeemgmt.configuration;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/**
	 * 
	 * @return Docket
	 * @see http://localhost:8888/v2/api-docs and http://localhost:8888/swagger-ui.html
	 */
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
}
