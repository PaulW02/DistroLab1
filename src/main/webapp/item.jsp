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
    <title>Product Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        /* Updated item container styles */
        .item-container {
            width: 100%;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #fff;
            display: flex;
            flex-direction: column;
            align-items: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        .item-name {
            font-size: 24px;
            font-weight: bold;
            margin: 10px 0;
            text-align: center;
        }

        .item-description {
            font-size: 16px;
            margin: 10px 0;
            text-align: center;
        }

        .item-category {
            font-size: 14px;
            font-style: italic;
            color: #555;
            text-align: center;
        }

        .item-price {
            font-size: 20px;
            font-weight: bold;
            color: #007bff;
            margin: 10px 0;
        }

        .item-image {
            max-width: 100%;
            max-height: 300px;
        }

        /* Button Styles */
        .add-to-basket-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: 600;
            font-size: 16px;
            cursor: pointer;
            margin: 10px 0;
            transition: background-color 0.3s;
        }

        .item-price {
            color: #007bff;
        }
    </style>
</head>

<jsp:include page="header.jsp" />

<body>
<div class="container">
    <br />
    <% ItemDTO item = (ItemDTO) request.getAttribute("item");
        if (item != null) { %>
    <!-- Product details container -->
    <div class="item-container">
        <div class="item-name"><%= item.getItemName() %></div>
        <img class="item-image"
             src="data:image/jpeg;base64,<%= item.getImageData() %>"
             alt="Item Image" />
        <div class="item-description"><%= item.getDesc() %></div>
        <div class="item-category"><%= item.getCategory() %></div>
        <div class="item-price">Price: <%= item.getPrice() %> kr</div>
        <form action="/shoppingbag/add" method="post" enctype="multipart/form-data">
            <input type="hidden" name="itemId" value="<%= item.getId() %>">
            <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
            <input type="hidden" name="itemDesc" value="<%= item.getDesc() %>">
            <input type="hidden" name="itemCategory" value="<%= item.getCategory() %>">
            <input type="hidden" name="itemPrice" value="<%= item.getPrice() %>">
            <input type="hidden" name="itemQuantity" value="<%= item.getQuantity() %>">
            <input type="hidden" name="itemImageData" value="<%= item.getImageData() %>">
            <input type="submit" class="add-to-basket-button" value="Add to Basket">
        </form>
    </div>
    <% } %>

</div>
</body>
</html>
