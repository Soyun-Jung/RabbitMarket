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
	label {
		float: left;
	}
	div {
		margin: auto;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="HeaderNav.jsp" %>
	<header class="masthead">
		<h2>게시글 작성</h2>
		<form action="BoardWriteController" name="boardForm" method="POST">
			<div class="mb-3 col-3">
				<label for="BD_TITLE" class="form-label">글제목</label>
				<input type="text" class="form-control" name="BD_TITLE" id="BD_TITLE" <c:if test="${!empty requestScope.BD_TITLE}">value="${requestScope.BD_TITLE}"</c:if> required="required">
			</div>
			<div class="mb-3 col-3">
				<label class="form-label">글쓴이</label>
				<input type="text" class="form-control" name="BD_MBID" value="${sessionScope.memberInfo.getMb_id()}" readonly="readonly">
			</div>
			<div class="mb-3 col-3">
				<label for="BD_STATE" class="form-label">판매상태</label>
				<select class="form-control" name="BD_STATE" id="BD_STATE">
					<c:if test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
						<option value="NO" selected="selected">공지</option>
					</c:if>
					<option value="AO" <c:if test="${requestScope.BD_STATE eq 'AX' or empty requestScope.BD_STATE and sessionScope.memberInfo.getMb_level() ne 'M'}">selected="selected"</c:if>>판매 중</option>
					<option value="AX" <c:if test="${requestScope.BD_STATE eq 'AX'}">selected="selected"</c:if>>판매 완료</option>
					<option value="HO" <c:if test="${requestScope.BD_STATE eq 'HO'}">selected="selected"</c:if>>나눔 중</option>
					<option value="HX" <c:if test="${requestScope.BD_STATE eq 'HX'}">selected="selected"</c:if>>나눔 완료</option>
				</select>
			</div>
			<div class="mb-3 col-3">
				<label for="BD_CONTENT" class="form-label">내용</label>
				<textarea rows="20" cols="40" class="form-control" name="BD_CONTENT" id="BD_CONTENT"><c:if test="${!empty requestScope.BD_CONTENT}">${requestScope.BD_CONTENT}</c:if></textarea>
			</div>
			<div class="mb-3 col-3">
				<label for="BD_FILE" class="form-label">첨부파일</label>
				<c:choose>
					<c:when test="${!empty requestScope.BD_FILE}">
						<input type="text" name="BD_FILE" value="${requestScope.BD_FILE}" readonly="readonly">
					</c:when>
					<c:otherwise>
						<input type="file" class="form-control" name="BD_FILE" id="BD_FILE">
						<button class="btn btn-primary btn-sm" onclick="fileUpload()" style="float: right;">파일업로드</button>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="mb-3 col-3" style="clear: both;">
				<input type="submit" class="btn btn-primary btn-md" value="글쓰기">
			</div>
			<input type="hidden" name="BD_REQUEST" value="BD1">
			<input type="hidden" name="page" value="1">
		</form>
		<form action="bList">
			<input type="hidden" name="MB_ID" value="${requestScope.MB_ID}">
			<input type="hidden" name="MB_LOC" value="${requestScope.MB_LOC}">
			<input type="hidden" name="BD_TITLE" value="${requestScope.BD_TITLE}">
			<input type="hidden" name="BD_REQUEST" value="${requestScope.BD_REQUEST}">
			<input type="hidden" name="page" value="${requestScope.page}">
			<input type="submit" class="btn btn-primary btn-md" value="목록보기">
		</form>
	</header>
	<%@ include file="ProjFooter.jsp" %>
</body>
<script>
	function fileUpload(){
		boardForm.onsubmit = null;
		boardForm.action = "FileUploadController";
		boardForm.enctype = "multipart/form-data";
		boardForm.submit();
	}
</script>
</html>