<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Danh sách tầng</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Danh sách tầng</h1>
    <table class="table mt-3">
        <thead class="thead-dark">
        <tr>
            <th>Tên tầng</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${floors}" var="floor">
            <tr>
                <td>${floor.name}</td>
                <td>
                    <a href="/floor/rooms?floorId=${floor.id}">Xem phòng của tầng</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id="roomsContainer" class="mt-3">
        <!-- Room list will be displayed here -->
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
