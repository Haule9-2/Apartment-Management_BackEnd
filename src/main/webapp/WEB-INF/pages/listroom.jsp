<%--
  Created by IntelliJ IDEA.
  User: HauLe
  Date: 6/13/2024
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Rental Contracts</title>
  <!-- Include necessary CSS and JavaScript libraries -->
</head>
<body>
<h2>Rental Contracts</h2>
<table border="1">
  <thead>
  <tr>
    <th>ID</th>
    <th>Room Number</th>
    <th>Start Date</th>
    <th>Rent Amount</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${contracts}" var="contract">
    <tr>
      <td>${contract.id}</td>
      <td>${contract.roomNumber}</td>
      <td>${contract.startDate}</td>
      <td>${contract.rentAmount}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
