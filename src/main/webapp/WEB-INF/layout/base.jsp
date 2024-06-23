<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <tiles:insertAttribute name="title"/>
    </title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<body>
<div class="wrapper">
    <!-- Sidebar -->
    <div class="sidebar">
        <tiles:insertAttribute name="sidebar"/>
    </div>

    <!-- Main content area -->

    <tiles:insertAttribute name="content"/>

</div>
</body>
</body>
</html>