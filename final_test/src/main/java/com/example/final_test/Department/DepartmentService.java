package com.example.final_test.Department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.final_test.Employee.Employee;
import com.example.final_test.Employee.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartmentService {

	private DepartmentRepository departmentRepository;
	private EmployeeRepository employeeRepository;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {

		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
	}

	public List<Department> getDepartments() {

		return this.departmentRepository.findAll();
	}

	public String deptSalAverage(Integer dept_id) {
		List<Employee> employees = employeeRepository.findAll();
		Department department = departmentRepository.findById(dept_id)
				.orElseThrow(() -> new IllegalStateException("There is no such department"));
		Integer sumSalary = 0;
		Integer employeeCount = 0;
		Float average = null;

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getDepartment_id() == dept_id) {
				sumSalary += employees.get(i).getSalary();
				employeeCount++;
			}
		}

		if (employeeCount != 0) {
			average = (float) sumSalary / employeeCount;
		}
		return "Department [department_id=" + dept_id + ", average=" + average + "]";
	}

	public void addEmployee(Integer department_id, Integer employee_id) {

		Department department = departmentRepository.findById(department_id)
				.orElseThrow(() -> new IllegalStateException("There is no such Department"));

		Employee employee = employeeRepository.findById(employee_id)
				.orElseThrow(() -> new IllegalStateException("There is no such project"));

		department.addEmployee(employee);
		departmentRepository.save(department);

	}

	public void addDepartment(Department department) {
		if (department.getManager_id() != null) {
			employeeRepository.findById(department.getManager_id())
					.orElseThrow(() -> new IllegalStateException("Department can't be controlled by unknown employee"));
		}
		departmentRepository.save(department);

	}

	@Transactional
	public void changeManager(Integer department_id, Integer manager_id, String department_name) {
		Employee manager;
		Department department = departmentRepository.findById(department_id)
				.orElseThrow(() -> new IllegalStateException("This department doesn't exist."));

		if (manager_id != null) {
			manager = employeeRepository.findById(manager_id)
					.orElseThrow(() -> new IllegalStateException("Department can't be controlled by unknown employee"));
		}

		if (department_name != null & department_name.length() > 0) {
			department.setDepartment_name(department_name);
		}

		department.setManager_id(manager_id);

	}

}
