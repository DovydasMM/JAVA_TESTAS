package com.example.final_test.Employee;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
		return args -> {
			Employee empOne = new Employee("Dovydas Maziliauskas", 10, "Engineer", 25000);
			Employee empTwo = new Employee("Dovydas", 10, "Engineer", 25000);
			Employee empThree = new Employee("Dovydas", 10, "Engineer", 25000);
			employeeRepository.saveAll(List.of(empOne, empTwo, empThree));
		};
	}

}
