<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<style>
body {
	text-align: center;
}

caption {
	font-weight: bold;
	font-size: 30px;
	margin-bottom: 15px;
}

table {
	margin: auto;
	text-align: center;
}
</style>
<title>메시지 보내기</title>
</head>
<body>
<form action="SendMsg" method="post">
	<table class="table table-striped">
		<tr>
			<td>받는 사람 : </td>
			<td>
				${requestScope.MS_RECID}
				<input type="hidden" name="MS_RECID" value="${requestScope.MS_RECID}">
			</td>
		</tr>
		<tr>
			<td>보내는 사람 : </td>
			<td>
				${requestScope.MS_SENDID}
				<input type="hidden" name="MS_SENDID" value="${requestScope.MS_SENDID}">		
			</td>
		</tr>
		<tr>
			<td>제목 : </td>
			<td>
				<c:choose>
					<c:when test="${!empty requestScope.MS_TITLE}">
						${requestScope.MS_TITLE}
						<input type="hidden" name="MS_TITLE" value="${requestScope.MS_TITLE}">
					</c:when>
					<c:otherwise>
						<input type="text" name="MS_TITLE">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>내용 : </td>
			<td>
				<textarea name="MS_TEXT" rows="5" cols="30" style="resize: none;"></textarea>	
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="보내기">
			</td>
		</tr>
	</table>
</form>
</body>
</html>