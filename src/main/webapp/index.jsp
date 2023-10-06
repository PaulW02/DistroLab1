<%@ page import="kth.distrolab1.ui.dtos.UserDTO" %>
<%@ page import="kth.distrolab1.ui.dtos.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="kth.distrolab1.ui.servlets.ItemServlet" %>
<%@ page import="java.util.Base64" %>
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
            .item-image {
                max-width: 150px;
                max-height: 60px;
                display: block;
                margin: 0 auto;
                margin-bottom: 10px;
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

    </head>

    <jsp:include page="header.jsp" />

        <h1>
            Welcome <% if (userDTO != null && userDTO.getFullname() != null){ %> <%= userDTO.getFullname() %> <% } else { %> stranger <%}%>
        </h1>
        <br/>

        <% if (request.getAttribute("items") != null) {%>
        <%List<ItemDTO> items = ((List<ItemDTO>) request.getAttribute("items"));%>
        <%for (ItemDTO item : items) {%>
        <!-- Container for each item -->
    <form action="/shoppingbag/add" method="post" enctype="multipart/form-data">
    <input type="hidden" name="itemId" value="<%= item.getId() %>">
            <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
            <input type="hidden" name="itemDesc" value="<%= item.getDesc() %>">
            <input type="hidden" name="itemCategory" value="<%= item.getCategory() %>">
            <input type="hidden" name="itemPrice" value="<%= item.getPrice() %>">
            <input type="hidden" name="itemQuantity" value="<%= item.getQuantity() %>">
            <input type="hidden" name="itemItem" value="<%= item.getQuantity() %>">
            <input type="hidden" name="itemImageData" value="<%= item.getImageData() %>">
            <div class="item-container">
                <div class="item-name"><a href="item/<%= item.getId() %>"><%= item.getItemName() %></a></div>
                <div class="item-description"><%= item.getDesc() %></div>
                <div class="item-category"><%= item.getCategory() %></div>
                <div class="item-price">Price: <%= item.getPrice() %> kr</div>
                <% if(item.getImageData() != null){%>
                <img class="item-image" src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(item.getImageData()) %>" alt="Item Image" />
                <%}%>
                <input type="submit" class="add-to-basket-button" value="Add to Basket">
            </div>
        </form>
        <% }
        } %>

    </body>
</html>