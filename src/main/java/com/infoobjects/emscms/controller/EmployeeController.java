package com.infoobjects.emscms.controller;

import java.sql.ResultSet;

public interface EmployeeController {
	void addEmployee()throws CapitalException;

	void removeEmployee();

	void updateEmployee();

	void showAllEmployee();

	ResultSet getWorkingEmployeesInCompany();

}
