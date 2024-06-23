<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Resident Details</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#loadResidentBtn").click(function() {
                var residentId = ${resident.id}; // Get resident ID from model
                $.ajax({
                    type: "GET",
                    url: "/resident/" + residentId, // Controller mapping to fetch resident details
                    success: function(data) {
                        // Update resident details section with fetched data
                        $("#residentDetails").html(data);
                    },
                    error: function(e) {
                        console.log("Error fetching resident details: " + e);
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>Resident Details</h1>

<h2>Resident Information</h2>
<ul>
    <li><strong>ID:</strong> ${resident.id}</li>
    <li><strong>Avatar:</strong> ${resident.avatar}</li>
</ul>

<h2>User Information</h2>
<ul id="residentDetails">
    <li><strong>ID:</strong> ${resident.user.id}</li>
    <li><strong>Username:</strong> ${resident.user.username}</li>
    <li><strong>Role:</strong> ${resident.user.role}</li>
    <li><strong>Full Name:</strong> ${resident.user.fullName}</li>
    <li><strong>Gender:</strong> ${resident.user.gender}</li>
    <!-- Add other user details as needed -->
</ul>

<button id="loadResidentBtn">Load Resident Details</button>
</body>
</html>
