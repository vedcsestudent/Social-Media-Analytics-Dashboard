<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
.hidden
{
display:none;}
</style>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Filter</title>
    <link rel="stylesheet"  href="styles/filter.css"/>
</head>
<body>
    <div class="container">
        <!--navbar -->
    <div class="navbar">
        <a href="index.jsp">Home</a>
        <a href="dashBoard.jsp" class="right">Dashboard</a>
        <a href="logoutServlet" class="right">Logout</a>
      </div>
    
      <!--Add User page-->
      <div class="register ">
        <div class="register-container shadow">
            <h2 id="register-heading">Filter Selection</h2>
           <form id="dynamicForm" action="filterServlet" >
        <select id="filterSelect" name="filter">
            <option value="none" disabled selected >Select Filter</option>
            <option value="date">Date</option>
            <option value="text">Text</option>
        </select>
        <div id="dateInput" class="hidden">
            <label for="date">Date:</label>
            <input type="date"    id="date" name="date">
        </div>
        <div id="textInput" class="hidden">
            <label for="text">Text:</label>
            <input type="text"  id="text" name="text">
        </div>
        <button  type="submit">Submit</button>
    </form>
        </div>
    </div>
    
        </div>
        <script>

        const filterSelect = document.getElementById('filterSelect');
        const dateInput = document.getElementById('dateInput');
        const textInput = document.getElementById('textInput');
		
        
        
      
        
        filterSelect.addEventListener('change', function() {
            if (filterSelect.value === 'date') {
                dateInput.classList.remove('hidden');
                textInput.classList.add('hidden');
                
                
                
                
            } else if (filterSelect.value === 'text') {
                dateInput.classList.add('hidden');
                textInput.classList.remove('hidden');
            } else {
                dateInput.classList.add('hidden');
                textInput.classList.add('hidden');
            }
        });
        
        
    
        
        </script>
    
</body>
</html>