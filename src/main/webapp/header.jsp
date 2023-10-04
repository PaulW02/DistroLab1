<%@ page import="kth.distrolab1.ui.UserDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <style>
        /* Header Styles */
        header {
            background-color: #20232a; /* Dark background color */
            color: #61dafb; /* Light text color */
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-family: 'Arial', sans-serif; /* Modern font style */
        }

        /* Navigation Menu Styles */
        nav ul {
            list-style: none;
            padding: 0;
            display: flex;
        }

        nav li {
            margin-right: 20px;
        }

        nav a {
            text-decoration: none;
            color: #61dafb; /* Light text color */
            font-weight: 600; /* Bold text */
            font-size: 16px; /* Larger text size */
            transition: color 0.3s;
        }

        nav a:hover {
            color: #FF5733; /* Highlight color on hover */
        }

        /* Button Styles */
        .btn {
            background-color: #FF5733; /* Eye-catching button color */
            color: #fff; /* White text color */
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: 600; /* Bold text */
            font-size: 16px; /* Larger text size */
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #FF7542; /* Slight color change on hover */
        }
    </style>
</head>
<body>
<header>
    <%UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");%>


    <img src="C:\Users\paula\Desktop\KTH\Distribuerade system\DistroLab1\DistroLab1\src\main\webapp\resources\Innovania-2.png" alt="Your Logo">
    <nav>
        <ul>
            <li><a href="http://localhost:8080/DistroLab1_war_exploded/">Home</a></li>
            <li><a href="http://localhost:8080/DistroLab1_war_exploded/">Products</a></li>
        </ul>
    </nav>
    <div>
        <% if (userDTO != null && userDTO.getRoles() != null && userDTO.getRoles().contains("role_admin")) {%>
            <a href="adminPanel.jsp" class="btn">Only For Admins</a>
        <%}%>
        <% if (userDTO == null) {%>
            <a href="login.jsp" class="btn">Login</a>
            <a href="registerUser.jsp" class="btn">Register</a>
        <%} else{%>
        <!-- Log out button -->
            <a href="session/logout" class="btn">Log Out</a>
        <%}%>
            <a href="#" class="btn">Shopping Bag</a>
    </div>
</header>

