<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <a href="<c:url value='/floor/${floor.id}' />" class="btn btn-primary">Xem chi tiết</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-success" >Thêm tầng</a>
</div>
