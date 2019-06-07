package com.tharindu.employeeservice.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tharindu.employeeservice.modal.Allocation;
import com.tharindu.employeeservice.modal.Employee;
import com.tharindu.employeeservice.modal.Telephone;
import com.tharindu.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeService;
	 @Override
	public Employee save(Employee employee) {
		
	 for (Telephone telephone : employee.getTelephone()) {
		 telephone.setEmployee(employee);
		
	}
		return employeeService.save(employee);
	}
	 
	public List<Employee> fetchAllEmployee(){
		return employeeService.findAll();
	}
	
	public Employee fetchEmployee(Employee employee){
		Optional<Employee>  optional = employeeService.findById(employee.getId());
		if(optional.isPresent()) {
//			RestTemplate restTemplate = new RestTemplate();
//			HttpHeaders httpHeaders = new HttpHeaders();
//		
//			ResponseEntity<List<Allocation>> responseEntity;
//			HttpEntity<String> entity = new HttpEntity<String>("",httpHeaders);
//			
//			
// 			
			
			// fetch project alllocation
			RestTemplate restTemplate=new RestTemplate();
					HttpHeaders httpHeaders=new HttpHeaders();

					//extract token from context
					OAuth2AuthenticationDetails oAuth2AuthenticationDetails =(OAuth2AuthenticationDetails)
							SecurityContextHolder.getContext().getAuthentication().getDetails();

					System.out.println(">>>>"+oAuth2AuthenticationDetails.getTokenValue());
					httpHeaders.add("Authorization","bearer".concat(oAuth2AuthenticationDetails.getTokenValue()));

					//
					ResponseEntity<Allocation[]> responseEntity;
					HttpEntity<String> httpEntity=new HttpEntity<>("",httpHeaders);
					 responseEntity=restTemplate.exchange("http://localhost:9090/emscloud/allocation/employee/".
							 concat(employee.getId().toString()),HttpMethod.GET,httpEntity, Allocation[].class);


							 Employee employee1= optional.get();
							 employee1.setAllocation(responseEntity.getBody());
					return employee1;
			//return optional.get();
		}
		else {
			return null;
		}
	}


	

}
