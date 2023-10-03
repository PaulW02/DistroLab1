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
    </body>
</html>