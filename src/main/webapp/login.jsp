<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="styles/login.css">
</head>
<body>
    <div class="container">
        <!--navbar -->
    <div class="navbar">
        <a href="index.jsp">Home</a>
      </div>
      
    
      
    
      <!--login page-->
      <div class="register ">
        <div class="register-container shadow">
            <h2 id="register-heading">Login</h2>
            <form id="registerForm" action="loginservlet" method="post" >
                <div class="input-group">
                    <label for="email">Enter Email:</label>
                    <input type="email" id="email" name="email" placeholder="email" required>
                </div>
                <div class="input-group">
                    <label for="password">Enter Password:</label>
                    <input type="password" placeholder="Password" id="password" name="password" required>
                </div>
      
               
                <button type="submit" class="btn">Login</button>
            </form>
              <%if(request.getAttribute("passwordError")!=null&&(boolean)request.getAttribute("passwordError")==true) 
        	out.println("Invalid username or  password");
        %>
        
        </div>
    </div>
     
    
        </div>
        
        
        
    
    
</body>
</html>