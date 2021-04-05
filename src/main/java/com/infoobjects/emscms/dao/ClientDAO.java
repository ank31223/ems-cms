package com.infoobjects.emscms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.infoobjects.emscms.dto.Client;
import com.infoobjects.emscms.dto.ClientEmployeeResponse;
import com.infoobjects.emscms.dto.Employees;

public class ClientDAO {
	private Connection con;
	PreparedStatement pst;

	public ClientDAO(Connection con) {
		super();
		this.con = con;
		try {
			Statement st = con.createStatement();
			String query = "create table if not exists Client(clientId varchar(60) not null,clientName varchar(40) not null,clientAddress varchar(80) not null)";
			String query1 = "create table if not exists ClientIds(employeeId varchar(60) not null,clientId varchar(40) not null,foreign key (employeeId) references Employees(employeeId))";
			st.executeUpdate(query);
			st.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addClient(Client client) {
		try {
			String query = "insert into Client values(?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, client.getId());
			pst.setString(2, client.getCompanyName());
			pst.setString(3, client.getCompanyAddress());
			pst.executeUpdate();
			System.out.println("Client Added Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeClient(String clientName) {
		try {
			String query = "delete from Client where clientName=?";
			pst = con.prepareStatement(query);
			pst.setString(1, clientName);
			pst.executeUpdate();
			System.out.println("Client Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateClient(Client client, String companyName) {

		try {
			String query = "update Client set clientName=?,clientAddress=? where clientName=?";
			pst = con.prepareStatement(query);
			pst.setString(1, client.getCompanyName());
			pst.setString(2, client.getCompanyAddress());
			pst.setString(3, companyName);
			pst.executeUpdate();
			System.out.print("Client Updated Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Client> showAllClients() {
		List<Client> list = new ArrayList<Client>();
		try {
			String query = "select * from Client";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getString(1));
				client.setCompanyName(rs.getString(2));
				client.setCompanyAddress(rs.getString(3));
				list.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Client getClientByName(String companyName) {
		ResultSet rs;
		Client client = new Client();
		try {
			String query = "select * from Client where clientName=?";
			pst = con.prepareStatement(query);
			pst.setString(1, companyName);
			rs = pst.executeQuery();
			rs.next();
			client.setId(rs.getString(1));
			client.setCompanyAddress(rs.getString(2));
			client.setCompanyName(rs.getString(3));

		} catch (Exception e) {
			System.out.println("the getClient Name error is: " + e);
		}
		return client;
	}

	public List<Client> getClientsByIds(List<Client> listClient) {
		// System.out.println("list of client Id is:---"+listClient.get(0));
		ResultSet rs;
		List<Client> list = new ArrayList<Client>();
		try {
			if (listClient.size() != 0) {
				String Query = "select * from Client where clientId not in";
				int size = listClient.size();
				int count = 0;

				Query = Query + "(";
				while (count < size) {
					Query = Query + "?";
					if (count == size - 1) {

					} else {
						Query = Query + ",";
					}
					count++;
				}
				Query = Query + ")";

				pst = con.prepareStatement(Query);
				count = 1;
				int i = 0;
				while (i < size) {
					pst.setString(count, listClient.get(i).getId());
					i++;
					count++;
				}
				rs = pst.executeQuery();
				while (rs.next()) {
					Client client = new Client();
					client.setId(rs.getString(1));
					client.setCompanyName(rs.getString(2));
					client.setCompanyAddress(rs.getString(3));
					list.add(client);
				}

			} else {
				String Query = "select * from Client";
				pst = con.prepareStatement(Query);
				rs = pst.executeQuery();
				while (rs.next()) {
					Client client = new Client();
					client.setId(rs.getString(1));
					client.setCompanyName(rs.getString(2));
					client.setCompanyAddress(rs.getString(3));
					list.add(client);
				}

			}

		} catch (Exception e) {
			System.out.println("The error is: " + e);
			e.printStackTrace();
		}
		return list;
	}

	public void updateEmployeeIds(Employees employeeData, Client clientData) {
		try {
			String Query = "select * from EmployeeIds where employeeId=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, employeeData.getId());
			ResultSet rs = pst.executeQuery();
			rs.next();
			if (rs.getFetchSize() == 0) {
				System.out.println("User already working for this client");
				return;
			}
			Query = "insert into EmployeeIds values(?,?)";
			pst = con.prepareStatement(Query);
			pst.setString(1, clientData.getId());
			pst.setString(2, employeeData.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ClientEmployeeResponse getAllAssignableEmployees(String clientName) {
		ClientEmployeeResponse clientEmployeeResponse = new ClientEmployeeResponse();
		List<Employees> employeeList = new ArrayList<Employees>();
		List<Client> clientList = new ArrayList<Client>();
		try {
			String Query = "select * from Client where clientName=?";
			PreparedStatement pst = con.prepareStatement(Query);
			pst.setString(1, clientName);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getString(1));
				client.setCompanyName(rs.getString(2));
				client.setCompanyAddress(rs.getString(3));
				clientList.add(client);
			}

			Query = "select distinct employeeId from EmployeeIds where clientId=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, clientList.get(0).getId());
			rs = pst.executeQuery();

			while (rs.next()) {
				Employees employees = new Employees();
				employees.setId(rs.getString(1));
				employeeList.add(employees);
			}
			int size = employeeList.size();

			if (size != 0) {
				String Query1 = "select * from Employees where employeeId not in";
				int count = 1;
				Query1 = Query1 + "(";
				while (count <= size) {
					Query1 = Query1 + "?";
					if (count == size) {

					} else {
						Query1 = Query1 + ",";
					}
					count++;
				}
				Query1 = Query1 + ")";

				PreparedStatement pst1 = con.prepareStatement(Query1);
				count = 1;
				int i = 0;
				while (count <= size) {
					System.out.println(employeeList.get(i).getId() + " " + count);
					pst1.setString(count, employeeList.get(i).getId());
					count++;
					i++;
				}
				employeeList.clear();
				ResultSet rs1 = pst1.executeQuery();
				while (rs1.next()) {
					Employees employees = new Employees();
					employees.setId(rs1.getString(1));
					employees.setName(rs1.getString(2));
					employees.setGender(rs1.getString(3));
					employees.setAge(rs1.getInt(4));
					employees.setContactNo(rs1.getInt(5));
					employees.setEmail(rs1.getString(6));
					employees.setDesignation(rs1.getString(7));
					employees.setSalary(rs1.getInt(8));
					employees.setStatus(rs1.getInt(9));
					employeeList.add(employees);
				}
			} else {

				String Query1 = "select * from Employees";
				PreparedStatement pst1 = con.prepareStatement(Query1);
				ResultSet rs1 = pst1.executeQuery();
				while (rs1.next()) {
					Employees employees = new Employees();
					employees.setId(rs1.getString(1));
					employees.setName(rs1.getString(2));
					employees.setGender(rs1.getString(3));
					employees.setAge(rs1.getInt(4));
					employees.setContactNo(rs1.getInt(5));
					employees.setEmail(rs1.getString(6));
					employees.setDesignation(rs1.getString(7));
					employees.setSalary(rs1.getInt(8));
					employees.setStatus(rs1.getInt(9));
					employeeList.add(employees);
					System.out.println("HIIIIIIIII");
				}

			}

			clientEmployeeResponse.setClientList(clientList);
			clientEmployeeResponse.setEmployeeList(employeeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientEmployeeResponse;
	}

	public ClientEmployeeResponse addEmployeeToClient(String clientId, String employeeId) {
		ClientEmployeeResponse clientEmployeeResponse = new ClientEmployeeResponse();
		List<Client> clients = new ArrayList<Client>();
		List<Employees> employees = new ArrayList<Employees>();

		try {
			String Query = "insert into EmployeeIds values(?,?)";
			pst = con.prepareStatement(Query);
			pst.setString(1, clientId);
			pst.setString(2, employeeId);
			pst.executeUpdate();

			Employees employees2 = new Employees();
			employees2.setId(employeeId);
			Client client = new Client();
			client.setId(clientId);
			clients.add(client);
			employees.add(employees2);
			clientEmployeeResponse.setClientList(clients);
			clientEmployeeResponse.setEmployeeList(employees);
			System.out.println("Employee added Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientEmployeeResponse;
	}

	public ClientEmployeeResponse getAllWorkingEmployeesUnderClient(Client client1) {
		ClientEmployeeResponse clientEmployeeResponse = new ClientEmployeeResponse();
		List<Employees> employeeList = new ArrayList<Employees>();
		List<Client> clientList = new ArrayList<Client>();
		try {
			String Query = "select employeeId from EmployeeIds where clientId=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, client1.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Employees employees = new Employees();
				employees.setId(rs.getString(1));
				employeeList.add(employees);
			}

			clientList.add(client1);
			clientEmployeeResponse.setClientList(clientList);
			clientEmployeeResponse.setEmployeeList(employeeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientEmployeeResponse;
	}

	public List<Client> getAllClientsByIds(List<Client> listClient) {
		ResultSet rs;
		List<Client> list = new ArrayList<Client>();
		try {
			if (listClient.size() != 0) {
				String Query = "select * from Client where clientId in";
				int size = listClient.size();
				int count = 0;

				Query = Query + "(";
				while (count < size) {
					Query = Query + "?";
					if (count == size - 1) {

					} else {
						Query = Query + ",";
					}
					count++;
				}
				Query = Query + ")";

				pst = con.prepareStatement(Query);
				count = 1;
				int i = 0;
				while (i < size) {
					pst.setString(count, listClient.get(i).getId());
					i++;
					count++;
				}
				rs = pst.executeQuery();
				while (rs.next()) {
					Client client = new Client();
					client.setId(rs.getString(1));
					client.setCompanyName(rs.getString(2));
					client.setCompanyAddress(rs.getString(3));
					list.add(client);
				}

			} else {
				String Query = "select * from Client";
				pst = con.prepareStatement(Query);
				rs = pst.executeQuery();
				while (rs.next()) {
					Client client = new Client();
					client.setId(rs.getString(1));
					client.setCompanyName(rs.getString(2));
					client.setCompanyAddress(rs.getString(3));
					list.add(client);
				}

			}

		} catch (Exception e) {
			System.out.println("The error is: " + e);
			e.printStackTrace();
		}
		return list;
	}

	public void deleteEmployeeFromClient(String clientId, String employeeId) {
		try {
			String Query = "delete from EmployeeIds where employeeId=? and clientId=?";
			pst = con.prepareStatement(Query);
			pst.setString(1, employeeId);
			pst.setString(2, clientId);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
