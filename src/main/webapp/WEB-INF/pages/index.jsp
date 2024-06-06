<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
  <h1 class="mt-5">Trang chủ</h1>
  <!-- Ô tìm kiếm sản phẩm -->
  <div class="input-group mb-3">
    <input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm" id="searchInput">
    <div class="input-group-append">
      <button class="btn btn-outline-secondary" type="button" onclick="searchProduct()">Tìm kiếm</button>
    </div>
  </div>
  <table class="table mt-3">
    <thead class="thead-dark">
    <tr>
      <th>Tên dịch vụ</th>
      <th>Giá</th>
      <th>Mô tả</th>
      <th>Trạng thái</th>
      <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${services}" var="service">
      <tr>
        <td>${service.name}</td>
        <td>${service.price}</td>
        <td>${service.description}</td>
        <td>
          <c:choose>
            <c:when test="${service.isActive == 1}">
              Hoạt động
            </c:when>
            <c:otherwise>
              Không hoạt động
            </c:otherwise>
          </c:choose>
        </td>
        <td>
          <c:url value="/services/${service.id}" var="url" />
          <a href="<c:url value='/services/${service.id}' />" class="btn btn-primary">Cập nhật</a>
          <button onclick="deleteProduct('${url}',${service.id})" class="btn btn-danger">Xóa</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a class="btn btn-success" href="<c:url value='/services' />" >Thêm dịch vụ</a>
</div>

<script src="<c:url value="/js/service.js" />"></script>
