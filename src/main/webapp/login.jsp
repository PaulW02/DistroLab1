<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>Login</title>

    <style>
        /* General styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            display: flex; /* Use flexbox */
            flex-direction: column; /* Stack children vertically */
            justify-content: center;
            align-items: center;
            width: 100%
        }

        .container {
            max-width: 400px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            display: flex; /* Use flexbox */
            flex-direction: column; /* Stack children vertically */
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }


        .login-form {
            text-align: center;
        }

        .login-form input {
            width: 75%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .login-form input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;

        }

        .login-message {
            text-align: center;
        }

        /* Header styles */
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
<div class="container">

    <h1>Login</h1>
    <form class="login-form" method="post" action="session/login">
        <input type="text" name="uname" placeholder="User Name">
        <input type="password" name="pass" placeholder="Password">
        <input type="submit" value="Login">
    </form>
    <div class="login-message">
        <%
            String userid = request.getParameter("uname");
            String pwd = request.getParameter("pass");
            if (pwd != null && userid != null) {
                session.setAttribute("userid", userid);
        %>
        Logged in as <%= userid %>
        <%
        } else {
        %>
        Don't have an account? <a href="http://localhost:8080/registerUser.jsp">Sign up</a>
        <%
            }
        %>
    </div>
</div>
</body>

</html>
