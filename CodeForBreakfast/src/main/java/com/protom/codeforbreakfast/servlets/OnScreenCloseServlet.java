package com.protom.codeforbreakfast.servlets;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.protom.codeforbreakfast.exceptions.SessionException;
import com.protom.codeforbreakfast.model.entity.User; 
import com.protom.codeforbreakfast.service.ServiceMsg;  
public class OnScreenCloseServlet extends HttpServlet{
	
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OnScreenCloseServlet() {
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
				 
				  		
					   
						
						ServiceMsg serviceMsg = ServiceMsg.getInstance();   
						 
						HttpSession currentSession = request.getSession();
						 
						User user = (User) currentSession.getAttribute("user"); 
						
						// se ho l'user
						if(user==null)
							throw new SessionException("Sessione scaduta"); 
						 
						currentSession.setMaxInactiveInterval(10*60);  
						   
						 
						serviceMsg.setValues(true,"", "Desk");
						
						//messaggio in console 
						currentSession.removeAttribute("infoMsg"); 
						currentSession.setAttribute("infoMsg", serviceMsg.getMsg()); 
						
						//articoloDaVisualizzare 
						currentSession.setAttribute("articleOnScreenInSession", null); 
						
						
						
						//redirect a index
						RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
						
						dis.forward(request, response); 
					 
			}

}
