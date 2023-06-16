package com.example.final_test.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@PostMapping(path = "/add")
	public void addNewEmployee(@RequestBody Employee employee) {
		employeeService.addNewEmployee(employee);
	}

	@PutMapping(path = "{employee_id}/addProject")
	public void addProject(@PathVariable("employee_id") Integer employee_id,
			@RequestParam(required = true) Integer projId) {
		employeeService.addProject(employee_id, projId);
	}

	@PutMapping(path = "{employee_id}/removeProject")
	public void removeProject(@PathVariable("employee_id") Integer employee_id,
			@RequestParam(required = true) Integer projId) {
		employeeService.removeProject(employee_id, projId);
	}

}
