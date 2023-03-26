package com.protom.codeforbreakfast.dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionMySql {
	
		//private static final String dbURL = "jdbc:mysql://localhost:3306/codeforbreakfast";
		//private static final String username = "root";
		//private static final String password = "1234"; 
		private static final String param= "jdbc:mysql://localhost:3306/code_for_breakfast?user=root&password=1234&serverTimezone=Europe/Rome";
	 
		
		
		public static Connection avviaConnessione() {
			
			try { 
				
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  Connection connection = DriverManager.getConnection(param);
			 
			  if (connection == null) {
				  System.out.println("Connessione al db failed");
				  return null;
			  }
			  
			  return connection;

				} catch (Exception e) {
					System.out.println("Eccezione --> Connessione al db failed");
					e.printStackTrace();
					return null;
				}

		}
		
		public static void chiudiConnessione(Connection connection) { 
				 try {
					 connection.close();
					 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
		}

}
