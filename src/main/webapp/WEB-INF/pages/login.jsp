<%--
  Created by IntelliJ IDEA.
  User: HauLe
  Date: 6/10/2024
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        /* CSS to center the login form and put it inside a border */
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            border: 1px solid #ccc; /* Border color */
            padding: 20px;
            max-width: 400px; /* Adjust width as needed */
            width: 100%;
        }
    </style>

    <script>
        // JavaScript to display alert when login fails
        const urlParams = new URLSearchParams(window.location.search);
        const errorParam = urlParams.get('error');
        if (errorParam !== null && errorParam === 'true') {
            window.onload = function() {
                alert("Login failed! Please check your credentials and try again.");
            }
        }
    </script>
</head>
<body>

<div class="container">
    <div class="login-container">
        <center><h5>Login</h5></center>

        <form action="<c:url value='/login' />" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" autocomplete="current-password">
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

</body>
</html>
