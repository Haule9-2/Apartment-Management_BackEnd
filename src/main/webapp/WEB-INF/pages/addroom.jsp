<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Phòng Mới</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center text-info">THÊM PHÒNG MỚI</h1>
    <c:url value="/rooms" var="action" />

    <form:form method="post" action="${action}" modelAttribute="room">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />

        <div class="form-group">
            <label for="number">Số Phòng:</label>
            <form:input class="form-control" id="number" path="number" required="true"/>
            <form:errors path="number" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="price">Giá:</label>
            <form:input class="form-control" id="price" path="price" required="true"/>
            <form:errors path="price" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="maximum">Số Người Tối Đa:</label>
            <form:input class="form-control" id="maximum" path="maximum"/>
            <form:errors path="maximum" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="description">Mô Tả:</label>
            <form:textarea class="form-control" id="description" path="description"/>
            <form:errors path="description" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="status">Trạng Thái:</label>
            <form:input class="form-control" id="status" path="status" required="true"/>
            <form:errors path="status" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="floor">Tầng:</label>
            <form:select class="form-control" id="floor" path="floor.id" required="true">
                <form:option value="" label="Chọn tầng"/>
                <form:options items="${floors}" itemValue="id" itemLabel="name"/>
            </form:select>
            <form:errors path="floor.id" cssClass="text-danger"/>
        </div>
        <div class="form-floating">
            <button class="btn btn-info mt-1" type="submit">
                <c:choose>
                    <c:when test="${room.id != null}"> Cập Nhật Phòng</c:when>
                    <c:otherwise> Thêm Phòng</c:otherwise>
                </c:choose>
            </button>
            <form:hidden path="id"/>
        </div>
    </form:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    // Disable form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>