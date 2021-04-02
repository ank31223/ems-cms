package com.infoobjects.emscms.service;

import java.sql.ResultSet;
import java.util.List;

import com.infoobjects.emscms.commonUtils.CommonUtils;
import com.infoobjects.emscms.dao.ClientDAO;
import com.infoobjects.emscms.dao.EmployeeDAO;
import com.infoobjects.emscms.dto.Client;

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

}
