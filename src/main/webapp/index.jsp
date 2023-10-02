<%@ page import="kth.distrolab1.ui.UserDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <style>
            /* Style for the shopping bag container */
            #shopping-bag-container {
                position: fixed;
                top: 0;
                right: 0;
                width: 300px; /* Justera bredden efter behov */
                background-color: #fff;
                border: 1px solid #ccc;
                padding: 10px;
            }

            /* Style for the shopping bag title */
            .shopping-bag-title {
                font-size: 18px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            /* Style for the shopping bag items */
            .shopping-bag-item {
                margin-bottom: 10px;
                padding: 5px;
                border: 1px solid #ddd;
            }
        </style>
    </head>
    <body>
        <h1>
            Welcome <% if (session.getAttribute("fullname") != null){ %> <%= session.getAttribute("fullname") %> <% } else { %> stranger <%}%>
        </h1>
        <br/>
        <% if (session.getAttribute("fullname") == null) {%>
        <a href="login.jsp">Login</a>
        <a href="registerUser.jsp">Register</a>
        <%} else{%>
        <!-- Log out button -->
        <form action="session/logout" method="post">
            <input type="submit" value="Log Out">
        </form>
        <%}%>

        <div id="shopping-bag-container">
            <div class="shopping-bag-title">Shopping Bag</div>
            <div class="shopping-bag-item">
                <!-- Add your shopping bag items here -->
            </div>
            <!-- You can add more shopping bag items as needed -->
        </div>
    </body>
</html>