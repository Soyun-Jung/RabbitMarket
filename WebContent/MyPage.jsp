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
	table {
		margin: auto;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<header class="masthead">
		<%@ include file="HeaderNav.jsp" %>
		<h2>MyPage</h2>
		<table class="table table-striped table-light" style="width: 500px">
			<tr>
				<th>ID</th>
				<td>${sessionScope.memberInfo.getMb_id()}</td>
			</tr>
			<tr>
				<th>NAME</th>
				<td>${sessionScope.memberInfo.getMb_name()}</td>
			</tr>
			<tr>
				<th>LOCATION</th>
				<td>${sessionScope.memberInfo.getMb_loc()}</td>
			</tr>
			<tfoot>
				<tr>
					<td colspan="2">
						<a href="bList?BD_REQUEST=BD2&MB_ID=${sessionScope.memberInfo.getMb_id()}">
							<button class="btn btn-primary btn-md">내 게시글</button>
						</a>
						<a href="bList?BD_REQUEST=BD1">
							<button class="btn btn-primary btn-md">게시판</button>
						</a>
						<a href="MsgList?MS_RECID=${sessionScope.memberInfo.getMb_id()}&MS_REQUEST=MS1">
							<button class="btn btn-primary btn-md">쪽지함</button>
						</a>
						<c:choose>
							<c:when test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
								<a href="UserListController">
									<button class="btn btn-primary btn-md">회원목록</button>
								</a>
								<c:if test="${sessionScope.memberInfo.getMb_id() eq 'admin'}">
									<a href="Join.jsp?MB_LEVEL=M">
										<button class="btn btn-primary btn-md">관리자 등록</button>
									</a>
								</c:if>
							</c:when>
							<c:otherwise>
								<a href="UserDetailController?MB_ID=${sessionScope.memberInfo.getMb_id()}">
									<button class="btn btn-primary btn-md">내정보</button>
								</a>
								<button class="btn btn-primary btn-md" onclick="memberDel('${sessionScope.memberInfo.getMb_id()}')">회원탈퇴</button>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tfoot>
		</table>
	</header>
	<%@ include file="ProjFooter.jsp" %>
</body>
<script>
	function memberDel(MB_ID){
		if (confirm("정말 탈퇴하시겠습니까??")) { //확인
			location.href = "memberDel?MB_ID=" + MB_ID;
		}
	}
</script>
</html>