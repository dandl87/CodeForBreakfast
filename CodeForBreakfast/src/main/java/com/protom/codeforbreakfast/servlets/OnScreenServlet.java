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

public class OnScreenServlet extends HttpServlet{
	
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OnScreenServlet() {
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
						 
						String articleLink = request.getParameter("articleLinkToView");
						String articleTitle = request.getParameter("articleTitleToView"); 
						
						//Fase 2
					 
						ServiceUser serviceUser = new ServiceUser();   
						
						ServiceMsg serviceMsg = ServiceMsg.getInstance(); 
						 
						HttpSession currentSession = request.getSession();
						
						
						
						User user = (User) currentSession.getAttribute("user"); 
						
						// se ho l'user
						if(user!=null) {
							
						System.out.println(user);
							
							
						serviceUser.avviaConnessione();
						 
								 
							 
						currentSession.setMaxInactiveInterval(10*60);   
						
						serviceMsg.setValues(true, articleTitle );
						
						//messaggio in console 
						currentSession.removeAttribute("infoMsg"); 
						currentSession.setAttribute("infoMsg", serviceMsg.getMsg().getMessage()); 
						
						//articoloDaVisualizzare 
						currentSession.setAttribute("articleOnScreenInSession", articleLink); 
						
						
						
						//redirect a index
						RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
						
						dis.forward(request, response);
	 
					 
						serviceUser.chiudiConnessione(); 
								 
						
						 
						  
						// l'user è null quindi la sessione è scaduta	
					}else {
						
						serviceMsg.setValues(false, "Sorry, your session has expired");
						
					request.setAttribute("infoMsg", serviceMsg.getMsg().getMessage());
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
					dis.forward(request, response);  

					}
			}

}
