package com.example.final_test.Department;

import java.util.ArrayList;
import java.util.List;

import com.example.final_test.Employee.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Department {

	@Id
	@SequenceGenerator(name = "dept_sequence", sequenceName = "dept_sequence", initialValue = 10, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_sequence")
	private Integer department_id;
	private String department_name;
	private Integer manager_id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> list_of_employees = new ArrayList<>();

	public Department(String department_name, Integer manager_id) {
		this.department_name = department_name;
		this.manager_id = manager_id;
	}

	public Department() {
	};

	public Department(String department_name) {
		this.department_name = department_name;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Integer getManager_id() {
		return manager_id;
	}

	public List<Employee> getEmployees() {
		return list_of_employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.list_of_employees = employees;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}

	public void addEmployee(Employee employee) {
		this.list_of_employees.add(employee);
	}

	@Override
	public String toString() {
		return "Department [department_id=" + department_id + ", department_name=" + department_name + ", manager_id="
				+ manager_id + ", list_of_employees=" + list_of_employees + "]";
	}

}
