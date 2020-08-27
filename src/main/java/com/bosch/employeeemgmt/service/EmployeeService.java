package com.bosch.employeeemgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosch.employeeemgmt.bean.Employee;
import com.bosch.employeeemgmt.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		return repository.findById(id).get();

	}

	public Employee save(Employee emp) {
		return repository.save(emp);

	}

	public int update(Employee emp) {
		repository.save(emp);
		return 1;
	}

	public void delete(Long id) {
		repository.deleteById(id);

	}
	
	public String getRole(String firstName) {
		return repository.getRole(firstName);
	}
}
