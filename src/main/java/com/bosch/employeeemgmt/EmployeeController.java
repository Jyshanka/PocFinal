package com.bosch.employeeemgmt;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bosch.employeeemgmt.Exception.EmployeeNotFoundException;
import com.bosch.employeeemgmt.Exception.UnauthorizedException;
import com.bosch.employeeemgmt.bean.Employee;
import com.bosch.employeeemgmt.configuration.JwtUtil;
import com.bosch.employeeemgmt.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/employee")
	public List<Employee> getEmployees(@RequestHeader("authorization") String jwtToken,@RequestParam String username) {
		Boolean isAuth=util.validateToken(jwtToken, username);
		String role=util.extractroles(jwtToken);
		if(isAuth && role.equalsIgnoreCase("admin")) {
			return service.getEmployees();
		}else {
			throw new UnauthorizedException("unauthorized Exception");
		}
	}
	
	@GetMapping(path = "/employee/{id}")
	public Employee getEmployeeById(@RequestHeader("authorization") String jwtToken,@RequestParam String username, @RequestParam Long id) {
		Boolean isAuth=util.validateToken(jwtToken, username);
		if(isAuth) {
			return service.getEmployeeById(id);
		}else {
			throw new UnauthorizedException("unauthorized Exception");
		}
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployees(@RequestBody Employee emp,@RequestHeader("authorization") String jwtToken,@RequestParam String username) {
		Boolean isAuth=util.validateToken(jwtToken, username);
		if(isAuth) {
			service.save(emp);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(service.getEmployeeById(emp.getId())).toUri();
			return ResponseEntity.created(location).build();
		}else {
			throw new UnauthorizedException("unauthorized Exception");
		}
		
	}

	@PutMapping("/employee")
	public ResponseEntity<Object> updateEmployees(@RequestBody Employee emp,@RequestHeader("authorization") String jwtToken,@RequestParam String username) {
		Boolean isAuth=util.validateToken(jwtToken, username);
		if(isAuth) {
			service.update(emp);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(service.getEmployeeById(emp.getId())).toUri();
			return ResponseEntity.created(location).build();
		}else {
			throw new UnauthorizedException("unauthorized Exception");
		}
		
		
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@RequestParam Long id,@RequestHeader("authorization") String jwtToken,@RequestParam String username) {
		Employee emp=service.getEmployeeById(id);
		if(null!=emp.getId()) {
			service.delete(id);
		}else {
			throw new EmployeeNotFoundException("employee not found with id "+id);
		}
		
		
	}
	
	@PostMapping("/getToken")
	public String createToken(@RequestBody Employee emp) {
		return util.generateToken(emp);
	}
	
	//"good.morning.message",null, LocaleContextHolder.getLocale()
}
