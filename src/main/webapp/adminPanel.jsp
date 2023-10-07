<%@ page import="kth.distrolab1.ui.dtos.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            flex-direction: column; /* Stack children vertically */
            align-items: center;
        }

        .container {
            max-width: 1400px; /* Increase the max-width for a wider content */
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            display: flex; /* Use flexbox */
            flex-direction: column; /* Stack children vertically */
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        .admin-form h1 {
            font-size: 24px;
            margin-top: 20px;
        }

        .admin-form label {
            font-weight: bold;
            width: 150px; /* Fixed width for labels */
            display: inline-block;
        }

        .admin-form select,
        .admin-form input[type="text"],
        .admin-form input[type="password"],
        .admin-form input[type="email"],
        .admin-form input[type="number"],
        .admin-form textarea {
            width: 75%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .admin-form input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .admin-form input[type="file"] {
            padding: 5px;
        }

        .admin-form .roles-checkbox {
            display: inline-block;
            margin-right: 10px;
        }

        .admin-form .admin-header-button {
            background-color: #007bff;
            color: white;
            padding: 50px 50px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
            min-width: 800px;
        }

        .admin-form .admin-header-button:hover {
            background-color: #0056b3;
        }
        .header {
            text-align: center;
        }

        .header img {
            max-width: 100%;
        }
    </style>
</head>
<div class="header" style="width: 100%">
    <jsp:include page="header.jsp" />
</div>
<body>
<br>
<br>
<br>
<div class="container">
    <div class="admin-form" style="width: 800px">
        <h1>Register User</h1>
        <form method="post" action="/session/register" onsubmit="prepareRoles()">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" name="email" required>
            </div>
            <div class="form-group">
                <label for="fullname">Full Name:</label>
                <input type="text" class="form-control" name="fullname" required>
            </div>
            <div class="form-group">
                <label for="roles">Roles:</label>
                <br>
                <div>
                    <input type="checkbox" class="roles-checkbox" name="roles" value="role_user" id="userRole"> User
                    <input type="checkbox" class="roles-checkbox" name="roles" value="role_employee" id="employeeRole"> Employee
                    <input type="checkbox" class="roles-checkbox" name="roles" value="role_admin" id="adminRole"> Admin
                </div>
                <br>
            </div>
            <div class="form-group">
                <input type="hidden" name="selectedRoles" id="selectedRoles" value="">
                <input type="submit" class="btn btn-primary" value="Register">
            </div>
        </form>
    </div>

    <div class="admin-form" style="width: 800px">
        <h1>Add Item</h1>
        <form action="/item/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="category">Category:</label>
                <select class="form-control" id="category" name="category" required>
                    <option value="Clothes">Clothes</option>
                    <option value="Electronics">Electronics</option>
                    <option value="Food">Food</option>
                </select>
            </div>
            <div class="form-group">
                <label for="itemName">Item Name:</label>
                <input type="text" class="form-control" id="itemName" name="itemName" required>
            </div>
            <div class="form-group">
                <label for="desc">Description:</label>
                <textarea class="form-control" id="desc" name="desc" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" class="form-control" id="price" name="price" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="number" class="form-control" id="quantity" name="quantity" required>
            </div>
            <div class="form-group">
                <label for="itemImage">Item Image:</label>
                <input type="file" class="form-control-file" id="itemImage" name="itemImage">
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Create Item">
            </div>
        </form>
    </div>

    <div class="admin-form" style="width: 800px">
        <h1>Edit Item</h1>
        <form action="/item/edit" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="item">Select an Item:</label>
                <select class="form-control" id="item" name="item" required>
                    <option value="">Select an item</option>
                    <% if (request.getAttribute("items") != null){
                        List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
                        for (ItemDTO item : items) { %>
                    <option value="<%= item.getId() %>"><%= item.getItemName() %></option>
                    <% } %>
                    <% } %>
                </select>
            </div>
            <input type="hidden" class="form-control" id="editItemId" name="editItemId" value="">
            <div class="form-group">
                <label for="editItemName">Item Name:</label>
                <input type="text" class="form-control" id="editItemName" name="editItemName">
            </div>
            <div class="form-group">
                <label for="editItemDescription">Description:</label>
                <textarea class="form-control" id="editItemDescription" name="editItemDescription" rows="4"></textarea>
            </div>
            <div class="form-group">
                <label for="editItemCategory">Category:</label>
                <select class="form-control" id="editItemCategory" name="editItemCategory">
                    <option value="">Select a category</option>
                    <option value="Clothes">Clothes</option>
                    <option value="Electronics">Electronics</option>
                    <option value="Food">Food</option>
                </select>
            </div>
            <div class="form-group">
                <label for="editItemPrice">Price:</label>
                <input type="number" class="form-control" id="editItemPrice" name="editItemPrice" step="0.01">
            </div>
            <div class="form-group">
                <label for="editItemQuantity">Quantity:</label>
                <input type="number" class="form-control" id="editItemQuantity" name="editItemQuantity">
            </div>
            <div class="form-group">
                <label for="editItemImageData">Edit Item Image:</label>
                <input type="file" class="form-control-file" id="editItemImageData" name="editItemImageData">
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Edit Item">
            </div>
        </form>
    </div>
</div>
<script>
    function prepareRoles() {
        // Get all selected roles
        var selectedRoles = document.querySelectorAll('input[name="roles"]:checked');

        // Extract role values into a comma-separated string
        var selectedRolesString = Array.from(selectedRoles).map(role => role.value).join(',');
        selectedRolesString = selectedRolesString + ",";
        // Set the selectedRoles input field value
        document.getElementById('selectedRoles').value = selectedRolesString;
    }
</script>
</body>
</html>
