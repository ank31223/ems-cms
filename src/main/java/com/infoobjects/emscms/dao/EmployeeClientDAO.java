package com.infoobjects.emscms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EmployeeClientDAO {

	private Connection con;
	PreparedStatement pst;
	Statement st;

	public EmployeeClientDAO(Connection con) {
		super();
		this.con = con;
	}

	public void connectEmployeeClient(String clientId, String employeeName) {
		try {
			String Query = "update Employee set clientId=? where employeeName=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, clientId);
			pst.setString(2, employeeName);
			pst.executeUpdate();
			System.out.println("Employee has been assigned client Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ResultSet getWorkingEmployees() {
		ResultSet rs = null;
		try {
			String Query = "select t1.employeeName, t1.employeeGender, t1.employeeAge, t1.employeeSalary, t1.employeeDesignation, t1.employeeStatus from Employee t1 inner join Client t2 on t1.clientId=t2.clientId";
			pst = con.prepareStatement(Query);
			rs = pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}

	public ResultSet getNotWorkingEmployees() {
		ResultSet rs = null;
		try {
			String Query = "select employeeName,employeeGender,employeeAge,employeeDesignation,employeeStatus from Employee where clientId is null";
			pst = con.prepareStatement(Query);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			System.out.println("the getNotWorkingEmployees error is");
		}

		return rs;
	}

	public ResultSet getWorkingEmployeesInCompany(String companyName) {
		ResultSet rs = null;
		try {
			String Query = "select t1.employeeName,t1.employeeGender,t1.employeeAge,t1.employeeSalary,t1.employeeDesignation,t1.employeeStatus,t2.clientName from Employee as t1 inner join Client as t2 on t1.clientId=t2.clientId where t2.clientName=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, companyName);
			rs = pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getEmployeesByDesignation(String companyName, String designation) {
		ResultSet rs = null;
		try {
			String Query = "select t1.employeeName,t1.employeeGender,t1.employeeAge,t1.employeeSalary,t1.employeeDesignation,t1.employeeStatus,t2.clientName from Employee as t1 inner join Client as t2 on t1.clientId=t2.clientId where t2.clientName=? and t1.employeeDesignation=?";
			Query=Query+" t1.designation=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, companyName);
			pst.setString(2, designation);
			rs = pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}

}
