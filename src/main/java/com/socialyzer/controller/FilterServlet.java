package com.socialyzer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialyzer.service.PostService;
import com.socialyzer.util.DBConnection;

import com.socialyzer.model.Post;
/**
 * Servlet implementation class FilterServlet
 */
@WebServlet("/filterServlet")
public class FilterServlet extends HttpServlet {

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out= response.getWriter();
		out.println("welcome to the filter servlet");
		String date=request.getParameter("date");
		out.println(date);
		String text= request.getParameter("text");
		out.println(text);
		DBConnection dbConnection= DBConnection.getDbConnnection();
		PostService postService= new PostService(dbConnection);
		
		try
		{
		HttpSession session= request.getSession();
		if(text!=null)
		{
			ArrayList<Post> postList=postService.findAllByKeyword(text);
			if(postList.size()==0  || postList== null)
			{
				System.out.println("hello from no data found+");
				request.setAttribute("noDataFound", true);
				session.setAttribute("noDataFound", true);
				request.getRequestDispatcher("/dashBoard.jsp").forward(request, response);

				
			}
			else
			{
				request.setAttribute("noDataFound", false);
				session.setAttribute("noDataFound",false);
				request.setAttribute("filterList", postList);
				session.setAttribute("filterList", postList);
				System.out.println(postList);
				request.getRequestDispatcher("/dashBoard.jsp").forward(request, response);
				
				
			}
			
			
		}
		else
		{
			System.out.println("inside the text filter");
			ArrayList<Post> postList=postService.findAllByDate(date);
			System.out.println(postList);
			
			if(postList.size()==0)
			{
				request.setAttribute("noDataFound", true);
				session.setAttribute("noDataFound", true);
			}
			else
			{
				request.setAttribute("noDataFound", false);
				session.setAttribute("noDataFound",false);
				request.setAttribute("filterList", postList);
				session.setAttribute("filterList",postList);
				
				
			}
			request.getRequestDispatcher("/dashBoard.jsp").forward(request, response);
			
	
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	

}
