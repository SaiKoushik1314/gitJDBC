package com.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeInsert {
	public static Connection dbconn;
	public static Statement stmt;
	public static ResultSet rset;

	public static void main(String[] args) {
		try {
			// Step1 Loading the dbDriver
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();

			// Step 2 Establishing the DB Connection
			dbconn = DriverManager.getConnection("jdbc:derby:C:\\Users\\SaiKoushik\\MyDB");

			// step3 Creating The statement Object to run the Query
			stmt = dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// step4 Running SQL Query to read the Data
			String query = "Select * from employee";
			rset = stmt.executeQuery(query);
			System.out.println("Query is executed  Successfully");

			rset.moveToInsertRow();
			rset.updateInt("EmpNo", 101);
			rset.updateString("Name", "Sekhar");
			rset.updateInt("Salary", 1200);
			rset.insertRow();

			rset.updateInt("EmpNo", 102);
			rset.updateString("Name", "Gokul");
			rset.insertRow();

			// step5 Read the Data From result Set Object
			rset.beforeFirst();
			while (rset.next()) {
				System.out.println("EmpNo: " + rset.getString("EmpNo") + "\t Name: " + rset.getString("Name")
						+ "\t Salary: " + rset.getString("Salary"));
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// step6 Close the DB Objects
			try {
				rset.close();
				stmt.close();
				dbconn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
