package com.socialyzer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialyzer.model.Account;
import com.socialyzer.model.Facebook;
import com.socialyzer.model.Instagram;
import com.socialyzer.model.SocialMedia;
import com.socialyzer.model.Twitter;
import com.socialyzer.model.User;
import com.socialyzer.service.AccountService;
import com.socialyzer.util.DBConnection;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out= response.getWriter();
		out.println("from the post method of the servlet add user ");
		
		DBConnection dbConnection= DBConnection.getDbConnnection();
		AccountService accountService= new AccountService(dbConnection);
		
		String platform=request.getParameter("platform-name");
		String userName= request.getParameter("user-name");
		String url= request.getParameter("user-url");
		System.out.println(request.getParameter("follower-count"));
		int followerCount=Integer.parseInt(request.getParameter("follower-count"));
		
		System.out.println(platform+userName+url+followerCount);
		
		Account accountData= new Account();
		SocialMedia socialMedia= null;
		if(platform.equalsIgnoreCase("twitter"))
		{
			socialMedia= new Twitter(userName, url, followerCount);
		}
		else if(platform.equalsIgnoreCase("facebook"))
		{
			socialMedia= new Facebook(userName, url, followerCount);
		}
		else
		{
			socialMedia= new Instagram(userName, url, followerCount);
		}
		
		User user= new User();
		HttpSession session= request.getSession();
		user.setEmail((String)session.getAttribute("email"));
		user.setPassword((String)session.getAttribute("password"));
		
		System.out.println(user);
		
		accountData.setSocialMedia(socialMedia);
		accountData.setUser(user);
		System.out.println(accountData);
		Account account;
		try {
			account = accountService.insert(accountData);
			if(account== null)
				out.println("failed to save");
			else
				System.out.println("saved successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("dashBoard.jsp");
//		request.getRequestDispatcher("/dashBoard.jsp").forward(request, response);
		
		
		
	}

}
