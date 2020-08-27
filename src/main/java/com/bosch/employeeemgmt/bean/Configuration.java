package com.bosch.employeeemgmt.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "mode", havingValue = "prod")
public class Configuration {
	private String config1;
	private String config2;
	
	

}
