package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.UserDAO;
import com.protom.codeforbreakfast.model.entity.User;

 
public class UserDAOimpl implements UserDAO{
	
	private DbConnectionMySql dbConnection;
	
	
	
	public UserDAOimpl(DbConnectionMySql dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}


	@Override
	public User readUser(String username, String password) {
		
		 
			ResultSet rs; 
			
			try {
			String query = "SELECT * FROM user WHERE username = '" + username +"' AND password = '"+password+"';";
			
			PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
			
			
				rs = ps.executeQuery();
				
				rs.next();

				String usernameFromDB= rs.getString("username");
				String passwordFromDB =rs.getString("password");
				String nomeFromDB = rs.getString("nome");
				String cognomeFromDB = rs.getString("cognome");
				User userFromDB = new User(usernameFromDB,passwordFromDB,nomeFromDB, cognomeFromDB); 
				return userFromDB;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Errore accesso a db!");
				return null;
			}

		
	}

	@Override
	public boolean createUser(User user) {
		 
		try {
		  
			 
		 
		PreparedStatement ps;

			ps = dbConnection.getConnection().prepareStatement(
					"INSERT INTO user (username, password, nome, cognome) VALUES ('"
							+ user.getUsername() + "', '" + user.getPassword() + "','" + user.getSurname() + "','"
							+ user.getName() + "');");
			System.out.println(ps.executeUpdate() + "Log: user inserted");
			
			
			
		 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			return false;
		} 
		return true; 
	}

	@Override
	public boolean updateUser(User user) {

		
		try {
		 
		String query = "UPDATE user SET username='"+user.getUsername()+"', password='"+user.getPassword()+"',nome='"+user.getName()+"', "
				+ "cognome='"+user.getSurname()+  
				"' WHERE username = " + user.getUsername()+" && password = "+user.getPassword();
		
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		System.out.println(ps.executeUpdate() + "Log: user updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			e.getStackTrace() ;
			return false;
		}

		return true;
	}
	

	@Override
	public boolean deleteUser(String username, String password) {

		try { 
		String query = "DELETE FROM user WHERE username = " + username + " && password = "+password;
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		int result= ps.executeUpdate() ;
		
		if(result==0)
			 return false; 
		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			return false;
		}

		return true;
	}


	@Override
	public ArrayList<User> readAllUsers() {
		ArrayList<User> listOfAllUsers = new ArrayList<User>();
		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM user"; 
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {
 
			String username = rs.getString("username");
			String name = rs.getString("nome");
			String surname = rs.getString("cognome"); 
			
			 
			
			 
			
			//String dateString = dataImmatricolazione.toString();
			User user = new User(username, name, surname );

			listOfAllUsers.add(user);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			e.printStackTrace();
			return null;
		}

		return listOfAllUsers;
	} 
 
 


}
