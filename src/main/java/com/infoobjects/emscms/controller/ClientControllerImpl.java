package com.infoobjects.emscms.controller;

import java.util.List;
import java.util.Scanner;

import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.ClientEmployeeResponse;
import com.infoobjects.emscms.dto.Employees;
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
		// clientService.getClientByName(scanner.nextLine());
		Client client2 = clientService.getClientByName(scanner.nextLine());
		System.out.println(client2);
		System.out.println("Enter the updated Comany Name");
		client.setCompanyName(scanner.nextLine());
		System.out.println("Enter the  updated company Address");
		client.setCompanyAddress(scanner.nextLine());
		clientService.updateClient(client, client2.getCompanyName());
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
		Client client = clientService.getClientByName(scanner.nextLine());
	    clientService.getEmployeeByClientId(client.getId());
	}

	@Override
	public void addEmployeeToClient() {
		scanner.nextLine();
		while (true) {
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s", "ClientId", "ClientName", "ClientAddress");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			for (Client client : clientService.showAllClients()) {
				System.out.format("%40s %25s %10s", client.getId(), client.getCompanyName(),
						client.getCompanyAddress());
				System.out.println();
			}
			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");
			System.out.println("Enter the name of Client which you want to assign Employee");
			String clientName = scanner.nextLine();
			System.out.println("client detail is:-  ");
			ClientEmployeeResponse clientEmployeeResponse = clientService.getAllAssignableEmployees(clientName);
			
			for (Client client : clientEmployeeResponse.getClientList()) {
				System.out.println(client);
			}
			
			System.out.println();
			System.out.println("Employees that could be assigned to this client are:>>>>>>");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s %10s %20s %30s %25s %15s", "EmployeId", "EmployeeName", "EmployeeGender",
					"EmployeeAge", "EmployeeContactNo", "EmployeeDesignation", "EmployeeEmail", "EmployeeStatus");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			for (Employees employee : clientEmployeeResponse.getEmployeeList()) {
				System.out.format("%40s %25s %10s %10d %20d %30s %30s %7d", employee.getId(), employee.getName(),
						employee.getGender(), employee.getAge(), employee.getContactNo(), employee.getDesignation(),
						employee.getEmail(), employee.getStatus());
				System.out.println();
			}
			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");
			System.out.println("Enter the name of Employee That is to be Added from above available choices");
			String employeeName = scanner.nextLine();
			clientService.addEmployeeToClient(clientEmployeeResponse, employeeName);
			System.out.println("to exit press -1 and to continue press 1");
			int x = scanner.nextInt();
			if (x == -1) {
				break;
			} else {
				scanner.nextLine();
				continue;
			}
			
		}

	}

	@Override
	public void getEmployeesUnderClient() {
		scanner.nextLine();
		while (true) {
			List<Client> clientList = clientService.showAllClients();

			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s", "ClientId", "ClientName", "ClientAddress");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");

			for (Client client : clientList) {
				System.out.format("%40s %25s %10s", client.getId(), client.getCompanyName(),
						client.getCompanyAddress());
				System.out.println();
			}

			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");

			System.out.println("Enter the name of client to get details of working employees for this client");
			String clientName = scanner.nextLine();
			ClientEmployeeResponse clientEmployeeResponse = clientService.getAllWorkingEmployeesUnderClient(clientList,
					clientName);

			for (Client client : clientEmployeeResponse.getClientList()) {
				System.out.println(client);
			}

			if(clientEmployeeResponse.getEmployeeList()==null) {
				System.out.println("this client is not assigned any user");
				return;
			}
			System.out.println();
			

			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s %10s %20s %30s %25s %15s", "EmployeId", "EmployeeName", "EmployeeGender",
					"EmployeeAge", "EmployeeContactNo", "EmployeeDesignation", "EmployeeEmail", "EmployeeStatus");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			for (Employees employee : clientEmployeeResponse.getEmployeeList()) {
				System.out.format("%40s %25s %10s %10d %20d %30s %30s %7d", employee.getId(), employee.getName(),
						employee.getGender(), employee.getAge(), employee.getContactNo(), employee.getDesignation(),
						employee.getEmail(), employee.getStatus());
				System.out.println();
			}
			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");

			System.out.println("to exit press -1 and to continue press 1");
			int x = scanner.nextInt();
			if (x == -1) {
				break;
			} else {
				scanner.nextLine();
				continue;
			}
		}

	}

	@Override
	public void deleteEmpoyeeFromClient() {

		scanner.nextLine();
		while (true) {
			List<Client> clientList = clientService.showAllClients();

			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s", "ClientId", "ClientName", "ClientAddress");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");

			for (Client client : clientList) {
				System.out.format("%40s %25s %10s", client.getId(), client.getCompanyName(),
						client.getCompanyAddress());
				System.out.println();
			}

			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");

			System.out.println("Enter the name of client to delete its employees");
			String clientName = scanner.nextLine();
			ClientEmployeeResponse clientEmployeeResponse = clientService.getAllWorkingEmployeesUnderClient(clientList,
					clientName);

			for (Client client : clientEmployeeResponse.getClientList()) {
				System.out.println(client);
			}

			System.out.println();

			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s %10s %20s %30s %25s %15s", "EmployeId", "EmployeeName", "EmployeeGender",
					"EmployeeAge", "EmployeeContactNo", "EmployeeDesignation", "EmployeeEmail", "EmployeeStatus");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			for (Employees employee : clientEmployeeResponse.getEmployeeList()) {
				System.out.format("%40s %25s %10s %10d %20d %30s %30s %7d", employee.getId(), employee.getName(),
						employee.getGender(), employee.getAge(), employee.getContactNo(), employee.getDesignation(),
						employee.getEmail(), employee.getStatus());
				System.out.println();
			}
			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");

			System.out.println();
			System.out.println("Enter the name of employee you want to remove from this client:---");
			String employeeName = scanner.nextLine();
			clientService.deleteEmployeeFromClient(clientEmployeeResponse.getClientList().get(0).getId(),
					clientEmployeeResponse.getEmployeeList(), employeeName);
			
			System.out.println("to exit press -1");
			int x = scanner.nextInt();
			if (x == -1) {
				break;
			} else {
				scanner.nextLine();
				continue;
			}
		}

	}

}
