<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>卒業研究@njb</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <%
        String errorMessage = com.example.sotsuken_sys.util.AutoCast.automaticCast(session.getAttribute("errorMessage"));
    %>
</head>
<body class="bg-light text-center">
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand">卒業研究@njb</a>
    </div>
</nav>
<div class="py-5">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 g-2">
            <h3><%=errorMessage%></h3>
        </div>
    </div>
</div>
<nav class="navbar fixed-bottom bg-light border-top">
    <div class="container-fluid">
        <input type="button" class="btn btn-primary mx-auto px-5" value="戻る" onclick="history.back();">
    </div>
</nav>
<script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</body>
</html>
