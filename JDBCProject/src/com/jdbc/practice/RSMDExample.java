package com.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RSMDExample {
	public static Connection dbconn;
	public static Statement stmt;
	public static ResultSet rset;
	public static ResultSetMetaData rsmd;

	public static void main(String[] args) {
		// Step1 Loading the dbDriver
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();

			// Step 2 Establishing the DB Connection
			dbconn = DriverManager.getConnection("jdbc:derby:C:\\Users\\SaiKoushik\\MyDB");

			// step3 Creating The statement Object to run the Query
			stmt = dbconn.createStatement();

			// step4 Running SQL Query to read the Data
			String query = "select * from item ";
			rset = stmt.executeQuery(query);

			rsmd = rset.getMetaData();
			int i = 1;
			while (i <= rsmd.getColumnCount()) {
				String columnName = rsmd.getColumnName(i);
				String columnType = rsmd.getColumnTypeName(i);
				System.out.print("The name of the column " + i + " is: '");
				System.out.print(columnName);
				System.out.println("'");
				System.out.print("The data type of the column " + i + " is: ");
				System.out.println(columnType);
				i++;
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				dbconn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
