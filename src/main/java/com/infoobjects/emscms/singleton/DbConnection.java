package com.infoobjects.emscms.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

private static Connection con=null;
	
	private DbConnection() {
		
	}
	
	public static Connection getConnection() {
		if(con==null) {
			try {
				String url = "jdbc:mysql://localhost:3306/cms";
				String uname = "ank31223";
				String upass = "ank31223";
				Class.forName("com.mysql.cj.jdbc.Driver");
				con= DriverManager.getConnection(url, uname, upass);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
