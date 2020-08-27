package com.bosch.employeeemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bosch.employeeemgmt.bean.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT role from Employee e where e.firstName=:firstName")
	public String getRole(String firstName);
	
}
