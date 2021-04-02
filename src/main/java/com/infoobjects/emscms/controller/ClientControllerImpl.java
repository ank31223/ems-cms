package com.infoobjects.emscms.controller;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.service.ClientService;

public class ClientControllerImpl implements ClientController {
	private Scanner scanner;
	private ClientService clientService;

	public ClientControllerImpl(Scanner scanner, ClientService clientService) {
		super();
		this.scanner = scanner;
		this.clientService = clientService;
	}

	public void addClient() {
		System.out.print("Enter client details to be added \n Enter the name of the Company");
		scanner.nextLine();
		Client client = new Client();
		client.setCompanyName(scanner.nextLine());
		System.out.println("Enter the Address of the company");
		client.setCompanyAddress(scanner.nextLine());
		clientService.addClient(client);
		// System.out.println("User Added Successfully");
	}

	public void removeClient() {
		System.out.print("Enter the Company name you wnat to delete");
		scanner.nextLine();
		clientService.removeClient(scanner.nextLine());
	}

	public void updateClient() {

		Client client = new Client();
		System.out.println("Enter the name of company whose details you want to update");
		scanner.nextLine();
		//clientService.getClientByName(scanner.nextLine());
		Client client2=clientService.getClientByName(scanner.nextLine());
		System.out.println(client2);
		System.out.println("Enter the updated Comany Name");
		client.setCompanyName(scanner.nextLine());
		System.out.println("Enter the  updated company Address");
		client.setCompanyAddress(scanner.nextLine());
		clientService.updateClient(client,client2.getCompanyName());
		//System.out.println("Clients details has been updated Successfully");

	}

	public void showAllClient() {
		List<Client> clients = clientService.showAllClients();
		for (Client client : clients) {
			System.out.println(client);
		}
	}

	@Override
	public void getWorkingEmplyeesInCompany() {
		System.out.println("Enter the name of company");
		scanner.nextLine();
		Client client=clientService.getClientByName(scanner.nextLine());
		ResultSet rs=clientService.getEmployeeByClientId(client.getId());
	}

}
