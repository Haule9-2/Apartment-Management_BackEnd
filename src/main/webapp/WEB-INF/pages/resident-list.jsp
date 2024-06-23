<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Residents List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Optional: Font Awesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Residents List</h2>
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Resident ID</th>
            <th scope="col">Username</th>
            <th scope="col">Full Name</th>
            <th scope="col">Gender</th>
            <th scope="col">Avatar</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="resident" items="${residents}">
            <tr>
                <td>${resident.id}</td>
                <td>${resident.user.username}</td>
                <td>${resident.user.fullName}</td>
                <td>${resident.user.gender}</td>
                <td><img src="${resident.avatar}" alt="Avatar" style="width:50px; height:50px;"></td>
                <td>
                    <!-- Delete Resident Button -->
                    <button onclick="deleteResident(${resident.id})" class="btn btn-danger"><i class="fas fa-trash"></i> Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS (Optional: Include if you use Bootstrap JS components) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- jQuery (Optional: Include if you use jQuery) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<!-- JavaScript for Delete Functionality -->
<script>
    function deleteResident(residentId) {
        if (confirm("Are you sure you want to delete this resident?")) {
            $.ajax({
                type: "POST",
                url: "/resident/delete/" + residentId, // Adjust the URL based on your controller mapping
                success: function () {
                    // Reload the page after successful deletion
                    location.reload();
                },
                error: function () {
                    alert("Error deleting resident.");
                }
            });
        }
    }
</script>
</body>
</html>
