<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body id="page-top">
	<%@ include file="HeaderNav.jsp" %>
	<header class="masthead">
		<div class="container">
			<div class="masthead-subheading">Welcome To Our Market!</div>
			<div class="masthead-heading text-uppercase">It's Nice To Meet You</div>
			<c:choose>
				<c:when test="${empty sessionScope.memberInfo}">
					<a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="LogIn.jsp">LogIn</a>
					<a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="Join.jsp?MB_LEVEL=A">Join</a>
				</c:when>
				<c:otherwise>
					<div class="masthead-subheading">${sessionScope.memberInfo.getMb_name()}님 환영합니다.</div>
					<main class="px-3">
						<a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="bList?BD_REQUEST=BD1">
							Sales List
						</a>
					</main>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
	<%@ include file="ProjFooter.jsp" %>
</body>
</html>