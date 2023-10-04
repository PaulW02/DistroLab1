<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<jsp:include page="header.jsp" />
<h1>Register</h1>
<form method="post" action="session/register">
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
            <td colspan="2">
                <input type="submit" value="Register" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
