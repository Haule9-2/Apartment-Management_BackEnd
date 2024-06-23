<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Room Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        .x_panel {
            padding: 20px;
            margin-bottom: 20px;
            background: #F7F7F7;
        }
        .x_title {
            max-width: 1200px;
            margin: 0 auto;
            margin-bottom: 20px;
            font-size: 15px;
        }
        .panel_toolbox {
            float: right;
        }
        #lblError,
        #lblSuccess {
            color: red;
            margin-bottom: 15px;
        }
        @media (max-width: 768px) {
            .navbar-right.panel_toolbox li {
                margin-bottom: 10px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <!-- Messages area -->
        <div id="lblError"></div>
        <div id="lblSuccess"></div>
        <div class="col-12">
            <!-- Main content panel -->
            <div class="x_panel">
                <div class="x_title">
                    <h4>Danh sách phòng</h4>
                    <div class="row">
                        <div class="col-md-2 col-sm-4 col-12">
                            <!-- Filter by room status -->
                            <select id="statusRoom" class="select2 form-control">
                                <option value="-1" selected> - Trạng thái phòng - </option>
                                <option value="1">còn trống</option>
                                <option value="2">đã cho thuê</option>
                            </select>
                        </div>
                        <div class="col-md-2 col-sm-4 col-12">
                            <!-- Search by room name -->
                            <input type="text" id="roomName" name="roomName" placeholder="Phòng" class="form-control">
                        </div>
                        <div class="col-md-1 col-sm-4 col-12">
                            <!-- Search button -->
                            <button class="btn btn-primary" id="searchButton"><i class="fa fa-search"></i> Tìm kiếm</button>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <span class="pr-2 border-right" id="numberEmtyRoomMain">Còn trống: <span id="emptyRoomCount">0</span></span>
                        <span class="px-2 border-right" id="numberRoomThueMain">Đã cho thuê: <span id="rentedRoomCount">0</span></span>

                        <ul class="nav navbar-right panel_toolbox">
                            <li><button class="btn btn-primary" onclick="window.location.href='<c:url value='/residents' />'"><i class="fa fa-users"></i> Khách thuê</button></li>
                            <li><button class="btn btn-info" onclick="window.location.href='<c:url value='/floor'/>'"><i class="fa fa-list-ol"></i> Danh sách tầng</button></li>
                        </ul>
                    </div>
                </div>
                <!-- Floor tabs and content -->
                <div class="x_content" id="contentArea">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <!-- Floor tabs -->
                        <ul class="nav nav-tabs bar_tabs" role="tablist">
                            <c:forEach items="${floors}" var="floor">
                                <li role="presentation" class="${floor.id == floors[0].id ? 'active' : ''}">
                                    <a href="#${floor.id}_Area" data-toggle="tab">${floor.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                        <!-- Floor content panels -->
                        <div id="myTabContent" class="tab-content">
                            <table class="table table-bordered mt-3">
                                <thead class="thead-light">
                                <tr>
                                    <th>ID</th>
                                    <th>Number</th>
                                    <th>Price</th>
                                    <th>Maximum</th>
                                    <th>Description</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${rooms}" var="room">
                                    <tr data-status="${room.status}">
                                        <td>${room.id}</td>
                                        <td>${room.number}</td>
                                        <td>${room.price}</td>
                                        <td>${room.maximum}</td>
                                        <td>${room.description}</td>
                                        <td class="status">${room.status}</td>
                                        <td>
                                            <c:url value="/rooms/${room.id}" var="url" />
                                            <a href="<c:url value='/rooms/${room.id}' />" class="btn btn-primary">Cập nhật</a>
                                            <button onclick="deleteRoom('${url}', ${room.id})" class="btn btn-danger">Xóa</button>
                                        </td>
                                        <td>
                                            <a href="<c:url value='/rooms/${room.id}/roommates' />" class="btn btn-info">View Roommates</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <a class="btn btn-success" href="<c:url value='/rooms' />">Thêm phòng</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        // Filter rooms based on selected status and name
        function filterRooms() {
            var selectedStatus = $('#statusRoom').val();
            var roomName = $('#roomName').val().toLowerCase();

            $('tbody tr').each(function() {
                var roomStatus = $(this).data('status').toString();
                var roomNumber = $(this).find('td:nth-child(2)').text().toLowerCase();

                var matchesStatus = (selectedStatus == -1 || roomStatus == selectedStatus);
                var matchesName = (roomName === '' || roomNumber.includes(roomName));

                if (matchesStatus && matchesName) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });

            updateRoomCounts();
        }

        // Update the room counts
        function updateRoomCounts() {
            var emptyCount = 0;
            var rentedCount = 0;

            $('tbody tr:visible').each(function() {
                var roomStatus = $(this).data('status').toString();
                if (roomStatus == '1') {
                    emptyCount++;
                } else if (roomStatus == '2') {
                    rentedCount++;
                }
            });

            $('#emptyRoomCount').text(emptyCount);
            $('#rentedRoomCount').text(rentedCount);
        }

        // Event handlers
        $('#statusRoom').on('change', filterRooms);
        $('#searchButton').on('click', filterRooms);

        // Initialize room counts on page load
        filterRooms();
    });

</script>

</body>
</html>
