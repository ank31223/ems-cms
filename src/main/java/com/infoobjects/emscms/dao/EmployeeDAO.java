package com.infoobjects.emscms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.infoobjects.emscms.commonUtils.CommonUtils;
import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.Employees;
import com.infoobjects.emscms.dto.EmployeeClientResponse;

public class EmployeeDAO {
	Connection con;
	PreparedStatement pst;

	public EmployeeDAO(Connection con) {
		this.con = con;
		try {
			Statement st = con.createStatement();
			String query = "create table if not exists Employees(employeeId varchar(60) not null primary key,employeeName varchar(40) not null,employeeGender varchar(30) not null,employeeAge int not null,employeeContactNo int not null,employeeEmail varchar(60) not null,employeeDesignation varchar(50) not null,employeeSalary int,employeeStatus int)";
			String query1 = "create table if not exists Client(clientId varchar(60) not null primary key,clientName varchar(40) not null,clientAddress varchar(80) not null)";
			String query2 = "create table if not exists ClientIds(employeeId varchar(60) not null,clientId varchar(40) not null,foreign key (employeeId) references Employees(employeeId))";

			String query3 = "create table if not exists EmployeeIds(clientId varchar(60) not null,employeeId varchar(60) not null unique,foreign key (clientId) references Client(clientId))";
			
			st.executeUpdate(query);
			st.executeUpdate(query1);
			st.executeUpdate(query2);
			st.executeUpdate(query3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveEmployee(Employees employee) {
		/*
		 * try { String
		 * query="insert into Employee values('"+CommonGenearteUUID.generateId()+"','"+
		 * employee.getName()+"','"+employee.getAge()+"','"+employee.getContactNo()+
		 * "','"+employee.getEmail()+"')"; Statement st=con.createStatement();
		 * System.out.println(st.executeUpdate(query));
		 * 
		 * System.out.println("inserted successfully"); }catch(SQLException e) {
		 * System.out.println("the error is "+e); }
		 */
		try {
			String query = "insert into Employees values(?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, CommonUtils.getUUID());
			pst.setString(2, employee.getName());
			pst.setString(3, employee.getGender());
			pst.setInt(4, employee.getAge());
			pst.setInt(5, employee.getContactNo());
			pst.setString(6, employee.getEmail());
			pst.setString(7, employee.getDesignation());
			pst.setInt(8, employee.getSalary());
			pst.setInt(9, employee.getStatus());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public void removeEmployee(String x) {
		/*
		 * try { String query="delete from Employee where employeeName='"+x+"'";
		 * Statement st=con.createStatement(); st.executeUpdate(query);
		 * }catch(SQLException e) { System.out.println("the remove error is "+ e); }
		 */
		try {
			String sqlStatement = " delete from Employees where employeeName=?";
			PreparedStatement pstmt = con.prepareStatement(sqlStatement);
			pstmt.setString(1, x);
			pstmt.executeUpdate();
			System.out.println("employee deleted Successfully");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public Employees getEmployeeById(String name) {
		Employees emp = new Employees();
		try {
			String str = "select * from Employees where employeeName=?";
			PreparedStatement pst = con.prepareStatement(str);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			rs.next();
			emp.setId(rs.getString(1));
			emp.setName(rs.getString(2));
			emp.setGender(rs.getString(3));
			emp.setAge(rs.getInt(4));
			emp.setContactNo(rs.getInt(5));
			emp.setEmail(rs.getString(6));
			emp.setDesignation(rs.getString(7));
			emp.setSalary(rs.getInt(8));
			emp.setStatus(rs.getInt(9));
		} catch (SQLException e) {
			System.out.println(e);
		}
		return emp;
	}

	public void updateEmployee(Employees emp, String name, int age, int contactNo, String email) {
		/*
		 * try { String
		 * query="update Employee set employeeName='"+name+"',employeeAge='"+age+
		 * "',employeeContactNo='"+contactNo+"',employeeEmail='"+
		 * email+"' where employeeName='"+emp.getName()+"'"; Statement
		 * st=con.createStatement(); st.executeUpdate(query); }catch(SQLException e) {
		 * System.out.println("the update error is "+e); }
		 */

	}

	public List<Employees> getEmployeeList() {
		/*
		 * List<Employee> list = new ArrayList(); String query =
		 * "select * from Employee"; try { PreparedStatement
		 * pst=con.prepareStatement(query); ResultSet rs=pst.executeQuery();
		 * while(rs.next()) { Employee emp = new Employee(); String name =
		 * rs.getString("employeeName"); int age = rs.getInt(3); int contactNo =
		 * rs.getInt(4); String email = rs.getString(5); String ids = rs.getString(1);
		 * 
		 * emp.setAge(age); emp.setContactNo(contactNo); emp.setEmail(email);
		 * emp.setName(name); emp.setId(ids); list.add(emp); } }catch(SQLException e) {
		 * System.out.println("the retrieve error is "+ e);
		 * 
		 * } return list;
		 */
		List<Employees> list = new ArrayList<Employees>();
		String query = "select * from Employees";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Employees emp = new Employees();
				String ids = rs.getString(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				int age = rs.getInt(4);
				int contactNo = rs.getInt(5);
				String email = rs.getString(6);
				String designation = rs.getString(7);
				int salary = rs.getInt(8);
				int status = rs.getInt(9);
				emp.setAge(age);
				emp.setContactNo(contactNo);
				emp.setEmail(email);
				emp.setName(name);
				emp.setId(ids);
				emp.setDesignation(designation);
				emp.setGender(gender);
				emp.setSalary(salary);
				emp.setStatus(status);
				list.add(emp);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}

	public void updateEmployee(Employees emp, String name, String gender, int age, int contactNo, String email,
			String designation, int salary) {
		try {
			String str = "update Employees set employeeName=?,employeeAge=?,employeeContactNo=?,employeeEmail=?,employeeDesignation=?,employeeSalary=?,employeeGender=? where employeeName=?";
			PreparedStatement pst = con.prepareStatement(str);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setInt(3, contactNo);
			pst.setString(4, email);
			pst.setString(5, designation);
			pst.setInt(6, salary);
			pst.setString(7, gender);
			pst.setString(8, emp.getName());
			System.out.println(pst.executeUpdate());
			System.out.println("Employee has been updated successfully");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public EmployeeClientResponse getAllNotAssignableClients(String employeeName) {
		EmployeeClientResponse employeeClientResponse = new EmployeeClientResponse();

		try {

			String Query = "select *from Employees where employeeName=?";
			PreparedStatement pst = con.prepareStatement(Query);
			pst.setString(1, employeeName);
			ResultSet rs = pst.executeQuery();
			List<Employees> listOfEmployees = new ArrayList<Employees>();
			List<Client> listOfClientIds = new ArrayList<>();
			String employeeId = "";

			while (rs.next()) {
				Employees employee = new Employees();

				employee.setId(rs.getString(1));
				employee.setName(rs.getString(2));
				employee.setGender(rs.getString(3));
				employee.setAge(rs.getInt(4));
				employee.setEmail(rs.getString(6));
				employeeId = rs.getString(1);
				listOfEmployees.add(employee);
			}

			Query = "select * from ClientIds where employeeId=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, employeeId);
			ResultSet rs1 = pst.executeQuery();
			while (rs1.next()) {
				Client client = new Client();
				client.setId(rs1.getString(2));
				listOfClientIds.add(client);
			}
			employeeClientResponse.setListEmployee(listOfEmployees);
			employeeClientResponse.setListClient(listOfClientIds);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeClientResponse;
	}

	public void addClientToEmployee(Employees employeeData, Client clientData) {
		try {
			String Query = "insert into ClientIds values(?,?)";
			pst = con.prepareStatement(Query);
			pst.setString(1, employeeData.getId());
			pst.setString(2, clientData.getId());
			pst.executeUpdate();
			System.out.println("client addedd Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Employees> getAllEmployeesByListOfIds(List<Employees> employeeList) {
		List<Employees> employeeList1 = new ArrayList<Employees>();
		
		if(employeeList.size()==0) {
			return null;
		}
		try {
			String Query = "select * from Employees where employeeId in";
			int count = 1;
			Query = Query + "(";
			while (count <= employeeList.size()) {
				Query = Query + "?";
				if (count == employeeList.size()) {

				} else {
					Query = Query + ",";
				}
				count++;
			}
			Query = Query + ")";
			pst = con.prepareStatement(Query);
			count = 1;
			int i = 0;
            
			System.out.println(Query);
			
			
			
			while (count <= employeeList.size()) {
				// System.out.println(employeeList.get(i).getId());
				pst.setString(count, employeeList.get(i).getId());
				count++;
				i++;
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Employees employees = new Employees();
				employees.setId(rs.getString(1));
				employees.setName(rs.getString(2));
				employees.setGender(rs.getString(3));
				employees.setAge(rs.getInt(4));
				employees.setContactNo(rs.getInt(5));
				employees.setEmail(rs.getString(6));
				employees.setDesignation(rs.getString(7));
				employees.setSalary(rs.getInt(8));
				employees.setStatus(rs.getInt(9));
				employeeList1.add(employees);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList1;
	}

	public EmployeeClientResponse getAllClientsIdforEmployee(Employees employee) {
		EmployeeClientResponse employeeClientResponse = new EmployeeClientResponse();
		List<Client> clientList = new ArrayList<Client>();
		List<Employees> employeeList = new ArrayList<Employees>();
		try {
			String Query = "select clientId from ClientIds where employeeId=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, employee.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getString(1));
				clientList.add(client);
			}
			employeeList.add(employee);
			employeeClientResponse.setListEmployee(employeeList);
			employeeClientResponse.setListClient(clientList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeClientResponse;
	}

	public void deleteClientFromEmployee(String employeeId, String clientId) {
		try {
			String Query = "delete from ClientIds where employeeId=? and clientId=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, employeeId);
			pst.setString(2, clientId);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
