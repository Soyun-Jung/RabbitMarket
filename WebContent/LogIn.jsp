<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.79.0">
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<style type="text/css">
	body {
		text-align: center;
	}
	div{
		margin: auto;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="HeaderNav.jsp" %>
	<header class="masthead">
		<div class="container">
			<div class="col-3">
				<form action="login" method="post" name="LogInForm">
					<h1 class="h3 mb-3 fw-normal">로그인</h1>
					<label for="inputUserId" class="visually-hidden"></label>
					<input type="text" name="userId" class="form-control" placeholder="ID" required autofocus>
   					<label for="inputUserPw" class="visually-hidden"></label>
   					<input type="password" name="userPw" class="form-control" placeholder="Password" required>
   					<input type="submit" class="btn btn-primary btnmd" value="로그인" style="margin-top: 10px;">					
   				</form>
			</div>
		</div>
	</header>
	<%@ include file="ProjFooter.jsp" %>
</body>
</html>