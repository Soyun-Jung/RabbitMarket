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
	<h2>내 쪽지함</h2>
	<table class="table table-striped" style="margin: auto; width: 1500px">
		<thead>
			<tr>
				<th>글제목</th>
				<th>보낸 사람</th>
				<th>받는 사람</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.msglist}" var="list">
				<tr>
					<td>
						<a href="#" onclick="msgDetail('${list.getMS_SENDID()}', '${list.getMS_RECID()}', '${list.getMS_DATE()}')">${list.getMS_TITLE()}</a>
					</td>
					<td>${list.getMS_SENDID()}</td>
					<td>${list.getMS_RECID()}</td>
					<td>${list.getMS_DATE()}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4" style="text-align: right;">
					<a href="MsgList?MS_RECID=${sessionScope.memberInfo.getMb_id()}&MS_REQUEST=MS1">
						<button class="btn btn-primary btn-md">받은 쪽지</button>
					</a>
					<a href="MsgList?MS_SENDID=${sessionScope.memberInfo.getMb_id()}&MS_REQUEST=MS2">
						<button class="btn btn-primary btn-md">보낸 쪽지</button>
					</a>
				</td>
			</tr>
		</tfoot>
	</table>
	<ul class="pagination" style="transform: translateX(-50%); position: absolute; left: 50%">
		<li class="page-item">
			<c:if test="${requestScope.page.getNowPage()<=1}">
	          	<a class="page-link" href="#" aria-label="Previous">
                	<span aria-hidden="true">&laquo;</span>
              	</a>
          	</c:if>
          	<c:if test="${requestScope.page.getNowPage()>1}">
	          	<a class="page-link" href="MsgList?MS_RECID=${sessionScope.memberInfo.getMb_id()}&MS_SENDID=${sessionScope.memberInfo.getMb_id()}&MS_REQUEST=${requestScope.MS_REQUEST}&page=${requestScope.page.getNowPage()-1}" aria-label="Previous">
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
						<a class="page-link" href="MsgList?MS_RECID=${sessionScope.memberInfo.getMb_id()}&MS_SENDID=${sessionScope.memberInfo.getMb_id()}&MS_REQUEST=${requestScope.MS_REQUEST}&page=${i}">
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
	            <a class="page-link" href="MsgList?MS_RECID=${sessionScope.memberInfo.getMb_id()}&MS_SENDID=${sessionScope.memberInfo.getMb_id()}&MS_REQUEST=${requestScope.MS_REQUEST}&page=${requestScope.page.getNowPage()+1}" aria-label="Next">
                	<span aria-hidden="true">&raquo;</span>
              	</a>
			</c:if>
        </li>
	</ul>
	</main>
	<%@ include file="ProjFooter.jsp" %>
</body>
<script>
	function msgDetail(MS_SENDID, MS_RECID, MS_DATE){
		window.open("MessageDetailController?MS_SENDID=" + MS_SENDID + "&MS_RECID=" + MS_RECID + "&MS_DATE=" + MS_DATE, "", "menubar=1, width=500px, height=400px");
	}
</script>
</html>