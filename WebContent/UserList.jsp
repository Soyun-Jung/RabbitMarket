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
	main{
		margin-top: 100px;
		text-align: center;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="HeaderNav.jsp" %>
	<main>
	<h2>UserList</h2>
	<table class="table table-striped" style="margin: auto; width: 1500px">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>판매지역</th>
				<th>신고된 게시글 수</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.userList}" var="result">
				<tr>
					<td><a href="UserDetailController?MB_ID=${result.getMB_ID()}&page=${requestScope.page.getNowPage()}">${result.getMB_ID()}</a></td>
					<td>${result.getMB_NAME()}</td>
					<td>${result.getMB_LOC()}</td>
					<td>${result.getBR_REPORT()}</td>
					<td>
						<c:if test="${result.getMB_LEVEL() eq 'A'}">
							<c:choose>
								<c:when test="${result.getMB_STATE() eq 'O'}">
									<a href="UserBlackController?MB_ID=${result.getMB_ID()}&page=${requestScope.page.getNowPage()}">
										<button class="btn btn-primary btn-sm">정지</button>
									</a>
								</c:when>
								<c:otherwise>
									<a href="UserFlashBackController?MB_ID=${result.getMB_ID()}&page=${requestScope.page.getNowPage()}">
										<button class="btn btn-primary btn-sm">복구</button>
									</a>
								</c:otherwise>
							</c:choose>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination" style="transform: translateX(-50%); position: absolute; left: 50%">
		<li class="page-item">
			<c:if test="${requestScope.page.getNowPage()<=1}">
	          	<a class="page-link" href="#" aria-label="Previous">
                	<span aria-hidden="true">&laquo;</span>
              	</a>
          	</c:if>
          	<c:if test="${requestScope.page.getNowPage()>1}">
	          	<a class="page-link" href="UserListController?page=${requestScope.page.getNowPage()-1}" aria-label="Previous">
	                <span aria-hidden="true">&laquo;</span>
              	</a>
          	</c:if>
		</li>
						
		<c:forEach begin="${requestScope.page.getStartPage()}" end="${requestScope.page.getEndPage()}" var="i" step="1">
			<c:choose>
				<c:when test="${i eq requestScope.page.getNowPage()}">
					<li class="page-item">
						<a class="page-link" href="#">${i}</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="page-link" href="UserListController?page=${i}">
							${i}
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
						
		<li class="page-item">
			<c:if test="${requestScope.page.getNowPage()>=requestScope.page.getLastPage()}">
				<a class="page-link" href="#" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
              	</a>
            </c:if>
			<c:if test="${requestScope.page.getNowPage()<requestScope.page.getLastPage()}">
	            <a class="page-link" href="UserListController?page=${requestScope.page.getNowPage()+1}" aria-label="Next">
                	<span aria-hidden="true">&raquo;</span>
              	</a>
			</c:if>
        </li>
	</ul>
	</main>
	<%@ include file="ProjFooter.jsp" %>
</body>
</html>