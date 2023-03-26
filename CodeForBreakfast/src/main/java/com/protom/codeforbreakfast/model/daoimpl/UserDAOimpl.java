package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import com.protom.codeforbreakfast.model.dao.UserDAO;
import com.protom.codeforbreakfast.model.entity.User;

 
public class UserDAOimpl implements UserDAO{
	
	private Connection connection;
	
	
	
	public UserDAOimpl(Connection connection) {
		super();
		this.connection = connection;
	}


	@Override
	public User readUser(String username, String password) {
		
		 
			ResultSet rs; 
			
			try {
			String query = "SELECT * FROM user WHERE username = '" + username +"' AND password = '"+password+"';";
			System.out.println(query);
			PreparedStatement ps = connection.prepareStatement(query);
			
			
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

			ps = connection.prepareStatement(
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
		
		PreparedStatement ps = connection.prepareStatement(query);
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
		PreparedStatement ps = connection.prepareStatement(query);
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
 
 


}
