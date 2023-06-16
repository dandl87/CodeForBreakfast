package com.protom.codeforbreakfast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.protom.codeforbreakfast.exceptions.SessionException;
import com.protom.codeforbreakfast.model.entity.Msg;
import com.protom.codeforbreakfast.model.entity.SottoscrizioneConference;
import com.protom.codeforbreakfast.model.entity.User;
import com.protom.codeforbreakfast.service.ServiceConference;
import com.protom.codeforbreakfast.service.ServiceMsg;  

public class RemoveConferenceFromConferenceSectionServlet extends HttpServlet {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoveConferenceFromConferenceSectionServlet() {
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
			 
	 
					
					//Fase 1: recupero i parametri dalla request
					 
					int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
					int conferencePage = Integer.parseInt(request.getParameter("conferencePage"));
					
					//Fase 2: creo gli oggetti che userò
				  
					ServiceConference serviceConference = new ServiceConference();   
					ServiceMsg serviceMsg = ServiceMsg.getInstance();
					 
					HttpSession currentSession = request.getSession();
					
					User user = (User) currentSession.getAttribute("user");
					
					if(user == null)
						throw new SessionException("Sessione scaduta");
					
					String articleOnDesk = (String) currentSession.getAttribute("articleOnScreenInSession");
					   
						
						serviceConference.removeConference(user, conferenceId, "Conference");
						
						Msg msg = serviceMsg.getMsg();
						 
						if(msg.getStatus()) {
							
							//update user senza la sottoscrizione
							ArrayList<SottoscrizioneConference> listOfConferenceSubscription = serviceConference.leggiSottoscrizioniConference(user);
							user.setSottoscrizioniConference(listOfConferenceSubscription);
							 
							
						 
	 					
		 					currentSession.removeAttribute("user"); 
		 					currentSession.setAttribute("user", user);
						  
							currentSession.setMaxInactiveInterval(10*60);  
							
							//messaggio in console 
							currentSession.removeAttribute("infoMsg"); 
							currentSession.setAttribute("infoMsg", msg);
							
							currentSession.setAttribute("articleOnScreenInSession", articleOnDesk);
							
							//redirect a index
							RequestDispatcher dis = request.getRequestDispatcher("conferences"+conferencePage+".jsp"); 
	 				
						
							dis.forward(request, response); 
		   
					
					} else { 					
						  
						
						request.setAttribute("infoMsg", serviceMsg.getMsg()); 
						 
						
						RequestDispatcher dis = request.getRequestDispatcher("articles"+conferencePage+".jsp"); 
						
						dis.forward(request, response);
	  
	 
					} 
		}
		
}
