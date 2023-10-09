<%@ page import="kth.distrolab1.ui.dtos.UserDTO" %>
<%@ page import="kth.distrolab1.ui.dtos.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="kth.distrolab1.ui.servlets.ItemServlet" %>
<%@ page import="kth.distrolab1.bo.entities.Item" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <style>
            /* General page styles */
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                margin: 0;
                padding: 0;
            }


            /* Container Styles */
            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            h1 {
                font-size: 28px;
                text-align: center;
            }
            .welcome {
                font-size: 18px;
                text-align: center;
            }

            /* Style for individual item boxes */
            .item-container {
                width: 220px;
                height: 260px;
                padding: 10px;
                border: 1px solid #ddd;
                background-color: #fff;
                margin: 10px;
                text-align: center;

            }

            .item-container-wrapper {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
            }

            .item-image {
                max-width: 150px;
                max-height: 150px;
                display: block;
                margin: 0 auto;
                margin-bottom: 10px;
            }
            .item-name {
                font-weight: bold;
            }
            .item-description {
                font-size: 14px;
                margin-bottom: 10px;
            }
            .item-category {
                font-style: italic;
                color: #555;
            }
            .item-price {
                font-weight: bold;
                color: #007bff;
            }
            /* Style for the "Add to Basket" button */
            .add-to-basket-button {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
            }
        </style>

    </head>
    <jsp:include page="header.jsp" />
    <body>
    <br/>
    <div class="container">
        <div class="item-container-wrapper">
            <% if (request.getAttribute("items") != null) {
                for (ItemDTO item : ((List<ItemDTO>) request.getAttribute("items"))) { %>
            <!-- Container for each item -->
            <form action="/shoppingbag/add" method="post" enctype="multipart/form-data">
            <input type="hidden" name="itemId" value="<%= item.getId() %>">
                <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
                <input type="hidden" name="itemDesc" value="<%= item.getDesc() %>">
                <input type="hidden" name="itemCategory" value="<%= item.getCategory() %>">
                <input type="hidden" name="itemPrice" value="<%= item.getPrice() %>">
                <input type="hidden" name="itemQuantity" value="<%= item.getQuantity() %>">
                <input type="hidden" name="itemImageData" value="<%= item.getImageData() %>">
                <div class="item-container">
                    <div class="item-name"><a href="/item/<%= item.getId() %>"><%= item.getItemName() %></a></div>
                    <div class="item-description"><%= item.getDesc() %></div>
                    <div class="item-category"><%= item.getCategory() %></div>
                    <div class="item-price">Price: <%= item.getPrice() %> kr</div>
                    <% if(item.getImageData() != null){%>
                    <img class="item-image" src="data:image/jpeg;base64,<%= item.getImageData() %>" alt="Item Image" />
                    <%}%>
                    <input type="submit" class="add-to-basket-button" value="Add to Basket">
                </div>
            </form
            <% }
            } %>
        </div>
    </div>
    </body>
</html>