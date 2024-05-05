<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="styles/home.css"/>
   
</head>
<body>
    <!--complete code responsive-->
        <!--navbar -->
    <div class="navbar">
        <!-- <a href="index.jsp">Home</a> -->
        <img src="images/logo2.png" alt="">
        
        
        <%if(session!=null&&session.getAttribute("email")!=null) {%>
        <a href="dashboard" class="right">Dashboard</a>
        <%} %>
        
        
        <%if(session!=null&&session.getAttribute("email")!=null){ %>
        <a href="logoutServlet" class="right">Logout</a>
        <%} 
        else{%>
        <div class="loginregister">
        <a href="register.jsp" class="right">Register</a>
        <a href="login.jsp" class="right">Login</a>
        </div>
        <%} %>
        
      </div>
      
    
      <!--Hero section -->
      <div class="hero-container">
        <div class="hero-left">
            <div class="hero-left-top">
                Welcome to the Socialyzer your social media analytics tool.
            </div>
            <div class="hero-left-bottom">
                <h1>Services</h1>
                <ul>
                    <li> Sentiment Analysis.</li>
                    <li> Analytics result in graphical form.</li>
                    <li> Competitor Analysis.</li>
                </ul>

            </div>

        </div>
        <div class="hero-right">
            <img src="images/download.png"  class=" hero-image" alt=" Image of socialmedia analytics"/>
        </div>
      </div>
      
      <div class="footer">
        <p>&copy; 2024 Social Media Analytics Dashboard. All rights reserved.</p>
      
          </div>
          
      </div>
    
        
    
</body>
</html>