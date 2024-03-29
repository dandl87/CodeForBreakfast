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
import com.protom.codeforbreakfast.model.entity.Post;
import com.protom.codeforbreakfast.model.entity.User;
import com.protom.codeforbreakfast.service.ServiceConference;
import com.protom.codeforbreakfast.service.ServicePost;
import com.protom.codeforbreakfast.service.ServiceUser;
 
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	  
    public LoginServlet() {
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
				String username   =  request.getParameter("username");
				String password = request.getParameter("password"); 
				
				
				//Fase 2
			 
				ServiceUser serviceUser = new ServiceUser();  
				ServicePost servicePost = new ServicePost();
				ServiceConference serviceConference = new ServiceConference();
				
				User user = serviceUser.cercaUser(username, password);
				
				 
				if(user!=null) {
					
						
				System.out.println("Log: match User");
						
				//invalido una sessione esistente
				HttpSession pastSession = request.getSession(false);
				if(pastSession != null) {
					pastSession.invalidate();
				}
					
				System.out.println(user.getUsername());
				
				// istanzio una nuova sessione
				HttpSession currentSession = request.getSession();
				currentSession.setMaxInactiveInterval(10*60); 
				currentSession.setAttribute("user", user);
				 
 				 
				//creo la lista di post preferiti facendo read a db 
				ArrayList<Post> listOfPostOfUser = servicePost.caricaPostForUser(user);
				currentSession.setAttribute("personalPostList", listOfPostOfUser);
				
				//creo la lista di conferences preferite facendo read a db
				ArrayList<Conference> listOfConferenceOfUser = serviceConference.caricaConferenceForUser(user);
				currentSession.setAttribute("personalConferenceList", listOfConferenceOfUser);
				
				//redirect a index
				response.sendRedirect("index.jsp"); 
						
				serviceUser.chiudiConnessione();
				
				}else { 					
					 
					String msg ="Login fallito!";
					
					request.setAttribute("errorMsg", msg); 
					 
					
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp"); 
					
					dis.forward(request, response);
 
				 
					serviceUser.chiudiConnessione();
 
				}
	}
}
