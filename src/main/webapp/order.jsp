<%@ page import="kth.distrolab1.ui.dtos.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="kth.distrolab1.ui.dtos.OrderItemDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%if (session.getAttribute("userDTO") == null) {
    response.sendRedirect("http://localhost:8080/");
}
%>
<head>
    <meta charset="UTF-8">
    <title>Order</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        h1 {
            text-align: center;
            background-color: #007bff;
            color: #fff;
            padding: 20px;
        }
        .checkout-summary {
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        h3 {
            text-align: right;
            color: #007bff;
        }
    </style>
</head>

<jsp:include page="header.jsp" />
<!-- Include your header content here -->
<body>
<div class="container">
<h1>Your Order</h1>
<%OrderDTO orderDTO = (OrderDTO) request.getAttribute("orderDTO");%>
<section class="checkout-summary">
    <h2>Order Summary</h2>
    <table>
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
        </tr>
        <br/>
        <% if (request.getAttribute("orderDTO") != null) {
            for (OrderItemDTO orderItemDTO : orderDTO.getItemsBought()) { %>
        <tr>
            <td><%=orderItemDTO.getItemName()%></td>
            <td><%=orderItemDTO.getTotalPrice() / orderItemDTO.getQuantity()%></td>
            <td><%=orderItemDTO.getQuantity()%></td>
            <td><%=orderItemDTO.getTotalPrice()%></td>
        </tr>
        <%}
        }%>
    </table>
    <h3>Total price: <%=orderDTO.getAmount()%></h3>
</section>
</div>
</body>
</html>
