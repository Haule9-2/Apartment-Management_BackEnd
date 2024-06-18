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
        /* Global styles */
        .x_panel {
            padding: 20px;
            margin-bottom: 20px;
            background: #F7F7F7;
        }
        .x_title {
            margin-bottom: 20px;
            font-size: 15px;
        }
        .thumbnail {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #fff;
            margin-bottom: 20px;
        }
        .panel_toolbox {
            float: right;
        }
        .clearfix {
            clear: both;
        }

        /* Specific styles for elements */
        #lblError,
        #lblSuccess {
            color: red;
            margin-bottom: 15px;
        }
        .select2,
        .form-control {
            width: 100%;
        }
        .navbar-right.panel_toolbox li {
            display: inline-block;
            margin-left: 5px;
        }

        /* Media queries for responsive design if needed */
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
        <div id="lblError"></div>
        <div id="lblSuccess"></div>
        <div class="col-12">
            <div class="x_panel">
                <div class="x_title">
                    <div class="row">
                        <div class="col-12">
                            <h4>Danh sách phòng</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2 col-sm-4 col-12">
                            <select id="statusRoom" class="select2 form-control">
                                <option value="-1" selected> - Trạng thái phòng - </option>
                                <option value="1">Còn trống</option>
                                <option value="2">Đã cho thuê</option>
                            </select>
                        </div>
                        <div class="col-md-2 col-sm-4 col-12">
                            <input type="text" id="roomName" name="roomName" placeholder="Phòng" class="form-control">
                        </div>
                        <div class="col-md-1 col-sm-4 col-12">
                            <button class="btn btn-primary" id="searchButton"><i class="fa fa-search"></i> Tìm kiếm</button>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <span class="pr-2 border-right" id="numberEmtyRoomMain">Còn trống 4</span>
                        <span class="px-2 border-right" id="numberRoomThueMain">Đã cho thuê 1</span>
                        <span class="px-2" id="numberRoomNotPaymentMain">Chưa thu phí 0</span>
                        <ul class="nav navbar-right panel_toolbox">
                            <li><button class="btn btn-warning" id="importRoom" data-toggle="modal" data-target="#modal"><i class="fa fa-upload"></i> Nhập phòng từ excel</button></li>
                            <li><button class="btn btn-primary" onclick="window.location.href='/Room/Customer'" id="updateCustomer"><i class="fa fa-users"></i> Khách thuê</button></li>
                            <li><button class="btn btn-info" onclick="window.location.href='/Room/ListRoom'" id="updateListRoom"><i class="fa fa-list-ol"></i> Phòng</button></li>
                            <li><button class="btn btn-success" onclick="window.location.href='/Area/Create'" id="addArea"><i class="fa fa-university"></i> Thêm nhà</button></li>
                        </ul>
                    </div>
                </div>
                <c:forEach items="${floors}" var="floor">
                    <tr>
                        <td>${floor.name}</td>
                        <td>
                            <a href="<c:url value='/floor/${floor.id}' />" class="btn btn-primary">Xem chi tiết</a>
                        </td>
                    </tr>
                </c:forEach>
                <div class="x_content" id="contentArea">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs" role="tablist">
                            <c:forEach items="${floors}" var="floor">
                                <li role="presentation" class="${floor.id == floors[0].id ? 'active' : ''}">
                                    <a href="#${floor.id}_Area" data-toggle="tab">${floor.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <c:forEach items="${floors}" var="floor">
                                <div class="tab-pane ${floor.id == floors[0].id ? 'active' : ''}" id="${floor.id}_Area">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h4 style="display:inline-block"><strong>${floor.name}</strong></h4>
                                            <ul class="nav navbar-right panel_toolbox">
                                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">
                                            <div class="row">
                                                <div class="col-12">
                                                    <span class="pr-2 border-right" name="numberEmtyRoom">Còn trống ${floor.emptyRooms}</span>
                                                    <span class="px-2 border-right" name="numberRoomThue">Đã cho thuê ${floor.rentedRooms}</span>
                                                    <span class="px-2" name="numberRoomNotPayment">Chưa thu phí ${floor.unpaidRooms}</span>
                                                    <ul class="nav navbar-right panel_toolbox">
                                                        <li><button class="btn btn-info" name="addFastRoom" disabled><i class="fa fa-plus"></i> Thêm phòng nhanh</button></li>
                                                        <li><button class="btn btn-success" name="addRoom" disabled><i class="fa fa-bed"></i> Thêm phòng</button></li>
                                                        <li><button class="btn btn-primary" name="editArea"><i class="fa fa-pencil"></i> Sửa nhà</button></li>
                                                        <li><button class="btn btn-danger" name="deleteArea"><i class="fa fa-close"></i> Xóa nhà</button></li>
                                                    </ul>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <c:forEach items="${rooms}" var="room">
                                                    <c:if test="${room.floor.id == floor.id}">
                                                        <div class="col-md-55 room-item" id="${room.id}_Room">
                                                            <div class="thumbnail bg-lightblue">
                                                                <div class="d-flex">
                                                                    <div class="w-60">
                                                                        <strong><i class="fa fa-home"></i> <span>${room.number}</span></strong>
                                                                    </div>
                                                                    <div class="w-40 text-right">
                                                                        <button class="btn btn-success btn-sm" name="paymentRoom" title="Trả"><i class="fa fa-undo"></i></button>
                                                                        <button class="btn btn-warning btn-sm" name="changeRoom" title="Đổi"><i class="fa fa-retweet"></i></button>
                                                                        <button class="btn btn-dark btn-sm" name="watchCustomer" title="Xem"><i class="fa fa-eye"></i></button>
                                                                        <button class="btn btn-info btn-sm" name="editCustomer" title="Sửa"><i class="fa fa-edit"></i></button>
                                                                    </div>
                                                                </div>
                                                                <div class="row mt-2">
                                                                    <p>Trạng thái: <span class="badge badge-warning">${room.status}</span></p>
                                                                    <p>Ngày tạo: ${room.creationDate}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Import Rooms from Excel</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="excelFile">Chọn file Excel</label>
                        <input type="file" class="form-control-file" id="excelFile" accept=".xls,.xlsx">
                    </div>
                    <div class="form-group">
                        <label for="excelFileName">Tên file</label>
                        <input type="text" class="form-control" id="excelFileName" placeholder="Tên file">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-primary">Lưu thay đổi</button>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    // Function to filter rooms by floor
    function filterRoomsByFloor(floorId) {
        if (floorId) {
            $('.tab-pane').hide();
            $('#' + floorId + '_Area').show();
            $('.nav-tabs li').removeClass('active');
            $('a[href="#' + floorId + '_Area"]').parent().addClass('active');
        } else {
            $('.tab-pane').show();
            $('.nav-tabs li').removeClass('active');
            $('.nav-tabs li:first-child').addClass('active');
        }
    }

    // Initial page load to show all rooms
    $(document).ready(function() {
        filterRoomsByFloor('');
    });

    // Function to delete room (similar to your existing function)
    function deleteRoom(url, roomId) {
        if (confirm('Bạn có chắc muốn xóa phòng này không?')) {
            $.ajax({
                url: url,
                type: 'DELETE',
                success: function(response) {
                    window.location.reload(); // Reload page after successful delete
                },
                error: function(xhr) {
                    alert('Đã xảy ra lỗi khi xóa phòng!');
                }
            });
        }
    }
</script>
</script>
</body>
</html>
