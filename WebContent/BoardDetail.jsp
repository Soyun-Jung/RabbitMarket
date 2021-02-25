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
<style>
	label {
		float: left;
	}
	div {
	margin: auto;
	}
	table {
	margin: auto;
	}
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
		<table class="table table-light table-striped" style="width: 1000px">
			<tr>
				<th>글번호</th>
				<th>지역</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>판매상태</th>
			</tr>
			<tr>
				<td>
					${requestScope.view.getBD_NUM()}			
				</td>
				<td>
					${requestScope.view.getMB_LOC()}			
				</td>
				<td>
					${requestScope.view.getBD_DATE()}			
				</td>
				<td>
					${requestScope.view.getBD_HIT()}
				</td>
				<td>
					<c:choose>
						<c:when test="${requestScope.view.getBD_STATE() eq 'NO'}">
							공지
						</c:when>
						<c:when test="${requestScope.view.getBD_STATE() eq 'AO'}">
							판매 중
						</c:when>
						<c:when test="${requestScope.view.getBD_STATE() eq 'AX'}">
							판매 완료
						</c:when>
						<c:when test="${requestScope.view.getBD_STATE() eq 'HO'}">
							나눔 중
						</c:when>
						<c:when test="${requestScope.view.getBD_STATE() eq 'HX'}">
							나눔 완료
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td colspan="4">
					${requestScope.view.getBD_TITLE()}
				</td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td colspan="4">
					${requestScope.view.getMB_ID()}
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<c:if test="${!empty requestScope.view.getBD_FILE()}">
						<img src="FileUpload/${requestScope.view.getBD_FILE()}" width="300px">
					</c:if>
					<pre>${requestScope.view.getBD_CONTENT()}</pre>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<c:if test="${sessionScope.memberInfo.getMb_id() ne requestScope.view.getMB_ID() and sessionScope.memberInfo.getMb_level() ne 'M'}">
						<form action="BoardReportController" method="POST" style="display: inline;">
							<input type="hidden" name="BR_BDNUM" value="${requestScope.view.getBD_NUM()}">
							<input type="hidden" name="BR_BDMBID" value="${requestScope.view.getMB_ID()}">
							<input type="hidden" name="BR_MBID" value="${sessionScope.memberInfo.getMb_id()}">
							<input type="submit" class="btn btn-primary btn-md" value="신고">
						</form>
					</c:if>
					<c:if test="${sessionScope.memberInfo.getMb_id() ne requestScope.view.getMB_ID()}">
						<button class="btn btn-primary btn-md" onclick="sendMsg('${requestScope.view.getMB_ID()}', '${sessionScope.memberInfo.getMb_id()}')">쪽지</button>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="text-align: right;">
					<a href="BoardWriteCallController?BD_REQUEST=BD1&page=1">
						<button class="btn btn-primary btn-md">글 쓰기</button>
					</a>
					<c:if test="${sessionScope.memberInfo.getMb_id() eq requestScope.view.getMB_ID()}">
						<form action="BoardGetModController" method="POST" style="display: inline;">
							<input type="hidden" name="BD_NUM" value="${requestScope.view.getBD_NUM()}">
							<input type="hidden" name="BD_TITLE" value="${requestScope.view.getBD_TITLE()}">
							<input type="hidden" name="BD_MBID" value="${requestScope.view.getMB_ID()}">
							<input type="hidden" name="BD_CONTENT" value="${requestScope.view.getBD_CONTENT()}">
							<input type="hidden" name="BD_STATE" value="${requestScope.view.getBD_STATE()}">
							<input type="hidden" name="BD_FILE" value="${requestScope.view.getBD_FILE()}">
							<input type="submit" class="btn btn-primary btn-md" value="수정하기">
						</form>
					</c:if>
					<a href="bList?MB_ID=${requestScope.MB_ID}&MB_LOC=${requestScope.MB_LOC}&BD_TITLE=${requestScope.BD_TITLE}&BD_REQUEST=${requestScope.BD_REQUEST}&page=${requestScope.page}">
						<button class="btn btn-primary btn-md">목록보기</button>
					</a>
					<c:choose>
						<c:when test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
							<c:choose>
								<c:when test="${requestScope.view.getBD_DEL() eq 1}">
									<button class="btn btn-primary btn-md" onclick="boardDel('${requestScope.view.getBD_NUM()}', '${requestScope.page}')">삭제</button>
								</c:when>
								<c:otherwise>
									<a href="BoardFlashBackController?BD_NUM=${requestScope.view.getBD_NUM()}">
										<button class="btn btn-primary btn-md">복구</button>
									</a>
								</c:otherwise>
								</c:choose>
						</c:when>
						<c:when test="${sessionScope.memberInfo.getMb_id() eq requestScope.view.getMB_ID() and sessionScope.memberInfo.getMb_level() ne 'M'}">
							<c:if test="${requestScope.view.getBD_DEL() eq 1}">
								<button class="btn btn-primary btn-md" onclick="boardDel('${requestScope.view.getBD_NUM()}', '${requestScope.page}')">삭제</button>
							</c:if>
						</c:when>
					</c:choose>
				</td>
			</tr>
		</table>
	<table class="table table-light table-striped" style="width: 1000px">
		<thead>
			<tr>
				<td colspan="4">
					<h5 style="text-align: left;">댓글</h5>
					<form action="CommentWrite" method="post">
						<textarea name="cmtext" required="required" style="width: 100%; resize: none;"></textarea>
						<input type="hidden" name="MB_ID" value="${requestScope.MB_ID}">
						<input type="hidden" name="MB_LOC" value="${requestScope.MB_LOC}">
						<input type="hidden" name="BD_TITLE" value="${requestScope.BD_TITLE}">
						<input type="hidden" name="BD_REQUEST" value="${requestScope.BD_REQUEST}">
						<input type="hidden" name="page" value="${requestScope.page}">
						<input type="hidden" name="BD_NUM" value="${requestScope.view.getBD_NUM()}">
						<input type="hidden" name="BD_MBID" value="${requestScope.view.getMB_ID()}">
						<input type="hidden" name="CM_MBID" value="${sessionScope.memberInfo.getMb_id()}">
						<input type="submit" class="btn btn-primary btn-md" value="작성" style="float: right;">
					</form>
				</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty requestScope.comment}">
				<tr>
					<th>작성자</th>
					<th>내용</th>
					<th>작성일</th>
					<th>삭제</th>
				</tr>
				<c:forEach items="${requestScope.comment}" var="comment">
					<tr>
						<td>
							<c:if test="${comment.getCm_state() eq 1}">
								${comment.getCm_mbid()}
							</c:if>
						</td>
						<td>
							<c:choose>
								<c:when test="${comment.getCm_state() eq 1}">
									${comment.getCm_content()}
								</c:when>
								<c:otherwise>
									삭제된 댓글
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:if test="${comment.getCm_state() eq 1}">
								${comment.getCm_date()}
							</c:if>
						</td>
						<td>
							<c:choose>
							<c:when test="${sessionScope.memberInfo.getMb_level() ne 'M'}">
								<c:if test="${comment.getCm_state() eq 1 and comment.getCm_mbid() eq sessionScope.memberInfo.getMb_id()}">
									<form action="CmDelete">
										<input type="hidden" name="bNum" value="${requestScope.view.getBD_NUM()}">
										<input type="hidden" name="bdmbid" value="${comment.getCm_bdmbid()}">
										<input type="hidden" name="mbid" value="${comment.getCm_mbid()}">
										<input type="hidden" name="date" value="${comment.getCm_date()}">
										<input type="submit" class="btn btn-primary btn-md" value="삭제">
									</form>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${comment.getCm_state() eq 1}">
										<form action="CmDelete">
											<input type="hidden" name="bNum" value="${requestScope.view.getBD_NUM()}">
											<input type="hidden" name="bdmbid" value="${comment.getCm_bdmbid()}">
											<input type="hidden" name="mbid" value="${comment.getCm_mbid()}">
											<input type="hidden" name="date" value="${comment.getCm_date()}">
											<input type="submit" class="btn btn-primary btn-md" value="삭제">
										</form>
									</c:when>
									<c:otherwise>
										<form action="CmFlashBackController">
											<input type="hidden" name="bNum" value="${requestScope.view.getBD_NUM()}">
											<input type="hidden" name="bdmbid" value="${comment.getCm_bdmbid()}">
											<input type="hidden" name="mbid" value="${comment.getCm_mbid()}">
											<input type="hidden" name="date" value="${comment.getCm_date()}">
											<input type="submit" class="btn btn-primary btn-md" value="복구">
										</form>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	</main>
	<%@ include file="ProjFooter.jsp" %>
</body>
<script>
    function boardDel(bd_num, page){
    	if (confirm("정말 삭제하시겠습니까??")) { 		
			location.href = "BD_Delete?bd_num=" + bd_num + "&BD_Delete?page=" + page;
		}
	}
    function sendMsg(MS_RECID, MS_SENDID){
    	window.open("sendmsginfo?MS_RECID=" + MS_RECID + "&MS_SENDID=" + MS_SENDID, "", "menubar=1, width=500px, height=400px");
    }
</script>
</html>