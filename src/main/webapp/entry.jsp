<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.sotsuken_sys.entity.SPBean,java.util.List" %>
<html lang="ja">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>詳細</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
</head>

<body class="bg-light">
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand">卒業研究@njb</a>
        <form class="d-flex mt-3" action="./FrontController" method="post">
            <input type="hidden" name="operation" value="logout">
            <input type="submit" class="btn btn-primary" value="ログアウト">
        </form>
    </div>
</nav>
<div class="py-5">
    <div class="container">
        <div class="card shadow-sm">
            <div class="card-body">
                <form action="./FrontController" method="post">

                </form>
            </div>
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
