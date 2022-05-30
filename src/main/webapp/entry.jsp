<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.sotsuken_sys.entity.TagBean,java.util.List" %>
<%@ page import="javax.swing.text.html.HTML" %>
<html lang="ja">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>卒研登録</title>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
    <%
        List<TagBean> tagList = com.example.sotsuken_sys.util.AutoCast.automaticCast(request.getAttribute("tagList"));
    %>
    <script type="text/javascript">
        function addTag() {
            document.forms["entry-form"].elements["operation"].value = "addTag";
            document.forms["entry-form"].submit();
        }
    </script>
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
        <div class="card shadow-sm mx-4">
            <div class="card-body">
                <form class="mx-auto" action="./FrontController" enctype="multipart/form-data" name="entry-form" method="post">
                    <div class="row mb-3">
                        <label for="inputTheme" class="col-sm-2 col-form-label">テーマ名</label>
                        <div class="col-sm-10">
                            <input type="txet" id="inputTheme" name="theme" required class="form-control">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="yearSelect" class="col-sm-2 col-form-label">卒業年度</label>
                        <div class="col-sm-2">
                            <select class="form-select" name="year"  required id="yearSelect">
                                <option value="2017">2017</option>
                                <option value="2018">2018</option>
                                <option value="2019">2019</option>
                                <option value="2020">2020</option>
                                <option value="2021">2021</option>
                                <option value="2022">2022</option>
                                <option value="2023">2023</option>
                                <option value="2024">2024</option>
                                <option value="2025">2025</option>
                                <option value="2026">2026</option>
                                <option value="2027">2027</option>
                                <option value="2028">2028</option>
                                <option value="2029">2029</option>
                                <option value="2030">2030</option>
                            </select>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">タグ</label>
                        <div class="col-sm-10">
                            <div class="border rounded px-4">
                                <%
                                for (TagBean tagBean : tagList){
                                %>
                                <div class="form-check form-check-inline py-2" >
                                    <input type="checkbox" required name="checkbox" class="form-check-input" id="<%=tagBean.getTag_id()%>>" value="<%=tagBean.getTag_id()%>">
                                    <label for="<%=tagBean.getTag_id()%>" class="form-check-label"><%=tagBean.getTag_name()%></label>
                                </div>
                                <%
                                    }
                                %>
                                <div id="tag"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputOverView" class="col-sm-2 col-form-label">概要</label>
                        <div class="col-sm-10">
                            <textarea id="inputOverView" name="overview" class="form-control" required></textarea>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="formFile" class="col-sm-2 col-form-label">ファイル</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="file" id="formFile" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-auto mx-auto">
                            <input type="submit" value="確認" class="btn btn-primary px-5">
                        </div>
                    </div>
                    <input type="hidden" name="operation" value="entry">
                </form>
            </div>
        </div>
    </div>
</div>
<script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</body></html>