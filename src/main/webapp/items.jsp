<%@ page import="kth.distrolab1.ui.dtos.UserDTO" %>
<%@ page import="kth.distrolab1.ui.dtos.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="kth.distrolab1.ui.servlets.ItemServlet" %>
<%@ page import="kth.distrolab1.bo.entities.Item" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <style>
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
        </style>

    </head>
    <jsp:include page="header.jsp" />
    <br/>
    <% if (request.getAttribute("items") != null) {
        for (ItemDTO item : ((List<ItemDTO>) request.getAttribute("items"))) { %>
    <!-- Container for each item -->
    <form action="/shoppingbag/add" method="post">
        <input type="hidden" name="itemId" value="<%= item.getId() %>">
        <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
        <input type="hidden" name="itemDesc" value="<%= item.getDesc() %>">
        <input type="hidden" name="itemCategory" value="<%= item.getCategory() %>">
        <input type="hidden" name="itemPrice" value="<%= item.getPrice() %>">
        <input type="hidden" name="itemQuantity" value="<%= item.getQuantity() %>">
        <div class="item-container">
            <div class="item-name"><a href="item/<%= item.getId() %>"><%= item.getItemName() %></a></div>
            <div class="item-description"><%= item.getDesc() %></div>
            <div class="item-category"><%= item.getCategory() %></div>
            <div class="item-price">Price: <%= item.getPrice() %> kr</div>
            <div class="item-quantity">antal: <%= item.getQuantity() %></div>
            <input type="submit" class="add-to-basket-button" value="Add to Basket">
        </div>
    </form
    <% }
    } %>
    </body>
</html>