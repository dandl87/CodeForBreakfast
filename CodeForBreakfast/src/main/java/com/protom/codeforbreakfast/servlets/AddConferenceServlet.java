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

public class AddConferenceServlet extends HttpServlet{
	
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AddConferenceServlet() {
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
						int conferencePage = Integer.parseInt(request.getParameter("conferencePage"));
						
						//Fase 2
					 
						ServiceUser serviceUser = new ServiceUser();
						ServiceMsg serviceMsg = ServiceMsg.getInstance(); 
						 
						 
						HttpSession currentSession = request.getSession();
						
						
						
						User user = (User) currentSession.getAttribute("user"); 
						
						// se ho l'user
						if(user!=null) {
							 
							
							
						serviceUser.avviaConnessione();
							
							
							
						serviceUser.addConference(user, conferenceId);
						
						String msg = serviceMsg.getMsg().getMessage();
						String articleOnSession = (String) currentSession.getAttribute("articleOnScreenInSession");
							
						// se l'add è andato a buon fine		
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
						RequestDispatcher dis = request.getRequestDispatcher("conferences"+conferencePage+".jsp"); 
						
						dis.forward(request, response);
	 
					 
						serviceUser.chiudiConnessione(); 
								 
						
						 
						// se l'add non è andato a buon fine
						}else { 					
							 
							
							
							request.setAttribute("infoMsg", serviceMsg.getMsg().getMessage()); 
							 
							
							RequestDispatcher dis = request.getRequestDispatcher("conferences"+conferencePage+".jsp"); 
							
							dis.forward(request, response);
		 
						 
							serviceUser.chiudiConnessione();
		 
						} 
						// l'user è null quindi la sessione è scaduta	
					}else {
						serviceMsg.setValues(false,"Sorry, your session has expired");
						
					request.setAttribute("infoMsg", serviceMsg.getMsg().getMessage());
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
					dis.forward(request, response);  

					}
			}

}
