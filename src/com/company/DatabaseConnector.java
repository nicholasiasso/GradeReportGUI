package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by iassona on 4/21/2017.
 */
public class DatabaseConnector {
	static Connection con;

    public static void main(String[] args) {
    	String connectionUrl = "jdbc:sqlserver://golem.csse.rose-hulman.edu:1433;" + 
    			"databaseName=GradeReport_Data;user=GRuser;password=abc123;";
    	try {
			Connection con = DriverManager.getConnection(connectionUrl);
	    	String SQL = "SELECT * FROM Student";
	    	PreparedStatement pstmt = con.prepareStatement(SQL);
	    	ResultSet rs = pstmt.executeQuery();
	    	
	    	while (rs.next()) {
	    		System.out.println(rs.getString("Name"));
	    	}
	    	rs.close();
	    	pstmt.close();
	    	con.close();
		} catch (SQLException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}
    }

    public boolean authenticateLogin(String username, String password) {

		boolean loggedIn = false;

		String connectionUrl = "jdbc:sqlserver://golem.csse.rose-hulman.edu:1433;" +
				"databaseName=GradeReport_Data;user=GRuser;password=abc123;";
		try {
			Connection con = DriverManager.getConnection(connectionUrl);
			String SQL = "EXEC LoginCheck @Username = " + username + ", @HashPass = " + password;
			PreparedStatement pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();

			loggedIn = rs.getBoolean(0);

			rs.close();
			pstmt.close();

		} catch (SQLException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}

		return loggedIn;

	}

}
