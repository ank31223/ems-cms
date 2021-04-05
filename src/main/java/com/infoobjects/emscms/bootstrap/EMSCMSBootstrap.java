package com.infoobjects.emscms.bootstrap;

import java.sql.Connection;
import java.util.Scanner;

import com.infoobjects.emscms.controller.ClientController;
import com.infoobjects.emscms.controller.ClientControllerImpl;
import com.infoobjects.emscms.controller.EmployeeController;
import com.infoobjects.emscms.controller.EmployeeControllerImpl;
import com.infoobjects.emscms.dao.ClientDAO;
import com.infoobjects.emscms.dao.EmployeeDAO;
import com.infoobjects.emscms.service.ClientService;
import com.infoobjects.emscms.service.ClientServiceImpl;
import com.infoobjects.emscms.service.EmployeeService;
import com.infoobjects.emscms.service.EmployeeServiceImpl;
import com.infoobjects.emscms.singleton.DbConnection;

public class EMSCMSBootstrap {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		Connection con;

		con = DbConnection.getConnection();
		EmployeeDAO employeeDAO = new EmployeeDAO(con);
		ClientDAO clientDAO = new ClientDAO(con);
		ClientService clientService = new ClientServiceImpl(clientDAO);
		EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO, clientService);
		EmployeeController employeeController = new EmployeeControllerImpl(scanner, employeeService);
		clientService.setEmployeeService(employeeService);
		ClientController clientController = new ClientControllerImpl(scanner, clientService);

		while (true) {
			System.out.print("1.Client Management System \n");
			System.out.print("2.Employee Management System\n");
			int choice = scanner.nextInt();
			if (choice == 1) {
				while (true) {
					System.out.print(
							" Enter the below choices to fetch/update Client \n 1.addClient\n 2.deleteClientByCompanyName\n 3.updateClientByCompanyName\n 4.showAllClientsDetails \n 5.add Employee to the client \n 6.get details of working employees under clients \n 7.remove empoyee from the client \n to the client for exit=-1 \n");
					choice = scanner.nextInt();
					if (choice == -1) {
						break;
					}

					switch (choice) {
					case 1:
						clientController.addClient();
						break;
					case 2:
						clientController.removeClient();
						break;
					case 3:
						clientController.updateClient();
						break;
					case 4:
						clientController.showAllClient();
						break;
					case 5:
						clientController.addEmployeeToClient();
						break;
					case 6:
						clientController.getEmployeesUnderClient();
						break;
					case 7:
						clientController.deleteEmpoyeeFromClient();
						break;
					}
				}

			}
			if (choice == 2) {
				while (true) {
					System.out.print(
							"\n Enter the below choices to fetch/update Employee\n 1.addEmployee\n 2.deleteEmployee\n 3.updateEmployee\n 4.showAllEmployees \n 5.add clients to the employee \n 6.show all clients under this employeess \n7.delete Client from Employee \n for exit=-1 \n");
					choice = scanner.nextInt();
					if (choice == -1) {
						break;
					}

					switch (choice) {
					case 1:
						try {
							employeeController.addEmployee();
						} catch (Exception e) {
							System.out.print("the error is " + e);
						}
						break;
					case 2:
						employeeController.removeEmployee();
						break;
					case 3:
						employeeController.updateEmployee();
						break;
					case 4:
						employeeController.showAllEmployee();
						break;
					case 5:
						employeeController.addClientToEmployee();
						break;
					case 6:
						employeeController.getAllClientsUnderEmployee();
						break;
					case 7:
						employeeController.deletClientFromEmployee();
						break;
					}
				}

			}
		}

	}

}
