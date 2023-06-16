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
import com.protom.codeforbreakfast.model.entity.SottoscrizionePost;
import com.protom.codeforbreakfast.model.entity.User; 
import com.protom.codeforbreakfast.service.ServiceMsg;
import com.protom.codeforbreakfast.service.ServicePost; 

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
				  
					ServicePost servicePost = new ServicePost();
					ServiceMsg serviceMsg = ServiceMsg.getInstance();
					boolean checkArticle;
					 
					HttpSession currentSession = request.getSession();
					
					 
					
					User user = (User) currentSession.getAttribute("user"); 
					
					if(user==null)
						throw new SessionException("Sessione scaduta");
					
					String articleOnDesk = (String) currentSession.getAttribute("articleOnScreenInSession");
					 
					//se l'articolo aperto è quello da eliminare setto a null l'attributo
					checkArticle = servicePost.checkArticleOnScreen(user, postId, articleOnDesk);
					
					servicePost.removePost(user, postId, "Desk"); 
					
					Msg msg = serviceMsg.getMsg(); 
						
					if(msg.getStatus()) {  
						
						//update user senza sottoscrizione
						ArrayList<SottoscrizionePost> listOfPostSubscription = servicePost.leggiSottoscrizioniPost(user);
						
						user.setSottoscrizioniPost(listOfPostSubscription);
						 
						 
		 					
		 				currentSession.removeAttribute("user"); 
		 				currentSession.setAttribute("user", user);
						   
							 
						//messaggio in console 
						currentSession.removeAttribute("infoMsg"); 
						currentSession.setAttribute("infoMsg", msg);
						
						if(!checkArticle)
							currentSession.setAttribute("articleOnScreenInSession", articleOnDesk);
						else currentSession.setAttribute("articleOnScreenInSession", null); 
						 
						   
					
					//redirect a index
						RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
						
						dis.forward(request, response);
					  
					}else { 					
						  
						
						request.setAttribute("infoMsg",serviceMsg.getMsg()); 
						 
						
						RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
						
						dis.forward(request, response);
	 
					  
					} 
					
		}
		
}
