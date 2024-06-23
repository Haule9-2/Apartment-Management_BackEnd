<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý dịch vụ</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center text-info">QUẢN LÝ DỊCH VỤ</h1>
    <c:url value="/services" var="action" />

        <form:form method="post" action="${action}" modelAttribute="services">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />

        <div class="form-group">
            <label for="name">Tên dịch vụ:</label>
            <form:input class="form-control" id="name" name="name" path="name" />
        </div>
        <div class="form-group">
            <label for="description">Mô tả:</label>
            <form:textarea class="form-control" id="description" path="description" />
        </div>
        <div class="form-group">
            <label for="price">Giá:</label>
            <form:input class="form-control" id="price" name="price" path="price" />
        </div>
        <div class="form-floating">
            <button class="btn btn-info mt-1" type="submit">
                <c:choose>
                    <c:when test="${services.id > 0}"> Cập nhật sản phẩm</c:when>
                    <c:otherwise> Thêm sản phẩm</c:otherwise>
                </c:choose>
            </button>
            <form:hidden path="id" />
        </div>
    </form:form>
</div>
</body>
</html>