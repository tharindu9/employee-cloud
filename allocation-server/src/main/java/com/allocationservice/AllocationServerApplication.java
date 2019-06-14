package com.allocationservice;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient

public class AllocationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllocationServerApplication.class, args);
	}
  
}
