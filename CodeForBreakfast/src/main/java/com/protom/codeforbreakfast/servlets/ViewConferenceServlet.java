package com.protom.codeforbreakfast.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.protom.codeforbreakfast.model.entity.Conference;
import com.protom.codeforbreakfast.service.ServiceConference;

 
public class ViewConferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ViewConferenceServlet() {
        super(); 
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id= Integer.parseInt(request.getParameter("id"));
					
		ServiceConference service = new ServiceConference();
		
	    Conference actualConference = service.cercaConference(id);
	    
	    if(actualConference == null)
	    	System.out.println("Eccezione");
	    
	    request.setAttribute("conference",actualConference);
	    RequestDispatcher dis = request.getRequestDispatcher("conference.jsp");
	    dis.forward(request, response);
	                 
		
		
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
