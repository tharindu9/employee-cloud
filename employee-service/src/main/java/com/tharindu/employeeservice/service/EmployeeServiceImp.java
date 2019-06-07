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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tharindu.employeeservice.modal.Allocation;
import com.tharindu.employeeservice.modal.Employee;
import com.tharindu.employeeservice.modal.Telephone;
import com.tharindu.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	 @Override
	public Employee save(Employee employee) {
		
	 for (Telephone telephone : employee.getTelephone()) {
		 telephone.setEmployee(employee);
		
	}
		return employeeRepository.save(employee);
	}
	 
	public List<Employee> fetchAllEmployee(){
		return employeeRepository.findAll();
	}
	
	public Employee fetchEmployee(Employee employee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
		if (optionalEmployee.isPresent()) {
			// fetch project alllocation
	//		RestTemplate restTemplate=new RestTemplate();


					 Employee employee1= optionalEmployee.get();
					 Allocation[] allocation = fetchEmployeeAllocation(optionalEmployee.get());
					 employee1.setAllocation(allocation);
			return employee1;
		}else {
			return null;
		}
	}

	@HystrixCommand(fallbackMethod = "fetchEmployeeAllocationFallBack")
	public  Allocation[] fetchEmployeeAllocation(Employee employee){

		HttpHeaders httpHeaders=new HttpHeaders();

		//extract token from context
		OAuth2AuthenticationDetails oAuth2AuthenticationDetails =(OAuth2AuthenticationDetails)
				SecurityContextHolder.getContext().getAuthentication().getDetails();


		httpHeaders.add("Authorization","bearer".concat(oAuth2AuthenticationDetails.getTokenValue()));


		ResponseEntity<Allocation[]> responseEntity;
		HttpEntity<String> httpEntity=new HttpEntity<>("",httpHeaders);
		responseEntity=restTemplate.exchange("http://allocation-service/emscloud/allocations/".
				concat(employee.getId().toString()),HttpMethod.GET,httpEntity, Allocation[].class);

         return responseEntity.getBody();
	}

	public  Allocation[] fetchEmployeeAllocationFallBack(Employee employee) {
		return new Allocation[1];
	}

	

}
