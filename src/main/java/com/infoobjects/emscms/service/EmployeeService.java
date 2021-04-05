package com.infoobjects.emscms.service;

import java.util.List;

import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.Employees;
import com.infoobjects.emscms.dto.EmployeeClientResponse;



public interface EmployeeService {
	
	void addEmployee(Employees employee);

	void removeEmployee(String x);

	Employees getEmployeeById(String nextInt);

	List<Employees> getEmployeeList();

	void updateEmployee(Employees emp, String name, String gender, int age, int contactNo, String email, String designation, int salary);

	EmployeeClientResponse showAllAssignableClients(String employeeName);

	void addClientToEmployee(Employees employeeData, Client clientData);

	void updateClientIds(Employees employees, Client client);

	List<Employees> getAllEmployeesByListOfIds(List<Employees> employeeList);

	EmployeeClientResponse getAllClientsUnderEmployee(List<Employees> employeList, String employeeName);

	void deleteClientFromEmployee(String id, List<Client> listClient, String clientName);

	void deleteClientFromEmployee(String employeeId, String clientId);

}
