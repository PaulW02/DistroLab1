<%@ page import="kth.distrolab1.ui.dtos.UserDTO" %>
<%@ page import="kth.distrolab1.ui.dtos.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <!-- Shopping bag container -->
    <%
        int itemCount = 0; // Skapar en variabel för att räkna antalet varor i shoppingbagen
        if (session.getAttribute("shoppingBag") != null) {
            itemCount = ((List<ItemDTO>) session.getAttribute("shoppingBag")).size(); // Hämtar antalet varor i shoppingbagen
        }
    %>
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

        /* Style for the shopping bag button */


        /* Style for the shopping bag container */
        #shopping-bag-container {
            display: none;
            position: fixed;
            top: 100px; /* Adjust the top position to place it below the button */
            right: 10px;
            width: 300px;
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 10px;
            z-index: 1000; /* Ensure it appears above other content */
        }

        /* Style for the shopping bag title */
        .shopping-bag-title {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        /* Style for the shopping bag items */
        .shopping-bag-item {
            margin-bottom: 20px;
            padding: 10px; /* Increase padding for spacing and appearance */
            border: 1px solid #ddd;
            background-color: #f9f9f9; /* Add a background color for better readability */
            position: relative; /* Lägg till position relative */
        }

        /* Style for individual item boxes within the shopping bag */
        .shopping-bag-item .item-details {
            margin-bottom: 20px; /* Add spacing between items */
            font-weight: bold; /* Make item name and price bold */
        }

        /* Style for the item price */
        .shopping-bag-item .item-price {
            color: #007bff; /* Change the color of the price */
        }

        /* Style for the remove item button */
        .remove-item-button {
            cursor: pointer;
            color: red;
            font-weight: bold;
            position: absolute; /* Använd absolut position */
            top: 5px; /* Justera toppen för att centrera vertikalt */
            right: 5px; /* Justera höger kant för att placera längst åt höger */
        }
    </style>
</head>
<body>
<header>
    <%UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");%>


    <img src="C:\Users\paula\Desktop\KTH\Distribuerade system\DistroLab1\DistroLab1\src\main\webapp\resources\Innovania-2.png" alt="Your Logo">
    <nav>
        <ul>
            <li><a href="http://localhost:8080/">Home</a></li>
            <li><a href="http://localhost:8080/item/all">Products</a></li>
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
            <a href="#" id="toggle-bag-button" class="btn">Shopping Bag (<%=itemCount%>)</a>
    </div>

    <!-- Toggle button to show/hide shopping bag -->
    <div id="shopping-bag-container">
        <div class="shopping-bag-title">Shopping Bag </div>
        <% if (session.getAttribute("shoppingBag") != null){
            for (ItemDTO item : ((List<ItemDTO>) session.getAttribute("shoppingBag"))) { %>

        <form action="shoppingbag/remove" method="post">
            <input type="hidden" name="itemId" value="<%= item.getId() %>">
            <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
            <input type="hidden" name="itemDesc" value="<%= item.getDesc() %>">
            <input type="hidden" name="itemPrice" value="<%= item.getPrice() %>">
            <input type="hidden" name="itemQuantity" value="<%= item.getQuantity() %>">

            <div class="shopping-bag-item">
                <div class="remove-item-button" data-item-id="<%= item.getId() %>">X</div>
            <%=item.getItemName()%>
            <%=item.getPrice()%> kr
            <input type="submit" class="remove-item-button" value="X">
        </div>
        </form>
        <% }
        } %>
    </div>

    <script>
        const shoppingBag = document.getElementById("shopping-bag-container");
        const toggleButton = document.getElementById("toggle-bag-button");
        const removeButton = document.getElementById("remove-item-button");

        toggleButton.addEventListener("click", () => {
            if (shoppingBag.style.display === "none" || shoppingBag.style.display === "") {
                shoppingBag.style.display = "block";
            } else {
                shoppingBag.style.display = "none";
            }
        });
    </script>
</header>

