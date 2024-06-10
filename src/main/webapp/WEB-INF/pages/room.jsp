<%--
  Created by IntelliJ IDEA.
  User: Phuc
  Date: 6/8/2024
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<html>
<head>
    <title>Room Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Room Management</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Number</th>
            <th>Price</th>
            <th>Maximum</th>
            <th>Description</th>
            <th>Status</th>
            <th>Floor</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td>${room.id}</td>
                <td>${room.number}</td>
                <td>${room.price}</td>
                <td>${room.maximum}</td>
                <td>${room.description}</td>
                <td>${room.status}</td>
                <td>${room.floor.name}</td>
                <td>
                    <a class="btn btn-primary" href="<c:url value='/rooms/{roomId}' />">Cập nhật</a>
                    <a class="btn btn-danger" href="<c:url value='.....' />">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-success" href="<c:url value='/rooms' />">Thêm phòng</a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>