package com.infoobjects.emscms.service;

import java.sql.ResultSet;
import java.util.List;

import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.ClientEmployeeResponse;
import com.infoobjects.emscms.dto.Employees;

public interface ClientService {

	void addClient(Client client);

	void removeClient(String string);

	void updateClient(Client client, String string);

	List<Client> showAllClients();

	Client getClientByName(String companyName);

	ResultSet getEmployeeByClientId(String string);

	void setEmployeeService(EmployeeService employeeService);

	List<Client> getClientsByIds(List<Client> listClient);

	void updateEmployeeIds(Employees employeeData, Client clientData);

	List<Employees> getAllEmployees();

	ClientEmployeeResponse getAllAssignableEmployees(String clientName);

	void addEmployeeToClient(ClientEmployeeResponse clientEmployeeResponse, String employeeName);

	ClientEmployeeResponse getAllWorkingEmployeesUnderClient(List<Client> clientList, String clientName);

	List<Client> getAllClientsByIds(List<Client> listClient);

	void deleteEmployeeFromClient(String clientId, String employeeId);

	void deleteEmployeeFromClient(String clientId, List<Employees> employeeList, String clientName);

}
