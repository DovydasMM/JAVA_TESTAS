package com.example.final_test.Project;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

	@Bean
	CommandLineRunner projectCommandLineRunner(ProjectRepository projectRepository) {
		return args -> {
			Project projOne = new Project("Test1", LocalDate.now());
			Project projTwo = new Project("Test2", LocalDate.now());
			Project projThree = new Project("Test3", LocalDate.now());
			projectRepository.saveAll(List.of(projOne, projTwo, projThree));
		};

	}
}
