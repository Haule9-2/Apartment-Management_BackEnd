<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Rooms for Floor ${floor.id}</title>
</head>
<body>
<h1>Rooms for Floor ${floor.name}</h1>
<table>
    <thead>
    <tr>
        <th>Room Number</th>
        <th>Price</th>
        <th>Maximum Capacity</th>
        <th>Description</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${floor.rooms}" var="room">
        <tr>
            <td>${room.number}</td>
            <td>${room.price}</td>
            <td>${room.maximum}</td>
            <td>${room.description}</td>
            <td>${room.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
