package com.example.final_test.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.final_test.Project.Project;
import com.example.final_test.Project.ProjectRepository;
import com.example.final_test.Project.ProjectService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	private ProjectRepository projectRepository;
	private ProjectService projectService;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository,
			ProjectService projectService) {
		this.employeeRepository = employeeRepository;
		this.projectRepository = projectRepository;
		this.projectService = projectService;
	}

	public List<Employee> getEmployees() {
		return this.employeeRepository.findAll();
	}

	public void addNewEmployee(Employee employee) {

		if (employee.getSalary() < 22000) {
			throw new IllegalStateException("Salary can't be lower than 22 200");
		}

		if (employee.getName().length() > 2) {
			String arr[] = employee.getName().split(" ", 2);
			String firstName = arr[0];
			char lastName = arr[1].charAt(0);
			String newName = firstName + " " + lastName + ".";

			employee.setName(newName);
		}

		employeeRepository.save(employee);

	}

	@Transactional
	public void addProject(Integer employee_id, Integer project_id) {

		Employee employee = employeeRepository.findById(employee_id)
				.orElseThrow(() -> new IllegalStateException("There is no such employee"));

		Project project = projectRepository.findById(project_id)
				.orElseThrow(() -> new IllegalStateException("There is no such project"));

		employee.addProject(project);
		projectService.addEmployee(employee, project);

	}

	public void removeProject(Integer employee_id, Integer projId) {
		Employee employee = employeeRepository.findById(employee_id)
				.orElseThrow(() -> new IllegalStateException("There is no such employee"));

		Project project = projectRepository.findById(projId)
				.orElseThrow(() -> new IllegalStateException("There is no such project"));

		employee.removeProject(project);
		employeeRepository.save(employee);

	}

}
