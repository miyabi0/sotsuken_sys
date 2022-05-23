<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.sotsuken_sys.entity.SPBean,java.util.List" %>
<html lang="ja">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>詳細</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <%
        SPBean spBean = com.example.sotsuken_sys.util.AutoCast.automaticCast(request.getAttribute("spBean"));
        List<String> tagNameList = com.example.sotsuken_sys.util.AutoCast.automaticCast(request.getAttribute("tagNameList"));
    %>
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
            <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 g-2">
                <div class="col">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h3 class="card-text"><%=spBean.getSp_theme()%></h3>
                            <p class="card-text">概要：<%=spBean.getSp_overview()%></p>
                            <p class="card-text">卒業年度：<%=spBean.getSp_year()%>年度</p>
                            <%
                                for (String tagName : tagNameList){
                            %><span class="badge bg-info text-dark"><%=tagName%></span>
                            <%
                                }
                            %>
                            <div class="card-text mt-3">
                                <form action="./FrontController" method="post">
                                    <input type="hidden" name="operation" value="file_link">
                                    <input type="submit" class="btn btn-sm btn-outline-secondary" value="ダウンロード（<%=spBean.getSp_file()%>）">
                                </form>
                            </div>
                        </div>
                    </div>
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
