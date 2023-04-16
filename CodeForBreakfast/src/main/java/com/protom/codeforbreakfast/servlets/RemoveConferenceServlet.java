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

public class RemoveConferenceServlet extends HttpServlet {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoveConferenceServlet() {
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
					 
					int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
					
					//Fase 2
				 
					ServiceUser serviceUser = new ServiceUser();  
					ServiceMsg serviceMsg = ServiceMsg.getInstance();
					 
					HttpSession currentSession = request.getSession();
					
					 
					
					User user = (User) currentSession.getAttribute("user");
					String articleOnSession = (String) currentSession.getAttribute("articleOnScreenInSession");
					
					 
					if(user!=null) {
						
					serviceUser.avviaConnessione();
					
					serviceUser.removeConference(user, conferenceId); 
					String msg = serviceMsg.getMsg().getMessage();
						
					if(serviceMsg.getMsg().getStatus()) {
						 
						//invalido una sessione esistente
						HttpSession pastSession = request.getSession(false);
						if(pastSession != null) {
							pastSession.invalidate();
						}
								 
							
					// istanzio una nuova sessione
	 				HttpSession currentSessionNew = request.getSession();
	 				currentSessionNew.setMaxInactiveInterval(10*60);
	 					
	 				User userNew = serviceUser.cercaUser(user.getUsername(), user.getPassword());
	 					
	 				currentSessionNew.removeAttribute("user"); 
	 				currentSessionNew.setAttribute("user", userNew);
					  
					currentSessionNew.setMaxInactiveInterval(10*60);   
						 
					//messaggio in console 
					currentSessionNew.removeAttribute("infoMsg"); 
					currentSessionNew.setAttribute("infoMsg", msg);
					
					
					currentSessionNew.setAttribute("articleOnScreenInSession", articleOnSession); 
					
					//redirect a index
					
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
					
					dis.forward(request, response);
					 
 
				 
					serviceUser.chiudiConnessione(); 
					
					 
					
					}else { 					
						  
						
						request.setAttribute("infoMsg",serviceMsg.getMsg().getMessage()); 
						 
						
						RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
						
						dis.forward(request, response);
	 
					 
						serviceUser.chiudiConnessione();
	 
					}
			}else {
				serviceMsg.setValues(false, "Sorry, your session has expired");
			request.setAttribute("infoMsg",serviceMsg.getMsg().getMessage());
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
			dis.forward(request, response); 
			}
		}
		
}
