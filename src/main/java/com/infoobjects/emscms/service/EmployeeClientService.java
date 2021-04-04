package com.infoobjects.emscms.service;

import java.sql.ResultSet;
import java.util.List;

import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.Employees;

public interface EmployeeClientService {

	Employees assignClientToEmployee(String employeeName);

	Employees getEmployeeByName(String employeeName);

	List<Client> getAllClients();

	Client getClientByName(String clientName);

	void connectEmployeClient(String id, String employeeName);

	ResultSet getWorkingEmployees();

	ResultSet getNotWorkingEmployees();

	ResultSet getWorkingEmployeesInCompany(String nextLine);

	ResultSet getEmployeesByDesignation(String companyName, String designation);

}
