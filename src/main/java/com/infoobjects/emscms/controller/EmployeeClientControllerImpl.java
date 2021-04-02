package com.infoobjects.emscms.controller;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.Employee;
import com.infoobjects.emscms.filter.EmployeeFilter;
import com.infoobjects.emscms.service.EmployeeClientService;

public class EmployeeClientControllerImpl implements EmployeeClientController {
	private EmployeeClientService employeeClientService;
	private Scanner scanner;

	public EmployeeClientControllerImpl(EmployeeClientService employeeClientService, Scanner scanner) {
		super();
		this.employeeClientService = employeeClientService;
		this.scanner = scanner;
	}

	@Override
	public void assignClietToEmployee() {
		System.out.println("Enter name of the Employee whom you want to assign Client");
		scanner.nextLine();
		String employeeName = scanner.nextLine();
		Employee employee = employeeClientService.getEmployeeByName(employeeName);
		System.out.println(employee);
		System.out.println("available clients that could be assigned ");
		List<Client> clientList = employeeClientService.getAllClients();

		for (Client client : clientList) {
			System.out.println(client);
		}

		System.out.println("Enter the name of the client you want to assign employee:  " + employee.getName());
		String clientName = scanner.nextLine();
		Client client = employeeClientService.getClientByName(clientName);
		employee.setClientId(client.getId());
		employeeClientService.connectEmployeClient(client.getId(), employeeName);
		System.out.println("Emplioyee is aasigned to client Successfully");
		System.out.println(employee);
	}

	@Override
	public void getWorkingEmployees() {
		try {
			ResultSet rs = employeeClientService.getWorkingEmployees();
			while (rs.next()) {

				System.out.println("employeeDetails are:--- \n employeeName is:  " + rs.getString(1));
				System.out.println("employeeGender is:  " + rs.getString(2));
				System.out.println("employeeAge is:  " + rs.getInt(3));
				System.out.println("employeeSalary is:  " + rs.getInt(4));
				System.out.println("employeeDesignation is:  " + rs.getString(5));
				System.out.println("employeeStatus of working is:  " + rs.getInt(6));
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getNotWorkingEmployees() {
		try {
			ResultSet rs = employeeClientService.getNotWorkingEmployees();
			while (rs.next()) {
				System.out.println("employeeDetails are:--- \n employeeName is:  " + rs.getString(1));
				System.out.println("employeeGender is:  " + rs.getString(2));
				System.out.println("employeeAge is:  " + rs.getInt(3));
				System.out.println("employeeDesignation is:  " + rs.getString(4));
				System.out.println("employeeStatus of working is:  " + rs.getInt(5));
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getWorkingEmployeesInCompany() {
		try {
			System.out.println("Enter the company name to get details of working employees");
			scanner.nextLine();

			ResultSet rs = employeeClientService.getWorkingEmployeesInCompany(scanner.nextLine());
			while (rs.next()) {
				System.out.println("employeeDetails are:--- \n employeeName is:  " + rs.getString(1));
				System.out.println("employeeGender is:  " + rs.getString(2));
				System.out.println("employeeAge is:  " + rs.getInt(3));
				System.out.println("employeeSalary is:  " + rs.getInt(4));
				System.out.println("employeeDesignation is:  " + rs.getString(5));
				System.out.println("employeeStatus of working is:  " + rs.getInt(6));
				System.out.println("companyName is:  " + rs.getString(7));
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getEmployeesByDesignation() {
		EmployeeFilter employeeFilter=new EmployeeFilter();
		//employeeFilter.setDesignation(s);
		System.out.println("Enter the name of Comany to get details of employees by their designation");
		scanner.nextLine();
		String companyName = scanner.nextLine();
		System.out.println("Enter the designation from HR Manager,Software Developer,Data Engineer,Trainee,Doctor");
		String designation = scanner.nextLine();
		ResultSet rs = employeeClientService.getEmployeesByDesignation(companyName, designation);
		try {
			while (rs.next()) {
				System.out.println("employeeDetails are:--- \n employeeName is:  " + rs.getString(1));
				System.out.println("employeeGender is:  " + rs.getString(2));
				System.out.println("employeeAge is:  " + rs.getInt(3));
				System.out.println("employeeSalary is:  " + rs.getInt(4));
				System.out.println("employeeDesignation is:  " + rs.getString(5));
				System.out.println("employeeStatus of working is:  " + rs.getInt(6));
				System.out.println("companyName is:  " + rs.getString(7));
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
