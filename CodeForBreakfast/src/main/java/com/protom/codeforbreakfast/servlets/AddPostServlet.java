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
import com.protom.codeforbreakfast.model.entity.SottoscrizionePost;
import com.protom.codeforbreakfast.model.entity.User; 
import com.protom.codeforbreakfast.service.ServiceMsg;
import com.protom.codeforbreakfast.service.ServicePost;
import com.protom.codeforbreakfast.service.ServiceUser;

public class AddPostServlet extends HttpServlet{
	
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AddPostServlet() {
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
						int articlesPage = Integer.parseInt(request.getParameter("articlesPage"));
						
						//Fase 2
					 
						ServiceUser serviceUser = new ServiceUser();
						ServicePost servicePost = new ServicePost();
						ServiceMsg serviceMsg = ServiceMsg.getInstance();
						
						 
						HttpSession currentSession = request.getSession();
						
						
						
						User user = (User) currentSession.getAttribute("user"); 
						
						// se ho l'user
						if(user!=null) {
							 
							
						serviceUser.avviaConnessione();
							
							
							
						serviceUser.addPost(user, postId, "Article"); 	
							
						Msg msg = serviceMsg.getMsg() ;
						String articleOnDesk = (String) currentSession.getAttribute("articleOnScreenInSession");
						
						// se l'add è andato a buon fine		
						if(serviceMsg.getMsg().getStatus()) {
							
							//update user con la nuova sottoscrizione
							ArrayList<SottoscrizionePost> listOfPostSubscription = servicePost.leggiSottoscrizioniPost(user);
							user.setSottoscrizioniPost(listOfPostSubscription);
							 
							 
			 				currentSession.setMaxInactiveInterval(10*60); 
			 				
			 				currentSession.removeAttribute("user"); 
			 				
			 				currentSession.setAttribute("user", user);
							   
								 
							//messaggio in console 
							currentSession.removeAttribute("infoMsg"); 
							
							currentSession.setAttribute("infoMsg", msg); 
							
							currentSession.setAttribute("articleOnScreenInSession", articleOnDesk); 
						 
						
						
						
						//redirect a index
						RequestDispatcher dis = request.getRequestDispatcher("articles"+articlesPage+".jsp"); 
						
						dis.forward(request, response);
	 
					 
						serviceUser.chiudiConnessione(); 
								 
						
						 
						// se l'add non è andato a buon fine
						}else { 					 
							
							request.setAttribute("infoMsg",  serviceMsg.getMsg());  
							
							RequestDispatcher dis = request.getRequestDispatcher("articles"+articlesPage+".jsp"); 
							
							dis.forward(request, response);
		 
						 
							serviceUser.chiudiConnessione();
		 
						} 
						// l'user è null quindi la sessione è scaduta	
					}else {
						serviceMsg.setValues(false, "Sorry, your session has expired", "Desk");
						
						request.setAttribute("infoMsg",serviceMsg.getMsg());
						
						RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
						
						dis.forward(request, response);  

					}
			}

}
