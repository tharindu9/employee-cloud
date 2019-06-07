package com.tharindu.employeeservice.controller;

import java.util.List;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tharindu.employeeservice.modal.Address;
import com.tharindu.employeeservice.modal.Employee;
import com.tharindu.employeeservice.modal.Project;
import com.tharindu.employeeservice.service.EmployeeService;
import com.tharindu.employeeservice.service.EmployeeServiceImp;

@RestController
@RequestMapping(value="/emscloud")
public class EmployeeController {
	
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "/employee" , method = RequestMethod.POST )
	public Employee save(@RequestBody Employee emp ) {
		
		return employeeService.save(emp);
		
	}
	
	@RequestMapping(value = "/employee" , method = RequestMethod.GET )
	public List<Employee> fetchAllEmployee() {
		
		return employeeService.fetchAllEmployee();
		
	}
	
	@RequestMapping(value = "/employee/{id}" , method = RequestMethod.GET )
	public ResponseEntity<Employee> fetchEmployee(@PathVariable Integer id) {
		Employee emp = new Employee();
		emp.setId(id);
		Employee emp1 = employeeService.fetchEmployee(emp);
		
		if(emp1==null) {
			return ResponseEntity.badRequest().build();
		}
		else {
			return new ResponseEntity<Employee>(emp1,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/employee/{id}/projects" , method = RequestMethod.GET )
	public ResponseEntity<List<Project>> fetchEmployeeProjects(@PathVariable Integer id) {
		Employee emp = new Employee();
		emp.setId(id);
		List<Project> projects = employeeService.fetchEmployee(emp).getProjects();
		return new ResponseEntity<List<Project>>(projects,HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/employee/{id}/projects/{pid}")
	public String fetchEmployeeProject(@PathVariable Integer id,
			@PathVariable Integer pid) {
		Employee emp = new Employee();
		emp.setId(id);
		List<Project> projects = employeeService.fetchEmployee(emp).getProjects();
	    String name = projects.get(pid).getName();
		return name.toString();
	}
	
}
