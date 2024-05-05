package com.socialyzer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialyzer.dao.UserDao;
import com.socialyzer.model.User;
import com.socialyzer.util.DBConnection;
import com.socialyzer.util.PasswordEncryption;
import com.socialyzer.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try
		{
			DBConnection dbConnection= DBConnection.getDbConnnection();
			UserDao userDao = new UserDao(dbConnection);
			
			System.out.println("working");
			String email=request.getParameter("email");
			email= email.trim();
			String password=request.getParameter("password");
			password= password.trim();
			
			UserService userService= new UserService(dbConnection);
			
			User user= userService.findOne(email);
			if(user!= null)
			{
				String encryptedPassword= PasswordEncryption.getEncryptedPassword(password);
				System.out.println(encryptedPassword);
				if(user.getPassword().equals(encryptedPassword))
				{
					System.out.println("password is correct");
					HttpSession session= request.getSession(true);
					session.setAttribute("email",  email);
					System.out.println(email);
					System.out.println(password);
					System.out.println("inside the login servlet");
					session.setAttribute("password", password);
					
					session.setAttribute("successMessage",  "logged in successfully");
					session.setAttribute("islogin", true);
					
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
				else
				{
					System.out.println("password is wrong");
					request.setAttribute("passwordError",true);	
					request.getRequestDispatcher("/login.jsp").forward(request, response);

				}
				
			}
			else
			{
				request.setAttribute("usernameError", "invalid username");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
	
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//doGet(request, response);
	}

}
