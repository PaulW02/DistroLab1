<%@ page import="kth.distrolab1.ui.dtos.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<jsp:include page="header.jsp" />
<h1>Register User</h1>
<form method="post" action="session/register" onsubmit="prepareRoles()">
    <table>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" /></td>
        </tr>
        <tr>
            <td>Full Name:</td>
            <td><input type="text" name="fullname" /></td>
        </tr>

        <tr>
            <td>Roles:</td>
            <td>
                <input type="checkbox" name="roles" value="role_user"> User
                <input type="checkbox" name="roles" value="role_employee"> Employee
                <input type="checkbox" name="roles" value="role_admin"> Admin
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="hidden" name="selectedRoles" id="selectedRoles" value="">
                <input type="submit" value="Register" />
            </td>
        </tr>
    </table>
</form>

<h1>Add item</h1>
<form action="item/create" method="post">
    <label for="category">Category:</label>
    <select id="category" name="category" required>
        <option value="Clothes">Clothes</option>
        <option value="Electronics">Electronics</option>
        <option value="Food">Food</option>
    </select>
    <br><br>

    <label for="itemName">Item Name:</label>
    <input type="text" id="itemName" name="itemName" required>
    <br><br>

    <label for="desc">Description:</label>
    <textarea id="desc" name="desc" rows="4" cols="50"></textarea>
    <br><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" step="0.01" required>
    <br><br>

    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required>
    <br><br>

    <input type="submit" value="Create Item">
</form>

<h1>Edit Item</h1>
<form action="/item/edit" method="post">
    <!-- Select dropdown for item names -->
    <label for="item">Select an Item:</label>
    <select id="item" name="item" required>

        <option value="">Select an item</option>
        <% if (request.getAttribute("items") != null){
            List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
            for (ItemDTO item : items) { %>
            <option value="<%= item.getId() %>"><%= item.getItemName() %></option>
        <% } %>
        <% } %>
    </select>
    <br><br>

    <!-- Hidden input field to store the item ID -->
    <input type="hidden" id="editItemId" name="editItemId" value="">

    <!-- Input fields for editing existing items -->
    <label for="editItemName">Item Name:</label>
    <input type="text" id="editItemName" name="editItemName">
    <br><br>

    <label for="editItemDescription">Description:</label>
    <textarea id="editItemDescription" name="editItemDescription" rows="4" cols="50"></textarea>
    <br><br>

    <!-- Reuse the category options from the Add Item form -->
    <label for="editItemCategory">Category:</label>
    <select id="editItemCategory" name="editItemCategory">
        <option value="">Select a category</option> <!-- Add this empty option -->
        <option value="Clothes">Clothes</option>
        <option value="Electronics">Electronics</option>
        <option value="Food">Food</option>
    </select>
    <br><br>


    <label for="editItemPrice">Price:</label>
    <input type="number" id="editItemPrice" name="editItemPrice" step="0.01">
    <br><br>

    <label for="editItemQuantity">Quantity:</label>
    <input type="number" id="editItemQuantity" name="editItemQuantity">
    <br><br>

    <input type="submit" value="Edit Item">
</form>

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
