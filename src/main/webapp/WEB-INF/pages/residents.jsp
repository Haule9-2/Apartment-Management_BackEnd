<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Residents</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            cursor: pointer;
        }

        .note {
            width: 100%;
        }
    </style>
    <script>
        function sortTable(column) {
            const urlParams = new URLSearchParams(window.location.search);
            const sortOrder = urlParams.get('sortOrder') === 'asc' ? 'desc' : 'asc';
            urlParams.set('sortBy', column);
            urlParams.set('sortOrder', sortOrder);
            window.location.search = urlParams.toString();
        }
    </script>
</head>
<body>
<h1>Residents</h1>

<form method="get" action="/residents">
    <table>
        <thead>
        <tr>
            <th onclick="sortTable('id')">ID</th>
            <th onclick="sortTable('username')">Username</th>
            <th>Avatar</th>
            <th>Note</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="resident" items="${residents}">
            <tr>
                <td>${resident.id}</td>
                <td>${resident.user.username}</td>
                <td><img src="${resident.avatar}" alt="Avatar" width="50" height="50"/></td>
                <td>
                    <textarea class="note" rows="3" placeholder="Add a note..."></textarea>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
