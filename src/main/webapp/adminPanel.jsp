<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
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
