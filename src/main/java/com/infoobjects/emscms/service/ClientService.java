package com.infoobjects.emscms.service;

import java.sql.ResultSet;
import java.util.List;

import com.infoobjects.emscms.dto.Client;

public interface ClientService {

	void addClient(Client client);

	void removeClient(String string);

	void updateClient(Client client, String string);

	List<Client> showAllClients();

	Client getClientByName(String companyName);

	ResultSet getEmployeeByClientId(String string);

	void setEmployeeService(EmployeeService employeeService);

}
