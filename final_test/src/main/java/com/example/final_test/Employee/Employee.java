package com.example.final_test.Employee;

import java.util.ArrayList;
import java.util.List;

import com.example.final_test.Department.Department;
import com.example.final_test.Project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Employee {

	@Id
	@SequenceGenerator(name = "emp_sequence", sequenceName = "emp_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_sequence")
	private Integer employee_id;
	private String name;
	private Integer department_id;

	@ManyToOne
	@JoinColumn(name = "department")
	private Department department;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	@JsonIgnore
	private List<Project> projects;

	private String role;
	private Integer salary;

	public Employee(String name, Integer department_id, String role, Integer salary) {
		this();
		this.name = name;
		this.department_id = department_id;
		this.role = role;
		this.salary = salary;
	}

	public Employee() {
		this.projects = new ArrayList<>();
	}

	public Employee(Integer employee_id, String name, Integer department_id, String role, Integer salary) {
		super();
		this.employee_id = employee_id;
		this.name = name;
		this.department_id = department_id;
		this.role = role;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public void addProject(Project project) {
		this.projects.add(project);
	}

	public List<Project> getCurrent_projects() {
		return this.projects;
	}

	public void setCurrent_projects(List<Project> projects) {
		this.projects = projects;
	}

	public void removeProject(Project project) {
		this.projects.remove(project);
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", name=" + name + ", department_id=" + department_id
				+ ", currentProjects=" + projects + ", role=" + role + ", salary=" + salary + "]";
	}

}
