<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <c:url value="/rooms/${room.id}" var="url" />
                    <a href="<c:url value='/rooms/${room.id}' />" class="btn btn-primary">Cập nhật</a>
                    <button onclick="deleteRoom('${url}', ${room.id})" class="btn btn-danger">Xóa</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-success" href="<c:url value='/rooms' />">Thêm phòng</a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function deleteRoom(url, roomId) {
        if (confirm('Bạn có chắc muốn xóa phòng này không?')) {
            $.ajax({
                url: url,
                type: 'DELETE',
                success: function(response) {
                    // Xử lý khi xóa thành công (nếu cần)
                    window.location.reload(); // Reload trang sau khi xóa
                },
                error: function(xhr) {
                    // Xử lý khi xóa thất bại (nếu cần)
                    alert('Đã xảy ra lỗi khi xóa phòng!');
                }
            });
        }
    }
</script>
</body>
</html>
