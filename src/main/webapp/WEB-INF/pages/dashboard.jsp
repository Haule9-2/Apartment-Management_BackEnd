<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 8px;
        }
    </style>
</head>
<body>
<h1>Dashboard</h1>

<h2>Residents</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <!-- Add more columns as per your Resident entity -->
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${residents}" var="resident">
        <tr>
            <td>${resident.id}</td>
            <td>${resident.name}</td>
            <td>${resident.address}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Floors</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <!-- Add more columns as per your Floor entity -->
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${floors}" var="floor">
        <tr>
            <td>${floor.id}</td>
            <td>${floor.name}</td>
            <!-- Add more columns as per your Floor entity -->
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Rooms</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Number</th>
        <!-- Add more columns as per your Room entity -->
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${rooms}" var="room">
        <tr>
            <td>${room.id}</td>
            <td>${room.number}</td>
            <!-- Add more columns as per your Room entity -->
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
