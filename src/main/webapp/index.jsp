<%@ page import="kth.distrolab1.ui.UserDTO" %>
<%@ page import="kth.distrolab1.ui.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="kth.distrolab1.bo.servlets.ItemServlet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <style>
            /* Style for the shopping bag button */
            #toggle-bag-button {
                position: fixed;
                top: 10px;
                right: 10px;
                cursor: pointer;
                background-color: lightgray;
                padding: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            /* Style for the shopping bag container */
            #shopping-bag-container {
                display: none;
                position: fixed;
                top: 40px; /* Adjust the top position to place it below the button */
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
                margin-bottom: 10px;
                padding: 5px;
                border: 1px solid #ddd;
            }

            /* Style for individual item boxes */
            .item-container {
                width: 200px; /* Set the width to control the size of the square */
                height: 200px; /* Set the height to match the width for a square */
                padding: 10px;
                border: 1px solid #000000;
                margin-bottom: 10px;
                float: left; /* Float the boxes to align them horizontally */
                margin-right: 20px; /* Add margin for spacing between boxes */
            }
            /* Style for the "Add to Basket" button */
            .add-to-basket-button {
                display: block;
                margin-top: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
            }
        </style>
        <%UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");%>
        <% if (userDTO != null && userDTO.getRoles() != null && userDTO.getRoles().contains("role_admin")) {%>
        <a href="adminPanel.jsp">Only For Admins</a>

        <%}%>
        <% if (userDTO == null) {%>
        <a href="login.jsp">Login</a>
        <a href="registerUser.jsp">Register</a>
        <%} else{%>
        <!-- Log out button -->
        <form action="session/logout" method="post">
            <input type="submit" value="Log Out">
        </form>
        <%}%>
    </head>
    <body>


        <h1>
            Welcome <% if (userDTO != null && userDTO.getFullname() != null){ %> <%= userDTO.getFullname() %> <% } else { %> stranger <%}%>
        </h1>
        <br/>


        <% if (request.getAttribute("items") != null) {
            for (ItemDTO item : ((List<ItemDTO>) request.getAttribute("items"))) { %>
        <!-- Container for each item -->
        <form action="shoppingbag/add" method="post">
            <input type="hidden" name="itemId" value="<%= item.getId() %>">
            <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
            <input type="hidden" name="itemDesc" value="<%= item.getDesc() %>">
            <input type="hidden" name="itemPrice" value="<%= item.getPrice() %>">
            <input type="hidden" name="itemQuantity" value="<%= item.getQuantity() %>">
            <div class="item-container">
                <div class="item-name"><a href="item/<%= item.getId() %>"><%= item.getItemName() %></a></div>
                <div class="item-description"><%= item.getDesc() %></div>
                <div class="item-price">Price: <%= item.getPrice() %> kr</div>
                <div class="item-quantity">antal: <%= item.getQuantity() %></div>
                <input type="submit" class="add-to-basket-button" value="Add to Basket">
            </div>
        </form>

        <% }
        } %>
        <%if (session.getAttribute("shoppingBag") != null){
        for (ItemDTO item : ((List<ItemDTO>) session.getAttribute("shoppingBag"))) { %>
        <%=item.getItemName()%>

        <% }
        } %>

        <!-- Toggle button to show/hide shopping bag -->
        <div id="toggle-bag-button">Shopping Bag</div>

        <!-- Shopping bag container -->
        <div id="shopping-bag-container">
            <div class="shopping-bag-title">Shopping Bag</div>
            <div class="shopping-bag-item">
                <!-- Add your shopping bag items here -->
            </div>
            <!-- You can add more shopping bag items as needed -->
        </div>

        <!-- JavaScript to toggle the shopping bag visibility -->
        <script>
            const shoppingBag = document.getElementById("shopping-bag-container");
            const toggleButton = document.getElementById("toggle-bag-button");

            toggleButton.addEventListener("click", () => {
                if (shoppingBag.style.display === "none" || shoppingBag.style.display === "") {
                    shoppingBag.style.display = "block";
                } else {
                    shoppingBag.style.display = "none";
                }
            });
        </script>
    </body>
</html>