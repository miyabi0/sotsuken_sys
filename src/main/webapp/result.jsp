<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.sotsuken_sys.entity.SPBean,java.util.List" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>卒業研究@njb</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <script type="text/javascript">
        function detailsClick(sp_id) {
            document.forms["result-form"].elements["sp_id"].value = sp_id;
            document.forms["result-form"].submit();
        }
    </script>
    <%
        List<SPBean> spList = com.example.sotsuken_sys.util.AutoCast.automaticCast(request.getAttribute("spList"));

    %>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand">卒業研究@njb</a>
        <form class="d-flex mt-3" action="./FrontControllerServlet" method="post">
            <!--<input class="form-control me-2" type="search" placeholder="テーマ名" aria-label="Search">
            <input type="submit" class="btn btn-primary" value="検索">-->
            <input type="hidden" name="operation" value="logout">
            <input type="submit" class="btn btn-primary" value="ログアウト">
        </form>
    </div>
</nav>
<div class="py-5">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 g-2">
            <%
                if (spList == null || spList.size() == 0){
            %>
            <h3>該当なし</h3>

            <%
                }
                else{
                    for (SPBean spBean : spList){
            %>
            <form action="./FrontController" method="post" name="result-form">
                <div class="col">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h3 class="card-text"><%=spBean.getSp_theme()%></h3>
                            <p class="card-text">概要：<%=spBean.getSp_overview()%></p>
                            <input type="button" class="btn btn-sm btn-outline-secondary" value="詳細" onclick="detailsClick(<%=spBean.getSp_id()%>)">
                        </div>
                    </div>
                </div>
                <input type="hidden" name="sp_id" value="<%=spBean.getSp_id()%>">
                <input type="hidden" name="operation" value="details">
            </form>
            <%
                    }
                }
            %>
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
