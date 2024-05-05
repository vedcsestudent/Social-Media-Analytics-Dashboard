package com.socialyzer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialyzer.model.Account;
import com.socialyzer.model.Follower;
import com.socialyzer.model.Post;
import com.socialyzer.service.AccountService;
import com.socialyzer.service.FollowerService;
import com.socialyzer.service.PostService;
import com.socialyzer.util.DBConnection;

/**
 * Servlet implementation class DashBoardServlet
 */

@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		HttpSession session= request.getSession();
		System.out.println("inside the dashboard servlet");
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		System.out.println(email+ password);
		
		ArrayList<Integer> followerList= new ArrayList();
		ArrayList<Integer> twitterFollowerList= new ArrayList();
		ArrayList<Integer> instagramFollowerList= new ArrayList();
		ArrayList<Integer> faceBookFollowerList= new ArrayList();
		ArrayList<Follower> followerDaoList= new ArrayList();
		
		DBConnection dbConnection= DBConnection.getDbConnnection();

		
		FollowerService followerService= new FollowerService(dbConnection);
		AccountService accountService= new AccountService(dbConnection);
		
		try {
			followerList=accountService.findAllFollower(email);
			System.out.println(followerList);
			
			followerDaoList=followerService.findAllByEmail(email);
			twitterFollowerList=followerService.findByPlatform(followerDaoList, "Twitter");
			instagramFollowerList= followerService.findByPlatform(followerDaoList, "Instagram");
			faceBookFollowerList= followerService.findByPlatform(followerDaoList,"FaceBook");
			
			System.out.println(followerDaoList);
			
			request.setAttribute("array", followerList);//store currennt followercount of the account such  as instagram twitter facebook 
			session.setAttribute("accountFollower", followerList);
			
			System.out.println("Twitter");
			System.out.println(twitterFollowerList);
	
			request.setAttribute("twitterFollower", twitterFollowerList);
			session.setAttribute("twitterFollower", twitterFollowerList);

			
			System.out.println("instagram");
			System.out.println(instagramFollowerList);
			request.setAttribute("instagramFollower", instagramFollowerList);
			session.setAttribute("instagramFollower", instagramFollowerList);

			
			
			System.out.println("facebook");
			System.out.println(faceBookFollowerList);
			request.setAttribute("faceBookFollower", faceBookFollowerList);
			session.setAttribute("faceBookFollower", faceBookFollowerList);
			
			//for th filteration initially pointing to false
			session.setAttribute("noDataFound", true);
			ArrayList<Post> postList1= new ArrayList();
			session.setAttribute("filterList", postList1);


			
			
			// adding the data for boxes
			ArrayList<Account> accountList= new ArrayList();
			PostService postService= new PostService(dbConnection);
			System.out.println("from the statistic box");
			
			accountList= accountService.findByEmail(email);
			long avgFollower=accountService.findAvgFollower(accountList);
			System.out.println(avgFollower);
			request.setAttribute("avgfollower", avgFollower);
			session.setAttribute("avgFollower", avgFollower);
			
			ArrayList<Post> postList= new ArrayList();
			postList= postService.findAllPostByEmail(email);
			System.out.println(postList);
			
			long avgShareCount=postService.avgShareCount(postList);
			System.out.println("avgsharecount"+avgShareCount);
			
			long avgLikeCount= postService.avgLikeCount(postList);
			System.out.println("avgLikeCount"+avgLikeCount);
			long avgSentimentScore=postService.avgSentimentScore(postList);
			System.out.println("avgSen"+avgSentimentScore);
			
			request.setAttribute("avgShare", avgShareCount);
			request.setAttribute("avgLikeCount", avgLikeCount);
			request.setAttribute("avgSentimentScore", avgSentimentScore);
			
			request.setAttribute("postList", postList);
			
			session.setAttribute("avgShare", avgShareCount);
			session.setAttribute("avgLikeCount", avgLikeCount);
			session.setAttribute("avgSentimentScore", avgSentimentScore);
			session.setAttribute("avgFollower", avgFollower);
			session.setAttribute("twitterList",twitterFollowerList);
			session.setAttribute("instagramFollower", instagramFollowerList);
			session.setAttribute("instagramList", instagramFollowerList);
			session.setAttribute("accountList", accountList);
			session.setAttribute("postList", postList);

			
			request.getRequestDispatcher("dashBoard.jsp").forward(request, response);
			
			
		
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

	

}
