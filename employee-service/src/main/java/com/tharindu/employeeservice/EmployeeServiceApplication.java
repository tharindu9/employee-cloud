package com.tharindu.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
@EnableCircuitBreaker
public class EmployeeServiceApplication   extends ResourceServerConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
		
	}

	@Override
	public void  configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/actuator/*")
				.permitAll().anyRequest().authenticated();
	}
}
