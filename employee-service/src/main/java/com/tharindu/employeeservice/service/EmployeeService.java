package com.tharindu.employeeservice.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tharindu.employeeservice.modal.Employee;
import com.tharindu.employeeservice.repository.EmployeeRepository;

public interface EmployeeService {
	
	Employee save(Employee employee);
	List<Employee> fetchAllEmployee();
	Employee fetchEmployee(Employee employee);
		
}
