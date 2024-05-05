<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel=" stylesheet" href="styles/competitorGraph.css"/>
    <title>Competitor Graph</title>
    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
    
</head>


<body>
    <div class="navbar">
        <a href="index.jsp">Home</a>
        <a href="dashBoard.jsp" class="right">Dashboard</a>
        <a href="logoutServlet" class="right">Logout</a>
      </div>
    
  
  <div class="container">
    <div class="small-menu" onclick="show()">menu</div>
   <div class="side-nav">
    <div class="side-nav-top" onclick="closed()">
        close
    </div>
    <div class="side-nav-container">
        <a href="Filter.jsp">Filter</a>
        <a href="addCompetitor.jsp">Add 
            Competitor
        </a>
        <a href="addUser.jsp">Add user</a>
    </div>

   </div>

   <!--analytical information-->
   <div class="statistic-container">
    <div class="statistic-content-container">
        <div class="statistic-content-box">
           <div class="box1" >   <%  if(session.getAttribute("avgLikeCount")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("avgLikeCount") %>
            <%} %> likes</div>
            
            
            
           <div class="box2">   <%  if(session.getAttribute("competitorAverageLike")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("competitorAverageLike") %>
            <%} %> likes</div>
        </div>
        
        <div class="statistic-content-box">
            <div class="box1" >   <%  if(session.getAttribute("avgShare")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("avgShare") %>
            <%} %> shares</div>
           <div class="box2">   <%  if(session.getAttribute("competitorAverageShare")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("competitorAverageShare") %>
            <%} %> shares</div>
        </div>
        
        <div class="statistic-content-box">
            <div class="box1" >   <%  if(session.getAttribute("avgSentimentScore")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("avgSentimentScore") %>
            <%} %> Sentiment Score</div>
           <div class="box2">   <%  if(session.getAttribute("competitorAverageScore")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("competitorAverageScore") %>
            <%} %> Sentiment Score</div>
        </div>
        

        <div class="statistic-content-box">
            <div class="box1" >   <%  if(session.getAttribute("avgFollower")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("avgFollower") %>
            <%} %>Follower</div>
           <div class="box2">   <%  if(session.getAttribute("competitorAverageFollower")==null)
            {%>
            
            0<%}
            else{ %>
            
            <%= session.getAttribute("competitorAverageFollower") %>
            <%} %> Follower</div>
        </div>
    </div>

    <div class="graph-container">
        <div class="graph-box">
            <div class="graph-heading">
			Comparison with the followers .
            </div>
            <div class="graph" id="graph3">
                
            </div>
        </div>


   </div>
  </div>
  
  <% 
  
      ArrayList<Integer> competitorFollowerList=(ArrayList<Integer>)session.getAttribute("CompetitorList");
  System.out.println(competitorFollowerList);
  ArrayList<Integer> accountFollowerList=(ArrayList<Integer>)session.getAttribute("accountFollower");
  System.out.println(accountFollowerList);

  %>
  
  
  <script>
  let competitor=<%=competitorFollowerList%>
  console.log(competitor);
  let account=<%=accountFollowerList%>
  console.log(competitor);
  </script>
  
  
  <!-- <div class="footer">footer</div> -->
 
 <script src="js/competitorGraph.js"></script>
</body>
</html>