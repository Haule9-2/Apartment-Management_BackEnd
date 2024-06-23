<%--
  Created by IntelliJ IDEA.
  User: HauLe
  Date: 6/12/2024
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sidebar With Bootstrap</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/css/admin.css'/>"/>

</head>

<body>
<div class="wrapper">
    <aside id="sidebar">
        <div class="d-flex">
            <button class="toggle-btn" type="button">
                <i class="lni lni-grid-alt"></i>
            </button>
            <div class="sidebar-logo">
                <a href="#">Quan ly chung cu </a>
            </div>
        </div>
        <ul class="sidebar-nav">
            <li class="sidebar-item">
                <a href="<c:url value='/dashboard' />" class="sidebar-link">
                    <i class="lni lni-user"></i>
                    <span>Thống kê </span>
                </a>
            </li>
            <li class="sidebar-item">
                <a href="<c:url value='/room' />" class="sidebar-link">
                    <i class="lni lni-apartment"></i>
                    <span>Phòng</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a href="<c:url value='/service' />" class="sidebar-link">
                    <i class="lni lni-service"></i>
                    <span>Dịch vụ</span>
                </a>

        </li>     <li class="sidebar-item">
            <a href="#" class="sidebar-link">
                <i class="lni lni-archive"></i>
                <span>Tủ đồ</span>
            </a>
        </li>
            <li class="sidebar-item">
                <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
                   data-bs-target="#auth" aria-expanded="false" aria-controls="auth">
                    <i class="lni lni-protection"></i>
                    <span>Auth</span>
                </a>
                <ul id="auth" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link">Login</a>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link">Register</a>
                    </li>
                </ul>
            </li>
            <li class="sidebar-item">
                <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
                   data-bs-target="#multi" aria-expanded="false" aria-controls="multi">
                    <i class="lni lni-layout"></i>
                    <span>Multi Level</span>
                </a>
                <ul id="multi" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse"
                           data-bs-target="#multi-two" aria-expanded="false" aria-controls="multi-two">
                            Two Links
                        </a>
                        <ul id="multi-two" class="sidebar-dropdown list-unstyled collapse">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">Link 1</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">Link 2</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="sidebar-item">
                <a href="#" class="sidebar-link">
                    <i class="lni lni-popup"></i>
                    <span>Notification</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a href="#" class="sidebar-link">
                    <i class="lni lni-cog"></i>
                    <span>Setting</span>
                </a>
            </li>
        </ul>
        <div class="sidebar-footer">
            <a href="#" onclick="document.getElementById('logoutForm').submit(); return false;" class="sidebar-link">
                <i class="lni lni-exit"></i>
                <span>Logout</span>
            </a>
        </div>

        <form id="logoutForm" action="/logout" method="post" style="display: none;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>

    </aside>
    <div class="main p-3">

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="<c:url value='/js/admin.js'/>"></script>

</body>
