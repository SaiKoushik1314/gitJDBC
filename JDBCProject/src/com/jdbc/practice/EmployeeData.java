package com.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeData {
	public static Connection dbconn;
	public static Statement stmt;
	public static ResultSet rset;

	public static void main(String[] args) {
		try {
			// Step1 Loading the dbDriver
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			System.out.println("Derby Driver is Loaded Successfully");

			// Step 2 Establishing the DB Connection
			dbconn = DriverManager.getConnection("jdbc:derby:C:\\Users\\SaiKoushik\\MyDB");
			System.out.println("DataBase Connection is established Successfully");

			// step3 Creating The statement Object to run the Query
			stmt = dbconn.createStatement();
			System.out.println("statement Object Created Successfully");

			// step4 Running SQL Query to read the Data
			String query = "Select * from employee";
			rset = stmt.executeQuery(query);
			System.out.println("Query is executed  Successfully");

			// step5 Read the Data From result Set Object
			while (rset.next()) {
				System.out.println("EmpNo: " + rset.getString(1) + "\t Name: " + rset.getString(2));
			}

			// step6 Close the DB Objects
			rset.close();
			stmt.close();
			dbconn.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
