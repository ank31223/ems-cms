package com.infoobjects.emscms.service;

import java.sql.ResultSet;
import java.util.List;

import com.infoobjects.emscms.commonUtils.CommonUtils;
import com.infoobjects.emscms.dao.ClientDAO;
import com.infoobjects.emscms.dao.EmployeeDAO;
import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.ClientEmployeeResponse;
import com.infoobjects.emscms.dto.EmployeeClientResponse;
import com.infoobjects.emscms.dto.Employees;

public class ClientServiceImpl implements ClientService {
	private ClientDAO clientDAO;
	private EmployeeDAO employeeDAO;
	private EmployeeService employeeService;

	public ClientServiceImpl(ClientDAO clientDAO) {
		super();
		this.clientDAO = clientDAO;
	}

	@Override
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void addClient(Client client) {
		client.setId(CommonUtils.getUUID());
		clientDAO.addClient(client);

	}

	public void removeClient(String companyName) {
		clientDAO.removeClient(companyName);
	}

	public void updateClient(Client client, String companyName) {
		clientDAO.updateClient(client, companyName);
	}

	public List<Client> showAllClients() {
		return clientDAO.showAllClients();
	}

	public Client getClientByName(String companyName) {
		return clientDAO.getClientByName(companyName);

	}

	@Override
	public ResultSet getEmployeeByClientId(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getClientsByIds(List<Client> listClient) {
		List<Client>client=clientDAO.getClientsByIds(listClient);
		return client;
	}

	@Override
	public void updateEmployeeIds(Employees employeeData, Client clientData) {
		clientDAO.updateEmployeeIds(employeeData,clientData);
		
	}

	@Override
	public List<Employees> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeService.getEmployeeList();
	}

	@Override
	public ClientEmployeeResponse getAllAssignableEmployees(String clientName) {
		ClientEmployeeResponse clientEmployeeResponse=clientDAO.getAllAssignableEmployees(clientName);
		//ClientEmployeeResponse clientEmployeeResponse2=
		return clientEmployeeResponse ;
	}

	
	@Override
	public void addEmployeeToClient(ClientEmployeeResponse clientEmployeeResponse,String employeeName) {
		String employeeId=null;
		for (Employees employees : clientEmployeeResponse.getEmployeeList()) {
			if(employees.getName().equalsIgnoreCase(employeeName)) {
				employeeId=employees.getId();
			}
		}
		ClientEmployeeResponse clientEmployeeResponse2= clientDAO.addEmployeeToClient(clientEmployeeResponse.getClientList().get(0).getId(),employeeId);
		employeeService.updateClientIds(clientEmployeeResponse2.getEmployeeList().get(0),clientEmployeeResponse2.getClientList().get(0));
	}

}
