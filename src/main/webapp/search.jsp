<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.sotsuken_sys.entity.TagBean,java.util.List" %>
<html lang="ja">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>卒研検索</title>
	<link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.css">
	<link rel="stylesheet" href="css/search.css">

	<%
        List<TagBean> tagList = com.example.sotsuken_sys.util.AutoCast.automaticCast(request.getAttribute("tagList"));
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
	<form action="./FrontController" method="post" name="tag-form">
		<div class="py-5">
			<div class="container">

				<div class="row btn-group  row-cols-1 row-cols-sm-2 row-cols-md-4 g-2" role="group">
					<%
                for (TagBean tagBean : tagList){
            %>
					<div class="col">
						<input type="checkbox" name="check_tags" class="btn-check" value="<%=tagBean.getTag_id()%>" id="<%=tagBean.getTag_id()%>" autocomplete="off">
						<label for="<%=tagBean.getTag_id()%>" class="btn card btn-outline-dark">
							<h3 class="card-text"><%=tagBean.getTag_name()%></h3>
							<small class="card-text"><%=tagBean.getCnt_tag()%> 件</small>
						</label>
					</div>
					<%
                }
            %>
				</div>
			</div>

		</div>
		<nav class="navbar fixed-bottom bg-light border-top">
			<div class="container-fluid">
				<input type="submit" class="btn btn-primary mx-auto px-5" value="検索">
			</div>
		</nav>
		<input type="hidden" name="operation" value="result">
	</form>
	<script src="bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
</body>

</html>
