package com.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteItem {
	public static Connection dbconn;
	public static PreparedStatement stmt;

	public static void main(String[] args) {

		try {
			// Step1 Loading the dbDriver
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();

			// Step 2 Establishing the DB Connection
			dbconn = DriverManager.getConnection("jdbc:derby:C:\\Users\\SaiKoushik\\MyDB");

			// step3 Creating The statement Object to run the Query
			String query = "Delete from item where itemNo=?";
			stmt = dbconn.prepareStatement(query);
			stmt.setInt(1, 10);
			int i = stmt.executeUpdate();
			System.out.println(i+" Records Delted Sucessfully");

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				stmt.close();
				dbconn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
