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
}table {
	margin: auto;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="HeaderNav.jsp" %>
	<header class="masthead">
		<c:choose>
			<c:when test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
				<h2>사용자정보</h2>
			</c:when>
			<c:otherwise>
				<h2>내정보</h2>
			</c:otherwise>
		</c:choose>
		<table class="table table-striped table-light" style="width: 500px">
			<tr>
				<th>아이디</th>
				<td>${requestScope.userDetail.getMB_ID()}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>${requestScope.userDetail.getMB_PW()}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${requestScope.userDetail.getMB_NAME()}</td>
			</tr>
			<tr>
				<th>판매지역</th>
				<td>${requestScope.userDetail.getMB_LOC()}</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>${requestScope.userDetail.getMB_PHONE()}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					${requestScope.userDetail.getMB_POSTCODE()}
					${requestScope.userDetail.getMB_ROADADDR()}
					${requestScope.userDetail.getMB_JIBUNADDR()}
					${requestScope.userDetail.getMB_DETAILADDR()}
					${requestScope.userDetail.getMB_EXADDR()}
				</td>
			</tr>
			<c:choose>
				<c:when test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
					<tr>
						<th>신고된 게시글 수</th>
						<td>${requestScope.userDetail.getBR_REPORT()}</td>
					</tr>
					<tr>
						<th>관리</th>
						<c:choose>
							<c:when test="${requestScope.userDetail.getMB_LEVEL() eq 'A'}">
								<c:if test="${requestScope.userDetail.getMB_STATE() eq 'O'}">
									<td>
										<a href="UserDeleteController?MB_ID=${requestScope.userDetail.getMB_ID()}&page=${requestScope.page}">
											<button class="btn btn-primary btn-md">정지</button>
										</a>
									</td>
								</c:if>
								<c:if test="${requestScope.userDetail.getMB_STATE() eq 'B'}">
									<td>
										<a href="UserFlashBackController?MB_ID=${requestScope.userDetail.getMB_ID()}&page=${requestScope.page}">
											<button class="btn btn-primary btn-md">복구</button>
										</a>
									</td>
								</c:if>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:when>
				<c:otherwise>
					<th>정보수정</th>
					<td>
						<form action="UserGetModController" method="POST">
							<input type="hidden" name="MB_ID" value="${requestScope.userDetail.getMB_ID()}">
							<input type="hidden" name="MB_PW" value="${requestScope.userDetail.getMB_PW()}">
							<input type="hidden" name="MB_NAME" value="${requestScope.userDetail.getMB_NAME()}">
							<input type="hidden" name="MB_LOC" value="${requestScope.userDetail.getMB_LOC()}">
							<input type="hidden" name="MB_PHONE" value="${requestScope.userDetail.getMB_PHONE()}">
							<input type="hidden" name="MB_POSTCODE" value="${requestScope.userDetail.getMB_POSTCODE()}">
							<input type="hidden" name="MB_ROADADDR" value="${requestScope.userDetail.getMB_ROADADDR()}">
							<input type="hidden" name="MB_JIBUNADDR" value="${requestScope.userDetail.getMB_JIBUNADDR()}">
							<input type="hidden" name="MB_DETAILADDR" value="${requestScope.userDetail.getMB_DETAILADDR()}">
							<input type="hidden" name="MB_EXADDR" value="${requestScope.userDetail.getMB_EXADDR()}">
							<input type="hidden" name="MB_LEVEL" value="${requestScope.userDetail.getMB_LEVEL()}">
							<input type="submit" class="btn btn-primary btn-md" value="정보수정">
						</form>
					</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
					<tfoot>
						<tr style="text-align: right;">
							<c:choose>
								<c:when test="${!empty requestScope.page}">
									<td colspan="2">
										<button class="btn btn-primary btn-md" onclick="location.href = 'bList?BD_REQUEST=BD2&MB_ID=${requestScope.userDetail.getMB_ID()}'">작성글</button>
										<a href="UserListController?page=${requestScope.page}">
											<button class="btn btn-primary btn-md">목록보기</button>
										</a>
									</td>
								</c:when>
								<c:otherwise>
									<td colspan="2">
										<button class="btn btn-primary btn-md" onclick="location.href = 'bList?BD_REQUEST=BD2&MB_ID=${requestScope.userDetail.getMB_ID()}'">작성글</button>
										<a href="UserListController">
											<button class="btn btn-primary btn-md">목록보기</button>
										</a>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</tfoot>
				</c:when>
				<c:otherwise>
					<tfoot>
						<tr style="text-align: right;">
							<td colspan="2">
								<button class="btn btn-primary btn-md" onclick="location.href = 'bList?BD_REQUEST=BD2&MB_ID=${sessionScope.memberInfo.getMb_id()}'">내 게시글</button>
								<button class="btn btn-primary btn-md" onclick="memberDel('${sessionScope.memberInfo.getMb_id()}')">회원탈퇴</button>
							</td>
						</tr>
					</tfoot>
				</c:otherwise>
			</c:choose>
		</table>
	</header>
	<%@ include file="ProjFooter.jsp" %>
</body>
<script>
	function memberDel(MB_ID){
		if (confirm("정말 탈퇴하시겠습니까??")) {
			location.href = "memberDel?MB_ID=" + MB_ID;
		}
	}
</script>
</html>