<%--
  Created by IntelliJ IDEA.
  User: HauLe
  Date: 6/20/2024
  Time: 3:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Roommates List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Add your custom styles here */
    </style>
</head>
<body>
<div class="container">
    <h2>Roommates List</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Roommate ID</th>
            <th>Resident Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Iterate over the list of roommates -->
        <c:forEach items="${roommates}" var="roommate">
            <tr>
                <td>${roommate.id}</td>
                <td>${roommate.resident.name}</td> <!-- Assuming residentId has a 'name' property -->
                <td>
                    <!-- Example link to delete roommate -->
                    <a href="<c:url value='/roommates/delete/${roommate.id}' />" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="<c:url value='/rooms' />" class="btn btn-primary">Back to Rooms</a>
</div>
</body>
</html>
