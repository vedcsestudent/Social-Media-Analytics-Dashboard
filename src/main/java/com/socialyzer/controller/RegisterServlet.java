package com.socialyzer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.socialyzer.model.User;
import com.socialyzer.service.UserService;
import com.socialyzer.util.DBConnection;
import com.socialyzer.util.PasswordEncryption;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerservlet")
public class RegisterServlet extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		DBConnection dbConnection= DBConnection.getDbConnnection();

		UserService userService= new UserService(dbConnection);
		System.out.println("working ");
		String email= request.getParameter("email");
		email=email.trim();
		
		String password= request.getParameter("password");
		password=password.trim();
		String encryptedPassword=
				(PasswordEncryption.getEncryptedPassword(password));
		System.out.println(encryptedPassword);
		
		User existingUser= userService.findOne(email);
		if(existingUser!=null)
		{
			request.setAttribute("oldUser", true);
			 request.getRequestDispatcher("register.jsp").forward(request, response);

		}
		else
		{
			request.setAttribute("oldUser", false);
		User user= new User(email, encryptedPassword);
		 User loginUser=userService.insert(user);
		 
		 if(loginUser!=null)
		 {
			 System.out.println(" saved the  user data");
			 request.getRequestDispatcher("index.jsp").forward(request, response);

		 }
		 else
		 {
			 System.out.println("error  during  registration please try again");
			 request.getRequestDispatcher("register.jsp").forward(request, response);
		 }

		
		 
		
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
