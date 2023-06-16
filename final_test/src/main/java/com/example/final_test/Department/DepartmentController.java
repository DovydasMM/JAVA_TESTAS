package com.example.final_test.Department;

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
@RequestMapping(path = "api/v1/departments")
public class DepartmentController {
	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	public List<Department> getDepartments() {
		return this.departmentService.getDepartments();
	}

	@PostMapping(path = "/add")
	public void addDepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);

	}

	@GetMapping(path = "{id}/avg")
	public String deptSalAverage(@PathVariable("id") Integer dept_id) {
		return departmentService.deptSalAverage(dept_id);
	}

	@PutMapping(path = "{department_id}/addEmp")
	public void addProject(@PathVariable("department_id") Integer department_id,
			@RequestParam(required = true) Integer employee_id) {
		departmentService.addEmployee(department_id, employee_id);
	}

	@PutMapping(path = "{department_id}/update")
	public void updateDepartment(@PathVariable("department_id") Integer department_id,
			@RequestParam(required = false) String department_name,
			@RequestParam(required = false) Integer manager_id) {
		departmentService.changeManager(department_id, manager_id, department_name);
	}

}
