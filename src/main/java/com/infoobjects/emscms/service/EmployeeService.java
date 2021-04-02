package com.infoobjects.emscms.service;

import java.util.List;

import com.infoobjects.emscms.dto.Employee;



public interface EmployeeService {
	
	void addEmployee(Employee employee);

	void removeEmployee(String x);

	Employee getEmployeeById(String nextInt);

	List<Employee> getEmployeeList();

	void updateEmployee(Employee emp, String name, String gender, int age, int contactNo, String email, String designation, int salary);

}
