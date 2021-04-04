package com.infoobjects.emscms.service;

import java.sql.ResultSet;
import java.util.List;

import com.infoobjects.emscms.dao.EmployeeDAO;
import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.Employees;
import com.infoobjects.emscms.dto.EmployeeClientResponse;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	private ClientService clientService;

	public EmployeeServiceImpl(EmployeeDAO employeeDAO,ClientService clientService) {
		this.employeeDAO = employeeDAO;
		this.clientService=clientService;
	}

	public void addEmployee(Employees employee) {
		employeeDAO.saveEmployee(employee);
	}

	public void removeEmployee(String x) {
		employeeDAO.removeEmployee(x);
	}

	public Employees getEmployeeById(String nextInt) {
		return employeeDAO.getEmployeeById(nextInt);
	}

	public List<Employees> getEmployeeList() {
		return employeeDAO.getEmployeeList();
	}

	@Override
	public void updateEmployee(Employees emp, String name, String gender, int age, int contactNo, String email,
			String designation, int salary) {
		employeeDAO.updateEmployee(emp, name,gender,age,contactNo, email,designation,salary);
		
	}

	@Override
	public EmployeeClientResponse showAllAssignableClients(String employeeName) {
		
		EmployeeClientResponse employeeClientResponse=employeeDAO.getAllNotAssignableClients(employeeName);
		
		List<Client> clients=clientService.getClientsByIds(employeeClientResponse.getListClient());
		EmployeeClientResponse employeeClientResponse2=new EmployeeClientResponse();
		employeeClientResponse2.setListEmployee(employeeClientResponse.getListEmployee());
		employeeClientResponse2.setListClient(clients);
		return employeeClientResponse2;
		
	}

	@Override
	public void addClientToEmployee(Employees employeeData, Client clientData) {
		employeeDAO.addClientToEmployee(employeeData,clientData);
		clientService.updateEmployeeIds(employeeData,clientData);
		
	}

	@Override
	public void updateClientIds(Employees employees, Client client) {
		employeeDAO.addClientToEmployee(employees, client);
	}

}
