<%@ page import="kth.distrolab1.ui.dtos.OrderDTO" %>
<%@ page import="kth.distrolab1.ui.dtos.OrderStatusDTO" %>
<%@ page import="kth.distrolab1.ui.dtos.OrderItemDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Panel</title>
    <style>
        /* General page styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f8fa;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        /* Header styles */
        h1 {
            background-color: #007BFF;
            color: #fff;
            padding: 20px;
            text-align: center;
        }

        /* Table styles */
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #007BFF;
            color: #fff;
        }

        /* Accordion styles */
        .accordion {
            background-color: #007BFF;
            color: #fff;
            cursor: pointer;
            padding: 10px;
            width: 100%;
            text-align: left;
            border: none;
            outline: none;
            transition: 0.4s;
        }

        .accordion.active {
            background-color: #0056b3;
        }

        .panel {
            display: none;
            padding: 10px;
            background-color: #f1f1f1;
        }

        .panel ul {
            list-style-type: none;
            padding: 0;
        }

        .panel li {
            margin-bottom: 10px;
        }

        /* Send button styles */
        .send-button {
            background-color: #28a745;
            color: #fff;
            padding: 5px 10px;
            border: none;
            cursor: pointer;
        }
    </style>
    <script>
        // Function to toggle the accordion
        function toggleAccordion(id) {
            var panel = document.getElementById(id);
            var accordion = document.getElementById("accordion-" + id);
            if (panel.style.display === 'block') {
                panel.style.display = 'none';
                accordion.classList.remove("active");
            } else {
                panel.style.display = 'block';
                accordion.classList.add("active");
            }
        }
    </script>
</head>
<jsp:include page="header.jsp" />
<body>
<div class="container">
<h1>Employee Panel - Orders</h1>

<h2>Unsent Orders</h2>
<table>
    <tr>
        <th>Order ID</th>
        <th>Customer Name</th>
        <th>Order Date</th>
        <th>Total Amount</th>
        <th>Items bought</th>
        <th>Action</th>
    </tr>
    <%
        OrderStatusDTO orderStatusDTO = (OrderStatusDTO) request.getAttribute("orderStatusDTO");
        for (OrderDTO orderDTO : orderStatusDTO.getUnsentOrders()) {%>
    <tr>
        <td><%=orderDTO.getOrderId()%></td>
        <td><%=orderDTO.getUserId()%></td>
        <td><%=orderDTO.getPurchaseDate()%></td>
        <td><%=orderDTO.getAmount()%></td>
        <td>
            <button class="accordion" id="accordion-items<%=orderDTO.getOrderId()%>"
                    onclick="toggleAccordion('items<%=orderDTO.getOrderId()%>')">View Items
            </button>
            <div class="panel" id="items<%=orderDTO.getOrderId()%>">
                <ul>
                    <%
                        for (OrderItemDTO orderItem : orderDTO.getItemsBought()) {%>
                    <li><%=orderItem.getItemName()%> - Quantity: <%=orderItem.getQuantity()%></li>
                    <%}%>
                </ul>
            </div>
        </td>
        <td>
            <form method="post" action="http://localhost:8080/employee/send">
                <input type="hidden" name="orderId" value="<%=orderDTO.getOrderId()%>">
                <input class="send-button" type="submit" value="Send">
            </form>
        </td>
    </tr>
    <%}%>
</table>

<h2>Sent Orders</h2>
<table>
    <tr>
        <th>Order ID</th>
        <th>Customer Name</th>
        <th>Order Date</th>
        <th>Total Amount</th>
        <th>Items bought</th>
    </tr>
    <%
        for (OrderDTO orderDTO : orderStatusDTO.getSentOrders()) {%>
    <tr>
        <td><%=orderDTO.getOrderId()%></td>
        <td><%=orderDTO.getUserId()%></td>
        <td><%=orderDTO.getPurchaseDate()%></td>
        <td><%=orderDTO.getAmount()%></td>
        <td>
            <button class="accordion" id="accordion-items<%=orderDTO.getOrderId()%>"
                    onclick="toggleAccordion('items<%=orderDTO.getOrderId()%>')">View Items
            </button>
            <div class="panel" id="items<%=orderDTO.getOrderId()%>">
                <ul>
                    <%
                        for (OrderItemDTO orderItem : orderDTO.getItemsBought()) {%>
                    <li><%=orderItem.getItemName()%> - Quantity: <%=orderItem.getQuantity()%></li>
                    <%}%>
                </ul>
                </div>
        </td>
    </tr>
    <%}%>
</table>
</div>
</body>
</html>
