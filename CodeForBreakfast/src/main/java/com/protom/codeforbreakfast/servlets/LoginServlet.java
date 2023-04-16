package com.protom.codeforbreakfast.servlets;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.protom.codeforbreakfast.model.entity.User; 
import com.protom.codeforbreakfast.service.ServiceMsg; 
import com.protom.codeforbreakfast.service.ServiceUser;
 
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	  
    public LoginServlet() {
        super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
				
				
				//Fase 1
				String username   =  request.getParameter("username");
				String password = request.getParameter("password"); 
				
				
				 
				//Fase 2
			 
				ServiceUser serviceUser = new ServiceUser(); 
				
				ServiceMsg serviceMsg = ServiceMsg.getInstance(); 
				
				serviceUser.avviaConnessione();
				
				User user = serviceUser.cercaUser(username, password);
				
				 
				if(user!=null) {
					
						
				System.out.println("Log: match User");
						
				//invalido una sessione esistente
				HttpSession pastSession = request.getSession(false);
				if(pastSession != null) {
					pastSession.invalidate();
				}
					
				System.out.println(user.getUsername());
				
				// istanzio una nuova sessione
				HttpSession currentSession = request.getSession();
				currentSession.setMaxInactiveInterval(10*60); 
				currentSession.setAttribute("user", user);
				 
			 
				 
				currentSession.setAttribute("infoMsg", serviceMsg.getMsg().getMessage()); 
				currentSession.setAttribute("articleOnScreenInSession", null); 
				
				//redirect a index
				response.sendRedirect("http://192.168.1.109:8086/CodeForBreakfast/home"); 
						
				serviceUser.chiudiConnessione();
				
				}else { 					
					 
					serviceMsg.setValues(false, "Login failed!"); 
					
					request.setAttribute("infoMsg", serviceMsg.getMsg().getMessage()); 
					 
					
					RequestDispatcher dis = request.getRequestDispatcher("http://192.168.1.109:8086/CodeForBreakfast/home"); 
					
					dis.forward(request, response);
 
				 
					serviceUser.chiudiConnessione();
 
				}
	}
}
