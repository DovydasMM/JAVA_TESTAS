package com.example.final_test.Project;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.final_test.Employee.Employee;
import com.example.final_test.Employee.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

	private ProjectRepository projectRepository;
	private EmployeeRepository employeeRepository;

	@Autowired
	public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {

		this.projectRepository = projectRepository;
		this.employeeRepository = employeeRepository;
	}

	public List<Project> getProjects() {
		return this.projectRepository.findAll();
	}

	public void addProject(Project project) {
		if (project.getProject_start_date().isAfter(project.getProject_due_date())) {
			throw new IllegalStateException("Project can't end before its start date");
		}

		if (Period.between(project.getProject_start_date(), project.getProject_due_date()).getYears() >= 1) {
			throw new IllegalStateException("Project duration is longer than one year");
		}

		projectRepository.save(project);

	}

	@Transactional
	public void addEmployee(Employee employee, Project project) {
		project.addEmployee(employee);
	}

	@Transactional
	public void updateProject(Integer project_id, String project_name, LocalDate dueDate) {
		Project project = projectRepository.findById(project_id)
				.orElseThrow(() -> new IllegalStateException("There is no such Project"));

		if (project.getProject_start_date().isAfter(dueDate)) {
			throw new IllegalStateException("Project can't end before its start date");
		}

		if (Period.between(project.getProject_start_date(), dueDate).getYears() >= 1) {
			throw new IllegalStateException("Project duration is longer than one year");
		}

		if (project_name != null)
			project.setProject_name(project_name);

	}

	public String getCount(String project_name, Integer project_id) {

		// Finding count by project_id count
		if (project_id != null) {
			Project project = projectRepository.findById(project_id)
					.orElseThrow(() -> new IllegalStateException("There is no such project"));
			return "Size is: " + project.getEmployees().size();

		}
		return "No such project";

	}

}
