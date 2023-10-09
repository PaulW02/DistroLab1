<%@ page import="kth.distrolab1.ui.dtos.UserDTO" %>
<%@ page import="kth.distrolab1.ui.dtos.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <style>
        /* General styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }

        /* Header styles */
        header {
            background-color: #007bff; /* Primary color from the previous pages */
            color: #fff; /* White text color */
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-family: 'Arial', sans-serif;
            max-width: 100%;
            overflow: hidden;
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
            color: #fff;
            font-weight: 600;
            font-size: 16px;
            transition: color 0.3s;
        }

        nav a:hover {
            color: #FF5733; /* Highlight color on hover */
        }

        /* Button Styles */
        .btn {
            background-color: #FF5733; /* Highlight color matching the previous pages */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: 600;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #FF7542; /* Slight color change on hover */
        }

        /* Style for the shopping bag button */
        #toggle-bag-button {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-decoration: none;
            cursor: pointer;
            color: #fff; /* White text color */
            transition: color 0.3s;
        }

        .shopping-bag-icon {
            font-size: 24px;
            position: relative;
            cursor: pointer;
            transition: color 0.3s;
        }

        .shopping-bag-counter {
            font-size: 14px;
            position: absolute;
            top: -10px;
            right: -15px;
            background-color: #FF5733; /* Red circle color */
            color: #fff; /* White text color */
            border-radius: 50%; /* Make it a circle */
            width: 24px;
            height: 24px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Shopping bag dropdown container */
        #shopping-bag-container {
            display: none;
            position: fixed;
            top: 100px;
            right: 10px;
            width: 300px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            padding: 10px;
            z-index: 1000;
        }

        /* Shopping bag title style */
        .shopping-bag-title {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        /* Shopping bag items style */
        .shopping-bag-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
            border-radius: 5px;
            position: relative;
        }

        /* Remove item button style */
        .remove-item-button {
            cursor: pointer;
            color: red;
            font-weight: bold;
        }

        .logo {
            width: 200px;
            height: auto;
        }
    </style>
</head>
<%UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");%>

<%
    int itemCount = 0; // Skapar en variabel för att räkna antalet varor i shoppingbagen
    if (session.getAttribute("shoppingBag") != null) {
        itemCount = ((List<ItemDTO>) session.getAttribute("shoppingBag")).size(); // Hämtar antalet varor i shoppingbagen
    }
%>
<body>
<header>

    <img src="/resources/Innovania-white.png"
         alt="Your Logo" class="logo">
    <nav>
        <ul>
            <li><a href="http://localhost:8080/">Home</a></li>
            <li><a href="http://localhost:8080/item/all">Products</a></li>
            <% if (userDTO != null && userDTO.getRoles() != null && userDTO.getRoles().contains("role_admin")) { %>
            <li><a href="http://localhost:8080/admin/">Admin Panel</a></li>
            <% } %>
            <% if (userDTO != null && userDTO.getRoles() != null && userDTO.getRoles().contains("role_employee")) { %>
            <li><a href="/employee/">Employee Panel</a></li>
            <% } %>
            <% if (userDTO == null) { %>
            <li><a href="/login.jsp">Login</a></li>
            <li><a href="/registerUser.jsp">Register</a></li>
            <% } else { %>
            <!-- Log out button -->
            <li><a href="/session/logout">Log Out</a></li>
            <% } %>
        </ul>
    </nav>

    <div>
        <!-- Shopping bag icon with counter -->

        <div class="shopping-bag-icon" id="toggle-bag-button">
            <a style="font-size: 16px"> Shopping bag
            <i class="fas fa-shopping-bag"></i>
            <div class="shopping-bag-counter"><%= itemCount %></div> <!-- Replace '3' with the actual number of items in the bag -->
            </a>
        </div>
    </div>
    <!-- Toggle button to show/hide shopping bag -->
    <div id="shopping-bag-container">
        <div class="shopping-bag-title">Shopping Bag </div>
        <% if (session.getAttribute("shoppingBag") != null) {
            for (ItemDTO item : ((List<ItemDTO>) session.getAttribute("shoppingBag"))) { %>

        <form action="/shoppingbag/remove" method="post" enctype="multipart/form-data">
            <input type="hidden" name="itemId" value="<%= item.getId() %>">
            <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
            <input type="hidden" name="itemDesc" value="<%= item.getDesc() %>">
            <input type="hidden" name="itemCategory" value="<%= item.getCategory() %>">
            <input type="hidden" name="itemPrice" value="<%= item.getPrice() %>">
            <input type="hidden" name="itemQuantity" value="<%= item.getQuantity() %>">

            <div class="shopping-bag-item" value="">
                <div style="color: #007bff">
                <%=item.getItemName()%>
                <%=item.getPrice()%> kr
                </div>
                <input type="submit" class="remove-item-button" value="X">
            </div>
        </form>
        <% }
        } %>
        <%
            double totalPrice = 0.0; // Initialize the total price to 0.0
            if (session.getAttribute("shoppingBag") != null) {
                List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
                for (ItemDTO item : shoppingBag) {
                    totalPrice += item.getPrice(); // Add each item's price to the total
                }
            }
        %>
        <!-- Display total price -->
        <div class="total-price">
            Total Price: <%= String.format("%.2f kr", totalPrice) %>
        </div>
        <!-- Checkout button -->
        <form action="/order/purchase" method="post">
            <input type="submit" class="btn" value="Purchase">
        </form>
    </div>


    <script>
        const shoppingBag = document.getElementById("shopping-bag-container");
        const toggleButton = document.getElementById("toggle-bag-button");
        const removeButtons = document.querySelectorAll(".remove-item-button");

        toggleButton.addEventListener("click", () => {
            if (shoppingBag.style.display === "none" || shoppingBag.style.display === "") {
                shoppingBag.style.display = "block";
            } else {
                shoppingBag.style.display = "none";
            }
        });

        removeButtons.forEach((button) => {
            button.addEventListener("click", (e) => {
                e.stopPropagation(); // Prevent click from closing the dropdown
                // Implement your remove item logic here
                // You can use e.target to identify the clicked item
            });
        });
    </script>
</header>
</body>
