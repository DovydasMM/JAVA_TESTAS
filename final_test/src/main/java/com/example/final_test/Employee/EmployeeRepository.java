package com.example.final_test.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	/*
	 * @Query("SELECT e FROM employee e WHERE e.department = :department_id") public
	 * List<Employee> employees(@Param("department_id") Integer department_id);
	 */

}
