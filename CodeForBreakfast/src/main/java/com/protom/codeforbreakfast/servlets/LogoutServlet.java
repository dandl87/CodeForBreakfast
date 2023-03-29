package com.protom.codeforbreakfast.servlets;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	  
    public LogoutServlet() {
        super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//invalido una sessione esistente
		HttpSession pastSession = request.getSession(false);
		if(pastSession != null) {
			pastSession.invalidate();
		}
		
		response.sendRedirect("index.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
			doGet(request,response); 
		 
	}

}
