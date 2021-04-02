package com.infoobjects.emscms.bootstrap;

import java.sql.Connection;
import java.util.Scanner;

import com.infoobjects.emscms.controller.ClientController;
import com.infoobjects.emscms.controller.ClientControllerImpl;
import com.infoobjects.emscms.controller.EmployeeClientController;
import com.infoobjects.emscms.controller.EmployeeClientControllerImpl;
import com.infoobjects.emscms.controller.EmployeeController;
import com.infoobjects.emscms.controller.EmployeeControllerImpl;
import com.infoobjects.emscms.dao.ClientDAO;
import com.infoobjects.emscms.dao.EmployeeClientDAO;
import com.infoobjects.emscms.dao.EmployeeDAO;
import com.infoobjects.emscms.service.ClientService;
import com.infoobjects.emscms.service.ClientServiceImpl;
import com.infoobjects.emscms.service.EmployeeClientService;
import com.infoobjects.emscms.service.EmployeeClientServiceImpl;
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
		EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO,clientService);
		EmployeeController employeeController = new EmployeeControllerImpl(scanner, employeeService);
		clientService.setEmployeeService(employeeService);

		ClientController clientController = new ClientControllerImpl(scanner, clientService);

		EmployeeClientDAO employeeClientDAO = new EmployeeClientDAO(con);
		EmployeeClientService employeeClientService = new EmployeeClientServiceImpl(employeeDAO, clientDAO,
				employeeClientDAO);
		EmployeeClientController employeeClientController = new EmployeeClientControllerImpl(employeeClientService,
				scanner);

		while (true) {
			while (true) {
				System.out.print(
						"\n the choices\n 1.addClient\n 2.deleteClientByCompanyName\n 3.updateClientByCompanyName\n 4.showAllClientsDetails \n  for exit=-1");
				System.out.print("\n Enter the choice above choices for Client data\n");
				int choice = scanner.nextInt();
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
					break;
				default:
					break;
				}
			}

			while (true) {
				System.out.print("\n the choices\n 1.add\n 2.delete\n 3.update\n 4.showAllEmployees \n for exit=-1");
				System.out.print("\n Enter the choice\n");
				int choice = scanner.nextInt();
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
					break;
				default:
					break;
				}
			}

			while (true) {
				System.out.print("\n Enter the choice \n 1.Assign Client to the employees \n for exit press -1");
				if (scanner.nextInt() == 1) {
					employeeClientController.assignClietToEmployee();
				} else {
					break;
				}
			}
			while (true) {
				System.out.print(
						"\n Query the data\n 1.find details of working Employees \n 2.find details of not working employees \n 3.Find details of working employees in a particuler company by CompanyName\n 4.get details of employees working in a company by their designation \n ");

				System.out.print("\n Enter the choice\n");
				int choice = scanner.nextInt();
				if (choice == -1) {
					break;
				}

				switch (choice) {
				case 1:
					employeeClientController.getWorkingEmployees();
					break;
				case 2:
					employeeClientController.getNotWorkingEmployees();
					break;
				case 3:
					employeeClientController.getWorkingEmployeesInCompany();
					break;
				case 4:
					employeeClientController.getEmployeesByDesignation();
					break;
				default:
					break;
				}

			}
            System.out.println("Enter -1 to end program else print 1 to continue");
            if(scanner.nextInt()==-1) {
            	break;
            }
            clientController.getWorkingEmplyeesInCompany();
		}

	}

}
