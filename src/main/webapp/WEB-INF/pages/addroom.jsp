<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<body>
<div class="container mt-5">
    <h1 class="text-center text-info">QUẢN LÝ PHÒNG</h1>
    <c:url value="/rooms" var="action" />

    <form:form method="post" action="${action}" modelAttribute="rooms">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />

        <div class="form-group">
            <label for="number">Số Phòng:</label>
            <input type="text" class="form-control" id="number" placeholder="Nhập số phòng" name="number" required>
            <div class="invalid-feedback">Vui lòng nhập số phòng.</div>
        </div>
        <div class="form-group">
            <label for="price">Giá:</label>
            <input type="number" class="form-control" id="price" placeholder="Nhập giá" name="price" required>
            <div class="invalid-feedback">Vui lòng nhập giá.</div>
        </div>
        <div class="form-group">
            <label for="maximum">Số người tối đa:</label>
            <input type="number" class="form-control" id="maximum" placeholder="Nhập số người tối đa" name="maximum">
        </div>
        <div class="form-group">
            <label for="description">Mô tả:</label>
            <textarea class="form-control" id="description" placeholder="Nhập mô tả" name="description"></textarea>
        </div>
        <div class="form-group">
            <label for="floor">Tầng:</label>
            <select class="form-control" id="floor" name="floor.id" required>
                <c:forEach items="${floors}" var="floor">
                    <option value="${floor.id}">${floor.name}</option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">Vui lòng chọn tầng.</div>
        </div>
        <button type="submit" class="btn btn-primary">Thêm phòng</button>
    </form:form>
</div>
</body>
</html>