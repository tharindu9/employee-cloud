package com.tharindu.dashbord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashBordApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashBordApplication.class, args);
	}

}
