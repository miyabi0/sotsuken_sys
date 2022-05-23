<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.sotsuken_sys.entity.SPBean,java.util.List" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>卒業研究@njb</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <script type="text/javascript">
        function menuClick(operation) {
            document.forms["menu-form"].elements["operation"].value = operation;
            document.forms["menu-form"].submit();
        }
    </script>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand">卒業研究@njb</a>
        <form class="d-flex mt-3" action="./FrontControllerServlet" method="post">
            <input type="hidden" name="operation" value="logout">
            <input type="submit" class="btn btn-primary" value="ログアウト">
        </form>
    </div>
</nav>
<div class="py-5 d-grid gap-2 col-4 mx-auto my-5">
    <div class="container">
            <form action="./FrontController" method="post" name="menu-form">
                    <div class="shadow-sm card">
                        <div class="card-body">
                            <input type="button" class="form-control mb-3 btn btn-lg btn-secondary" value="卒業研究登録" onclick="menuClick('');">
                            <input type="button" class="form-control btn btn-lg btn-secondary" value="卒業研究検索" onclick="menuClick('search');">
                            <input type="hidden" name="operation">
                        </div>
                    </div>
            </form>
    </div>
</div>
<script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</body>
</html>
