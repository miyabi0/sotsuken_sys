<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ログイン</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <link rel="stylesheet" href="css/login.css">
    <%
        String errorMessage = com.example.sotsuken_sys.util.AutoCast.automaticCast(session.getAttribute("errorMessage"));
    %>
</head>

<body class="text-center">
<main class="form-login w-100 m-auto">
    <form action="./FrontController" method="post">
        <img class="mb-4" src="images/logo.png" alt="" width="150">
        <h1 class="h3 mb-3 fw-normal">ログイン</h1>
        <%
            if (errorMessage != null && errorMessage.length() != 0){
        %>
            <small class="text-danger"><%=errorMessage%></small>
        <%
            }
        %>
        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput" placeholder="ユーザーID" name="user_id">
            <label for="floatingInput">ユーザーID</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="パスワード" name="user_pwd">
            <label for="floatingPassword">パスワード</label>
        </div>
        <input class="w-100 btn btn-lg btn-primary" type="submit" value="ログイン">
        <input type="hidden" name="operation" value="login">
    </form>
</main>

<script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</body>

</html>