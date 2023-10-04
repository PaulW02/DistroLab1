<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<jsp:include page="header.jsp" />
<br/>
<%
    String userid = request.getParameter("uname");
    String pwd = request.getParameter("pass");
    if (pwd != null && userid != null) {
        session.setAttribute("userid", userid);%>
Logged in as <%= userid %>
<% } else { %>
<form method="post" action="session/login">
    <table border="1">
        <tbody>
        <tr>
            <td>User Name</td>
            <td><input type="text" name="uname" value=""/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="pass" value=""/>
        <tr>
            <td><input type="submit" value="Login"/></td>
        </tr>
        </tbody>
    </table>
</form>
<% } %>
</body>
</html>
