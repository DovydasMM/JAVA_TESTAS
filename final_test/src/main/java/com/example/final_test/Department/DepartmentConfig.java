package com.example.final_test.Department;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentConfig {

	@Bean
	CommandLineRunner departmentCommandLineRunner(DepartmentRepository departmentRepository) {
		return args -> {
			Department deptOne = new Department("Sales");
			Department deptTwo = new Department("Executive");
			Department deptThree = new Department("CustSup");
			departmentRepository.saveAll(List.of(deptOne, deptTwo, deptThree));
		};

	}

}
