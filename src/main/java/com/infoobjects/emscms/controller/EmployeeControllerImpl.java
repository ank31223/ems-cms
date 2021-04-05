package com.infoobjects.emscms.controller;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.Employees;
import com.infoobjects.emscms.dto.EmployeeClientResponse;
import com.infoobjects.emscms.service.EmployeeService;

public class EmployeeControllerImpl implements EmployeeController {

	private Scanner scanner;
	private EmployeeService employeeService;

	public EmployeeControllerImpl(Scanner scanner, EmployeeService employeeService) {
		this.scanner = scanner;
		this.employeeService = employeeService;
	}

	public void addEmployee() throws CapitalException {
		Employees employee = new Employees();
		System.out.println("Enter the Employee Detaills to be added:- ");
		System.out.println("Enter the Name of Employee");
		scanner.nextLine();
		String name = scanner.nextLine();
		if (isCapital(name)) {

		} else {
			throw new CapitalException("name must be in capital letters");
		}
		employee.setName(name);
		System.out.println("Enter the gender of Employee");
		employee.setGender(scanner.nextLine());
		System.out.println("Enter the Age of Employee");
		employee.setAge(scanner.nextInt());
		System.out.println("Enter the contactNo of Employee");
		employee.setContactNo(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Enter the Designation of Employee");
		employee.setDesignation(scanner.nextLine());
		System.out.println("Enter the Salary of Employee");
		employee.setSalary(scanner.nextInt());
		System.out.println(
				"Enter the Status of Employee whether he/she working or not \n if working press 1 if not working press 0");
		employee.setStatus(scanner.nextInt());

		System.out.println("Enter the emailId of Employee");
		String email = scanner.next();
		String regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		int i = 0;

		if (pattern.matcher(email).matches()) {
			employee.setEmail(email);
		} else {

			while (i != 3) {
				System.out.println("Enter the valid emailId of Employee");
				email = scanner.next();
				if (pattern.matcher(email).matches()) {
					employee.setEmail(email);
					break;
				}
				i++;
			}
		}
		if (i != 3) {
			employeeService.addEmployee(employee);
			System.out.print("User added Successfully");
		} else {
			System.out.print("User not able to add employee");
		}

	}

	public void removeEmployee() {
		System.out.println("enter the name of employee  you want to delete");
		scanner.nextLine();
		String x = scanner.nextLine();
		employeeService.removeEmployee(x);
	}

	public void updateEmployee() {
		System.out.println("Enter the name of Employee whose details you want to update");
		scanner.nextLine();
		Employees emp = employeeService.getEmployeeById(scanner.nextLine());
		System.out.println(emp);
		System.out.println(
				"Update employee name,gender,age,contactNo,email,designation,salary,status and assignedClient");
		System.out.println("update name");
		String name = scanner.nextLine();
		System.out.println("update gender");
		String gender = scanner.nextLine();
		System.out.println("update age");
		int age = scanner.nextInt();
		System.out.println("update contact no");
		int contactNo = scanner.nextInt();
		scanner.nextLine();
		System.out.println("update email");
		String email = scanner.nextLine();
		System.out.println("update designation");
		String designation = scanner.nextLine();
		System.out.println("update salary");
		int salary = scanner.nextInt();

		employeeService.updateEmployee(emp, name, gender, age, contactNo, email, designation, salary);
	}

	public void showAllEmployee() {
		for (Employees empl : employeeService.getEmployeeList()) {
			System.out.println(empl);
		}
	}

	private boolean isCapital(String name) {
		char[] arr = name.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (Character.isLowerCase(arr[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ResultSet getWorkingEmployeesInCompany() {
		return null;

	}

	@Override
	public void addClientToEmployee() {
		scanner.nextLine();
		while (true) {
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s %10s %20s %30s %25s %15s", "EmployeId", "EmployeeName", "EmployeeGender",
					"EmployeeAge", "EmployeeContactNo", "EmployeeDesignation", "EmployeeEmail", "EmployeeStatus");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			for (Employees employee : employeeService.getEmployeeList()) {
				System.out.format("%40s %25s %10s %10d %20d %30s %30s %7d", employee.getId(), employee.getName(),
						employee.getGender(), employee.getAge(), employee.getContactNo(), employee.getDesignation(),
						employee.getEmail(), employee.getStatus());
				System.out.println();
			}
			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");

			System.out.println("Enter the name of Employee whom you want to assign client");

			String employeeName = scanner.nextLine();
			EmployeeClientResponse ecs = employeeService.showAllAssignableClients(employeeName);

			for (Employees employee : ecs.getListEmployee()) {
				System.out.println(employee);
			}

			System.out.println("All available clients that could be added to Above employee");

			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s", "ClientId", "ClientName", "ClientAddress");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");

			for (Client client : ecs.getListClient()) {
				System.out.format("%40s %25s %10s", client.getId(), client.getCompanyName(),
						client.getCompanyAddress());
				System.out.println();
			}

			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");
			if (ecs.getListClient().size() == 0) {
				System.out.println("All clients already assigned to this employee");
				System.out.println("enter -1 to exit");
				if (scanner.nextInt() == -1) {
					break;
				} else {
					continue;
				}
			}

			System.out.println("Enter the name of client that is to be assigned");
			String clientName = scanner.next();
			Client clientData = null;
			Employees employeeData = ecs.getListEmployee().get(0);

			for (Client client : ecs.getListClient()) {
				if (client.getCompanyName().equalsIgnoreCase(clientName)) {
					clientData = client;
				}
			}
			System.out.println(clientData);
			employeeService.addClientToEmployee(employeeData, clientData);

			System.out.println("enter -1 to exit");
			if (scanner.nextInt() == -1) {
				break;
			} else {
				continue;
			}

		}
	}

	@Override
	public void getAllClientsUnderEmployee() {
		scanner.nextLine();
		while (true) {
			List<Employees> employeList = employeeService.getEmployeeList();

			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s %10s %20s %30s %25s %15s", "EmployeId", "EmployeeName", "EmployeeGender",
					"EmployeeAge", "EmployeeContactNo", "EmployeeDesignation", "EmployeeEmail", "EmployeeStatus");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			for (Employees employee : employeList) {
				System.out.format("%40s %25s %10s %10d %20d %30s %30s %7d", employee.getId(), employee.getName(),
						employee.getGender(), employee.getAge(), employee.getContactNo(), employee.getDesignation(),
						employee.getEmail(), employee.getStatus());
				System.out.println();
			}
			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");

			for (Employees employees : employeList) {
				System.out.println(employees);
			}
			System.out.println();
			System.out.println("Enter the name of employee whose clients detals you want to know");
			String employeeName = scanner.nextLine();

			EmployeeClientResponse employeeClientResponse = employeeService.getAllClientsUnderEmployee(employeList,
					employeeName);

			for (Employees employees : employeeClientResponse.getListEmployee()) {
				System.out.println(employees);
			}
			System.out.println();

			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s", "ClientId", "ClientName", "ClientAddress");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");

			if (employeeClientResponse.getListClient().size() == 0) {

			}
			for (Client client : employeeClientResponse.getListClient()) {
				System.out.format("%40s %25s %10s", client.getId(), client.getCompanyName(),
						client.getCompanyAddress());
				System.out.println();
			}

			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");

			System.out.println("enter -1 to exit");
			if (scanner.nextInt() == -1) {
				break;
			}
			scanner.nextLine();

		}

	}

	@Override
	public void deletClientFromEmployee() {
		scanner.nextLine();
		while (true) {
			List<Employees> employeeList = employeeService.getEmployeeList();
			for (Employees employees : employeeList) {
				System.out.println(employees);
			}
			System.out.println("Enter the name of Employee");
			String employeeName = scanner.nextLine();

			EmployeeClientResponse employeeClientResponse = employeeService.getAllClientsUnderEmployee(employeeList,
					employeeName);
			for (Employees employees : employeeClientResponse.getListEmployee()) {
				System.out.println(employees);
			}
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");
			System.out.printf("%40s %25s %10s", "ClientId", "ClientName", "ClientAddress");
			System.out.println();
			System.out.println(
					"..................................................................................................................................................................................................................................................");

			for (Client client : employeeClientResponse.getListClient()) {
				System.out.format("%40s %25s %10s", client.getId(), client.getCompanyName(),
						client.getCompanyAddress());
				System.out.println();
			}

			System.out.println();
			System.out.println(
					"...........................................................................................................................................................................................................................................................");
			System.out.println("Enter the name of client you want to remove from employee:----");
			String clientName = scanner.nextLine();
			employeeService.deleteClientFromEmployee(employeeClientResponse.getListEmployee().get(0).getId(),
					employeeClientResponse.getListClient(), clientName);

			System.out.println("enter -1 to exit to continue press 1");
			if (scanner.nextInt() == -1) {
				break;
			}
			scanner.nextLine();
		}
	}

}
