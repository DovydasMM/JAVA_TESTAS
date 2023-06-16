package com.example.final_test.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.final_test.Employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Project {

	@Id
	@SequenceGenerator(name = "proj_sequence", sequenceName = "proj_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proj_sequence")

	private Integer project_id;
	private String project_name;
	private LocalDate project_start_date;
	private LocalDate project_due_date;

	@ManyToMany(mappedBy = "projects")
	@JsonIgnore
	private List<Employee> employees = new ArrayList<>();

	public Project(String project_name, LocalDate project_due_date) {
		this();
		this.project_name = project_name;
		this.project_due_date = project_due_date;

	}

	public Project() {
		this.project_start_date = LocalDate.now();
	}

	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public LocalDate getProject_start_date() {
		return project_start_date;
	}

	public void setProject_start_date(LocalDate project_start_date) {
		this.project_start_date = project_start_date;
	}

	public LocalDate getProject_due_date() {
		return project_due_date;
	}

	public void setProject_due_date(LocalDate project_due_date) {
		this.project_due_date = project_due_date;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);

	}

	public void removeEmployee(Employee employee) {
		this.employees.remove(employee);
	}

	@Override
	public String toString() {
		return "Project [project_id=" + project_id + ", project_name=" + project_name + ", project_start_date="
				+ project_start_date + ", project_due_date=" + project_due_date + ", employees=" + employees + "]";
	}

}
