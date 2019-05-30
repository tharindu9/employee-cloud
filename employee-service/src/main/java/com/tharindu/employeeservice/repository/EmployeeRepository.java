package com.tharindu.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tharindu.employeeservice.modal.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
