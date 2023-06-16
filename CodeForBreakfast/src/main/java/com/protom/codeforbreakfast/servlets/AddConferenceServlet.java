package com.protom.codeforbreakfast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.protom.codeforbreakfast.model.entity.Msg;
import com.protom.codeforbreakfast.model.entity.SottoscrizioneConference;
import com.protom.codeforbreakfast.model.entity.User;
import com.protom.codeforbreakfast.service.ServiceConference;
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
				 
				  		
				  			
			 
						
						//Fase 1: Prelevo i Parametri dalla request
						 
						int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
						int conferencePage = Integer.parseInt(request.getParameter("conferencePage"));
						
						//Fase 2: Creo i Service e tutti gli oggetti che mi servono
					 
						ServiceUser serviceUser = new ServiceUser();
						ServiceConference serviceConference = new ServiceConference();
						ServiceMsg serviceMsg = ServiceMsg.getInstance();  
						 
						HttpSession currentSession = request.getSession();
					
						User user = (User) currentSession.getAttribute("user");
						String articleOnSession = (String) currentSession.getAttribute("articleOnScreenInSession");
						
						// se ho l'user
						if(user!=null) { 
							
						serviceUser.avviaConnessione();
							
							
						// eseguo la funzionalità	
						serviceUser.addConference(user, conferenceId, "Conference");
						
						// l'operazione di add della Conference setta il msg nel serviceMsg
						Msg msg = serviceMsg.getMsg();
						
							
						// se l'add è andato a buon fine		
						if(serviceMsg.getMsg().getStatus()) { 
							
							//update user con la nuova sottoscrizione
							ArrayList<SottoscrizioneConference> listOfConferenceSubscription = serviceConference.leggiSottoscrizioniConference(user);
							user.setSottoscrizioniConference(listOfConferenceSubscription);
							  
								 
 
			 				currentSession.setMaxInactiveInterval(10*60);
			 					 
			 					
			 				currentSession.removeAttribute("user"); 
			 				currentSession.setAttribute("user", user);
							    
			 				
							//messaggio in console 
							currentSession.removeAttribute("infoMsg"); 
							currentSession.setAttribute("infoMsg", msg);
							
							
							currentSession.setAttribute("articleOnScreenInSession", articleOnSession); 
								 
						 
						
						
						//redirect a index
						RequestDispatcher dis = request.getRequestDispatcher("conferences"+conferencePage+".jsp"); 
						
						dis.forward(request, response);
	 
					 
						serviceUser.chiudiConnessione(); 
								 
						
						 
						// se l'add non è andato a buon fine
						} else { 					
							 
							
							
							request.setAttribute("infoMsg", serviceMsg.getMsg()); 
							 
							
							RequestDispatcher dis = request.getRequestDispatcher("conferences"+conferencePage+".jsp"); 
							
							dis.forward(request, response);
		 
						 
							serviceUser.chiudiConnessione();
		 
						} 
						// l'user è null quindi la sessione è scaduta	
					}else {
						serviceMsg.setValues(false,"Sorry, your session has expired", "Desk");
						
					request.setAttribute("infoMsg", serviceMsg.getMsg());
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
					dis.forward(request, response);  

					}
			}

}
