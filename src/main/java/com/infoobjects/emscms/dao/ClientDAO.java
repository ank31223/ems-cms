package com.infoobjects.emscms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.infoobjects.emscms.dto.Client;

public class ClientDAO {
	private Connection con;
	PreparedStatement pst;

	public ClientDAO(Connection con) {
		super();
		this.con = con;
		try {
			Statement st = con.createStatement();
			String query = "create table if not exists Client(clientId varchar(60) not null,clientName varchar(40) not null,clientAddress varchar(80) not null)";
			String query1="create table if not exists ClientIds(employeeId varchar(60) not null,clientId varchar(40) not null,foreign key (employeeId) references Employee(employeeId))";
			st.executeUpdate(query);
			st.executeUpdate(query1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void addClient(Client client) {
		try {
			String query="insert into Client values(?,?,?)";
			pst=con.prepareStatement(query);
			pst.setString(1, client.getId());
			pst.setString(2, client.getCompanyName());
			pst.setString(3, client.getCompanyAddress());
			pst.executeUpdate();
			System.out.println("Client Added Successfully");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeClient(String clientName) {
		try {
			String query="delete from Client where clientName=?";
			pst=con.prepareStatement(query);
			pst.setString(1, clientName);
			pst.executeUpdate();
			System.out.println("Client Deleted Successfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateClient(Client client, String companyName) {
		
		try {
			String query="update Client set clientName=?,clientAddress=? where clientName=?";
			pst=con.prepareStatement(query);
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
		List<Client> list=new ArrayList<Client>();
		try {
			String query="select * from Client";
			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Client client=new Client();
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
		Client client=new Client();
		try {
			String query="select * from Client where clientName=?";
			pst=con.prepareStatement(query);
			pst.setString(1, companyName);
		    rs= pst.executeQuery();
			rs.next();
			client.setId(rs.getString(1));
			client.setCompanyAddress(rs.getString(2));
			client.setCompanyName(rs.getString(3));
			
		} catch (Exception e) {
			System.out.println("the getClient Name error is: "+ e);
		}
		return client;
	}
	
	
}
