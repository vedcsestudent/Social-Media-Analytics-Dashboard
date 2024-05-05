<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.ArrayList" %>
<html lang="en">
<%@ page import="com.socialyzer.model.Post"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel=" stylesheet" href="styles/dashBoard.css"/>
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
            <%  if(session.getAttribute("avgLikeCount")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("avgLikeCount") %>
            <%} %> Like
        </div>
        <div class="statistic-content-box">
           
            <%  if(session.getAttribute("avgShare")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("avgShare") %>
            <%} %> Share
             </div>
             
             
             
             
             
             
             
         
        <div class="statistic-content-box">
          <%  if(session.getAttribute("avgSentimentScore")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("avgSentimentScore") %>
            <%} %> Sentiment Score
        
        
        
        
        
        
        

        </div>

        <div class="statistic-content-box">
             <%  if(session.getAttribute("avgFollower")==null)
            {%>
            
            0<%} 
            else{ %>
            
            <%= session.getAttribute("avgFollower") %>
            <%} %> Follower
        
        </div>
    </div>

    <div class="graph-container">
        <div class="graph-box">
            <div class="graph-heading">
                Follower count on different Platform.
            </div>
            <div class="graph" id="graph1">
                
            </div>
        </div>

        <div class="graph-box">
            <div class="graph-heading">
               Follower Growth over the time.
            </div>
            <div class="graph" id="graph2">
                
            </div>
        </div>
    </div>

   </div>
  </div>
 
  <div class="footer">footer</div> 
  
  
  <!-- generating followerCount on different platform -->
  
  <%
  
  ArrayList<Integer> followerList=(ArrayList<Integer>)session.getAttribute("accountFollower");
  System.out.println(followerList);
  ArrayList<Integer > twitterList=(ArrayList<Integer>) session.getAttribute("twitterFollower");
  System.out.println("twitter");
  System.out.println(twitterList);
  ArrayList<Integer> faceBookList=(ArrayList<Integer>) session.getAttribute("faceBookFollower");
  System.out.println("facebookList");
  System.out.println(faceBookList);
  ArrayList<Integer> instagramList=(ArrayList<Integer>) session.getAttribute("instagramFollower");

  System.out.println("instagram");
  System.out.println(instagramList);
  ArrayList<Post> postList= (ArrayList<Post>) session.getAttribute("postList");
  System.out.println(postList);
  
  

  
  %>
  <div class="post_container">
  <% if((boolean)(session.getAttribute("noDataFound"))==true) {%>
  
  <%for(int i=0;i<postList.size();i++){ %>
   <div class="post">
   <div class="post_id_content">
    <div class="post_id">Post Id</div>
    <div class="post_id_value"><%=postList.get(i).getPostId() %></div>
    </div>
    <div class="post_box"><%=postList.get(i).getPostText() %></div>
    <div class="post_sentiment_score"><%=postList.get(i).getPostScore() %>
    
    </div>
     </div>
    <%} %>
    <%} else{%>
    <%ArrayList<Post>filterList= (ArrayList<Post>)session.getAttribute("filterList"); 
    
    if(filterList.size()==0)
    out.println("no data found");
    else
    {%>
    
    
    <%for(int i=0;i<filterList.size();i++){ %>
   <div class="post">
   <div class="post_id_content">
    <div class="post_id">Post Id</div>
    <div class="post_id_value"><%=filterList.get(i).getPostId() %></div>
    </div>
    <div class="post_box"><%=filterList.get(i).getPostText() %></div>
    <div class="post_sentiment_score"><%=filterList.get(i).getPostScore() %>
    
    </div>
     </div>
     <%} %>
    <%} %>
    
    <%} %>
    
    
   
    
    
   
    
   
    
    
    
    
    
    
    </div>
    
  <script>
  let array=<%=followerList%>
  console.log(array);
  
  
  let twitterList=<%=twitterList%>
  console.log("twitter");
  console.log(twitterList);
  
  let faceBookList=<%=faceBookList%>
  console.log("faceBookList");
  console.log(faceBookList);
  
  
  
  
  let instagramList=<%=instagramList%>
  console.log("instagram list");
  console.log(instagramList);



  </script>
 

 <script src="js/dashboardChart.js"></script>
 
 
</body>
</html>