package com.infoobjects.emscms.dto;

import java.util.List;

public class ClientEmployeeResponse {
	private List<Client> clientList;
	private List<Employees> employeeList;
	
	public List<Client> getClientList() {
		return clientList;
	}
	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
	public List<Employees> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employees> employeeList) {
		this.employeeList = employeeList;
	}
}
