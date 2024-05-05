package com.socialyzer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialyzer.model.Account;
import com.socialyzer.model.Post;
import com.socialyzer.service.AccountService;
import com.socialyzer.service.PostService;
import com.socialyzer.util.DBConnection;

/**
 * Servlet implementation class FilterServlet
 */
@WebServlet("/competitorServlet")
public class CompetitorServlet extends HttpServlet {

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompetitorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session=request.getSession();//generating the session
		String email=(String)session.getAttribute("email");//getting the logged in user email
		System.out.println(email);
		PrintWriter out= response.getWriter();
		String competitor =(String)request.getParameter("competitor");//getting the competitor id
		System.out.println(competitor);
		out.println("Welcome to the competitor servlet");
		
		DBConnection dbConnection= DBConnection.getDbConnnection();
		AccountService accountService= new AccountService(dbConnection);//
		ArrayList<Integer> accountListCompetitor=new ArrayList();//for storing the list of followers;
		
		try
		{
			
			
			System.out.println("email");
			PostService postService= new PostService(dbConnection);
			accountListCompetitor=accountService.findAllFollower(competitor);
			
			System.out.println(accountListCompetitor);//containing the data to be displayed
			
			ArrayList<Post> postList= new ArrayList();
			postList=postService.findAllPostByEmail(competitor);
			
			System.out.println("welcome to the competitor servelet middle");
			
			
			
			long  cAvgFollower=accountService.findAverageById(competitor);
			System.out.println(cAvgFollower);
			long cAvgLike= postService.avgLikeCount(postList);
			System.out.println(cAvgLike);
			long cAvgShare=postService.avgShareCount(postList);
			System.out.println(cAvgShare);
			long cAvgScore=postService.avgSentimentScore(postList);
			System.out.println(cAvgScore);

			session.setAttribute("competitorAverageFollower", cAvgFollower);
			session.setAttribute("competitorAverageLike", cAvgLike);
			session.setAttribute("competitorAverageShare", cAvgShare);
			session.setAttribute("competitorAverageScore", cAvgScore);
			session.setAttribute("CompetitorList", accountListCompetitor);

			request.getRequestDispatcher("competitorgraph.jsp").forward(request, response);

			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
 		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
