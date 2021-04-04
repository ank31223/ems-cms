package com.infoobjects.emscms.service;

import java.sql.ResultSet;
import java.util.List;

import com.infoobjects.emscms.dao.ClientDAO;
import com.infoobjects.emscms.dao.EmployeeClientDAO;
import com.infoobjects.emscms.dao.EmployeeDAO;
import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.Employees;

public class EmployeeClientServiceImpl implements EmployeeClientService {
	private EmployeeDAO employeeDAO;
	private ClientDAO clientDAO;
	private EmployeeClientDAO employeeClientDAO;

	public EmployeeClientServiceImpl(EmployeeDAO employeeDAO, ClientDAO clientDao,
			EmployeeClientDAO employeeClientDAO) {
		super();
		this.employeeDAO = employeeDAO;
		this.clientDAO = clientDao;
		this.employeeClientDAO = employeeClientDAO;
	}

	@Override
	public Employees assignClientToEmployee(String employeeName) {
		return employeeDAO.getEmployeeById(employeeName);
	}

	@Override
	public Employees getEmployeeByName(String employeeName) {
		return employeeDAO.getEmployeeById(employeeName);
	}

	@Override
	public List<Client> getAllClients() {
		return clientDAO.showAllClients();
	}

	@Override
	public Client getClientByName(String clientName) {
		return clientDAO.getClientByName(clientName);
	}

	@Override
	public void connectEmployeClient(String clientId, String employeeName) {
		employeeClientDAO.connectEmployeeClient(clientId, employeeName);

	}

	@Override
	public ResultSet getWorkingEmployees() {
		return employeeClientDAO.getWorkingEmployees();

	}

	@Override
	public ResultSet getNotWorkingEmployees() {
		return employeeClientDAO.getNotWorkingEmployees();
	}

	@Override
	public ResultSet getWorkingEmployeesInCompany(String companyName) {
		return employeeClientDAO.getWorkingEmployeesInCompany(companyName);
	}

	@Override
	public ResultSet getEmployeesByDesignation(String companyName, String designation) {
		return employeeClientDAO.getEmployeesByDesignation(companyName, designation);
	}

}
