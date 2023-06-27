package com.protom.codeforbreakfast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.protom.codeforbreakfast.exceptions.SessionException;
import com.protom.codeforbreakfast.model.entity.Conference;
import com.protom.codeforbreakfast.service.ServiceAllConferences;

 
public class ConferencesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ConferencesServlet() {
        super(); 
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
         
           
         int page=Integer.parseInt(request.getParameter("page"));
         
         ServiceAllConferences service = new ServiceAllConferences();
         ArrayList<Conference> allConferences = service.caricaAllConferencesOfPage(page);
         
         if(allConferences.size()<1)
        	 throw new SessionException("errore di paginazione");
         	
         request.setAttribute("conferences",allConferences);
         request.setAttribute("page", page);
         
         if(allConferences.size()>3) 
 	        request.setAttribute("next", page+1);
         
         if(page>1)
         	request.setAttribute("back", page-1);
         
         RequestDispatcher dis = request.getRequestDispatcher("conferences.jsp");
         dis.forward(request, response);
 		
          
        
	}

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
