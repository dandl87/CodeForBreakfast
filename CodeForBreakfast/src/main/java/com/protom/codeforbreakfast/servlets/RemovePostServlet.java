package com.protom.codeforbreakfast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.protom.codeforbreakfast.model.entity.Conference;
import com.protom.codeforbreakfast.model.entity.Msg;
import com.protom.codeforbreakfast.model.entity.Post;
import com.protom.codeforbreakfast.model.entity.User;
import com.protom.codeforbreakfast.service.ServiceConference;
import com.protom.codeforbreakfast.service.ServicePost;
import com.protom.codeforbreakfast.service.ServiceUser;

public class RemovePostServlet extends HttpServlet {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemovePostServlet() {
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
					 
					int postId = Integer.parseInt(request.getParameter("postId"));
					
					//Fase 2
				 
					ServiceUser serviceUser = new ServiceUser();  
					ServicePost servicePost = new ServicePost();
					ServiceConference serviceConference = new ServiceConference();
					 
					HttpSession currentSession = request.getSession();
					User user = (User) currentSession.getAttribute("user"); 
					
					
					Msg msg = serviceUser.removePost(user, postId); 
					
					if(user!=null && msg.getResult()) {
						
							
					System.out.println("Log: match User");
							
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
					 
	 				 
			 
					//creo la lista di conferences preferite facendo read a db
					ArrayList<Conference> listOfConferenceOfUser = serviceConference.caricaConferenceForUser(userNew);
					currentSessionNew.setAttribute("personalConferenceList", listOfConferenceOfUser);
					
					//messaggio in console 
					request.setAttribute("infoMsg", msg); 
					
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

		}
		
}
