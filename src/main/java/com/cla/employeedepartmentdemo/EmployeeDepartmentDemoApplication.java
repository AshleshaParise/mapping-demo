package com.cla.employeedepartmentdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching  
@SpringBootApplication
@ComponentScan(basePackages = "com.cla.*")
@EntityScan("com.cla.*")
@EnableJpaRepositories("com.cla.employeedepartmentdemo.Repository")
public class EmployeeDepartmentDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDepartmentDemoApplication.class, args);
	}

}

