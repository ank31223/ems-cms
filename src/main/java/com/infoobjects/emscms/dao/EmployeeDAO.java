package com.infoobjects.emscms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.infoobjects.emscms.commonUtils.CommonUtils;
import com.infoobjects.emscms.dto.Employee;

public class EmployeeDAO {
	Connection con;
	PreparedStatement pst;

	public EmployeeDAO(Connection con) {
		this.con = con;
		try {
			Statement st = con.createStatement();
			String query = "create table if not exists Employee(employeeId varchar(60) not null,employeeName varchar(40) not null,employeeGender varchar(30) not null,employeeAge int not null,employeeContactNo int not null,employeeEmail varchar(60) not null,employeeDesignation varchar(50) not null,employeeSalary int,employeeStatus int,clientId varchar(60),primary key (employeeId),foreign key (clientId) references Client(clientId))";   
			String Query1= "create table EmployeeIds(clientId varchar(60) not null,employeeId varchar(60) not null,foreign key (clientId) references Client(clientId))";
			st.executeUpdate(query);
			st.executeUpdate(Query1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveEmployee(Employee employee) {
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
			String query = "insert into Employee values(?,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, CommonUtils.getUUID());
			pst.setString(2, employee.getName());
			pst.setString(3, employee.getGender());
			pst.setInt(4, employee.getAge());
			pst.setInt(5, employee.getContactNo());
			pst.setString(6, employee.getEmail());
			pst.setString(7,employee.getDesignation() );
			pst.setInt(8, employee.getSalary());
			pst.setInt(9, employee.getStatus());
			pst.setString(10, null);
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
			String sqlStatement = " delete from Employee where employeeName=?";
			PreparedStatement pstmt = con.prepareStatement(sqlStatement);
			pstmt.setString(1, x);
			pstmt.executeUpdate();
			System.out.println("employee deleted Successfully");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public Employee getEmployeeById(String name) {
		Employee emp = new Employee();
		try {
			String str = "select * from Employee where employeeName=?";
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
			emp.setClientId(rs.getString(10));
		} catch (SQLException e) {
			System.out.println(e);
		}
		return emp;
	}

	public void updateEmployee(Employee emp, String name, int age, int contactNo, String email) {
		/*
		 * try { String
		 * query="update Employee set employeeName='"+name+"',employeeAge='"+age+
		 * "',employeeContactNo='"+contactNo+"',employeeEmail='"+
		 * email+"' where employeeName='"+emp.getName()+"'"; Statement
		 * st=con.createStatement(); st.executeUpdate(query); }catch(SQLException e) {
		 * System.out.println("the update error is "+e); }
		 */
		

	}

	public List<Employee> getEmployeeList() {
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
		List<Employee> list = new ArrayList<Employee>();
		String query = "select * from Employee";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Employee emp = new Employee();
				String ids = rs.getString(1);
				String name = rs.getString(2);
				String gender=rs.getString(3);
				int age = rs.getInt(4);
				int contactNo = rs.getInt(5);
				String email = rs.getString(6);
				String designation=rs.getString(7);
				int salary=rs.getInt(8);
				int status=rs.getInt(9);
				String clientId=rs.getString(10);
				emp.setAge(age);
				emp.setContactNo(contactNo);
				emp.setEmail(email);
				emp.setName(name);
				emp.setId(ids);
				emp.setDesignation(designation);
				emp.setGender(gender);
				emp.setSalary(salary);
				emp.setStatus(status);
				emp.setClientId(clientId);
				list.add(emp);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}

	public void updateEmployee(Employee emp, String name, String gender, int age, int contactNo, String email,
			String designation, int salary) {
		try {
			String str = "update Employee set employeeName=?,employeeAge=?,employeeContactNo=?,employeeEmail=?,employeeDesignation=?,employeeSalary=?,employeeGender=? where employeeName=?";
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

}
