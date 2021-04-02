package com.infoobjects.emscms.service;

import java.util.List;

import com.infoobjects.emscms.dao.EmployeeDAO;
import com.infoobjects.emscms.dto.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	private ClientService clientService;

	public EmployeeServiceImpl(EmployeeDAO employeeDAO,ClientService clientService) {
		this.employeeDAO = employeeDAO;
		this.clientService=clientService;
	}

	public void addEmployee(Employee employee) {
		employeeDAO.saveEmployee(employee);
	}

	public void removeEmployee(String x) {
		employeeDAO.removeEmployee(x);
	}

	public Employee getEmployeeById(String nextInt) {
		return employeeDAO.getEmployeeById(nextInt);
	}

	public List<Employee> getEmployeeList() {
		return employeeDAO.getEmployeeList();
	}

	@Override
	public void updateEmployee(Employee emp, String name, String gender, int age, int contactNo, String email,
			String designation, int salary) {
		employeeDAO.updateEmployee(emp, name,gender,age,contactNo, email,designation,salary);
		
	}

}
