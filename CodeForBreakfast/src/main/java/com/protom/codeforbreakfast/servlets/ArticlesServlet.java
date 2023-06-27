package com.protom.codeforbreakfast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.protom.codeforbreakfast.exceptions.SessionException;
import com.protom.codeforbreakfast.model.entity.Post;
import com.protom.codeforbreakfast.service.ServiceAllPosts;

 
public class ArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ArticlesServlet() {
        super(); 
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		int page = Integer.parseInt(request.getParameter("page"));
		
	 
        ServiceAllPosts service = new ServiceAllPosts(); 
        ArrayList<Post> allPosts = service.caricaAllPostsOfPage(page);

        if(allPosts.size()<1) {
        	throw new SessionException("errore di paginazione");
        	
        } else {
        	 
        request.setAttribute("posts",allPosts);
        
        if(allPosts.size()>3) 
	        request.setAttribute("next", page+1);
        
        if(page>1)
        	request.setAttribute("back", page-1);
        
        RequestDispatcher dis = request.getRequestDispatcher("articles.jsp");
        dis.forward(request, response);
        }
		
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
