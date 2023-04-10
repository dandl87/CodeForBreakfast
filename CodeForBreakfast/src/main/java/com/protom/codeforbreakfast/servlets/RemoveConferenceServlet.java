package com.protom.codeforbreakfast.servlets;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.protom.codeforbreakfast.model.entity.Msg; 
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
					ServiceMsg serviceMsg = new ServiceMsg(); 
					 
					HttpSession currentSession = request.getSession();
					
					 
					
					User user = (User) currentSession.getAttribute("user"); 
					
					 
					if(user!=null) {
						
					serviceUser.avviaConnessione();
					
					Msg msg = serviceUser.removeConference(user, conferenceId); 
						
					if(msg.getResult()) { 
							
					//invalido una sessione esistente
					HttpSession pastSession = request.getSession(false);
					if(pastSession != null) {
						pastSession.invalidate();
					}
						
					User userNew = serviceUser.cercaUser(user.getUsername(), user.getPassword());
					System.out.println(user.getUsername());
					
					// istanzio una nuova sessione
					HttpSession currentSessionNew = request.getSession();
					currentSessionNew.setMaxInactiveInterval(10*60); 
					currentSessionNew.setAttribute("user", userNew);
					 
					
					serviceMsg.verifyStatus();
					
					msg = serviceMsg.getMsg();
	 				 
			 
					
					//messaggio in console 
					currentSessionNew.removeAttribute("infoMsg"); 
					currentSessionNew.setAttribute("infoMsg", msg);  
					
					//redirect a index
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
					
					dis.forward(request, response);
 
				 
					serviceUser.chiudiConnessione(); 
					
					 
					
					}else { 					
						  
						
						request.setAttribute("infoMsg", msg); 
						 
						
						RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
						
						dis.forward(request, response);
	 
					 
						serviceUser.chiudiConnessione();
	 
					}
			}else {
			request.setAttribute("infoMsg", new Msg(false, "Sorry, your session has expired"));
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
			dis.forward(request, response); 
			}
		}
		
}