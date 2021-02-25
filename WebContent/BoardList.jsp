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
<style type="text/css">
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
		<form action="bList">
  		 	 지역선택 :
   				<select name="MB_LOC" onchange="submit()">
    				<option value="all" <c:if test="${empty requestScope.MB_LOC}">selected="selected"</c:if>>전체</option>
    			    <option value="중구" <c:if test="${requestScope.MB_LOC eq '중구'}">selected="selected"</c:if>>중구</option>
     			   	<option value="동구" <c:if test="${requestScope.MB_LOC eq '동구'}">selected="selected"</c:if>>동구</option>
     		  		<option value="미추홀구" <c:if test="${requestScope.MB_LOC eq '미추홀구'}">selected="selected"</c:if>>미추홀구</option>
       				<option value="연수구" <c:if test="${requestScope.MB_LOC eq '연수구'}">selected="selected"</c:if>>연수구</option>
       				<option value="남동구" <c:if test="${requestScope.MB_LOC eq '남동구'}">selected="selected"</c:if>>남동구</option>
        			<option value="부평구" <c:if test="${requestScope.MB_LOC eq '부평구'}">selected="selected"</c:if>>부평구</option>
        			<option value="계양구" <c:if test="${requestScope.MB_LOC eq '계양구'}">selected="selected"</c:if>>계양구</option>
        			<option value="서구" <c:if test="${requestScope.MB_LOC eq '서구'}">selected="selected"</c:if>>서구</option>
        			<option value="강화군" <c:if test="${requestScope.MB_LOC eq '강화군'}">selected="selected"</c:if>>강화군</option>
		        	<option value="웅진군" <c:if test="${requestScope.MB_LOC eq '웅진군'}">selected="selected"</c:if>>웅진군</option>
        			<option value="기타" <c:if test="${requestScope.MB_LOC eq '기타'}">selected="selected"</c:if>>기타</option>
    			</select>
    		<c:choose>
    			<c:when test="${!empty requestScope.BD_TITLE}">
	    			<input type="hidden" name="BD_TITLE" value="${requestScope.BD_TITLE}">
    				<input type="hidden" name="BD_REQUEST" value="BD5">
    			</c:when>
    			<c:otherwise>
	    			<input type="hidden" name="BD_REQUEST" value="BD3">
    			</c:otherwise>
    		</c:choose>
    	</form>
		<form action="bList" method="post">
			검색 : 
			<c:choose>
				<c:when test="${!empty requestScope.BD_TITLE}">
					<input type="text" name="BD_TITLE" value="${requestScope.BD_TITLE}">
				</c:when>
				<c:otherwise>
					<input type="text" name="BD_TITLE">
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${!empty requestScope.MB_LOC}">
					<input type="hidden" name="MB_LOC" value="${requestScope.MB_LOC}">
					<input type="hidden" name="BD_REQUEST" value="BD5">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="BD_REQUEST" value="BD4">
				</c:otherwise>
			</c:choose>
			<input class="btn btn-primary btn-md" type="submit" value="검색">
		</form>
	</main>
	
	<table class="table table-dark table-borderless" style="width: 80%; margin-top: 10px;">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
				<c:if test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
					<th>삭제</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.notice}" var="list">
				<tr>
					<td>
						${list.getBD_NUM()}
					</td>
					<td>
						<c:choose>
							<c:when test="${list.getBD_DEL() eq -1 and sessionScope.memberInfo.getMb_level() eq 'A'}">
								삭제된게시글
							</c:when>
							<c:otherwise>
								<a href="boardView?MB_ID=${requestScope.MB_ID}&MB_LOC=${requestScope.MB_LOC}&BD_TITLE=${requestScope.BD_TITLE}&BD_REQUEST=${requestScope.BD_REQUEST}&bNum=${list.getBD_NUM()}&page=${requestScope.page.getNowPage()}">
									${list.getBD_TITLE()}
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						${list.getMB_ID()}
					</td>
					<td>
						<c:if test="${list.getBD_DEL() eq 1}">
							${list.getBD_HIT()}
						</c:if>
					</td>
					<td>
						${list.getBD_DATE()}
					</td>
					<c:if test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
						<td>
							<c:choose>
								<c:when test="${list.getBD_DEL() eq 1}">
									<button class="btn btn-primary btn-md" onclick="boardDel('${list.getBD_NUM()}', '${requestScope.page.getNowPage()}')">삭제</button>
								</c:when>
								<c:otherwise>
									<a href="BoardFlashBackController?BD_NUM=${list.getBD_NUM()}&page=${requestScope.page.getNowPage()}&BD_REQUEST=BD1">
										<button class="btn btn-primary btn-md">복구</button>
									</a>
								</c:otherwise>
							</c:choose>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<table class="table table-striped" style="width: 80%;">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>지역</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>판매상태</th>
				<th>조회수</th>
				<th>작성일</th>
				<c:choose>
					<c:when test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
						<th>신고횟수</th>
						<th>관리</th>
					</c:when>
					<c:otherwise>
						<th>삭제</th>
					</c:otherwise>
				</c:choose>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.board}" var="list">
				<tr>
					<td>
						${list.getBD_NUM()}
					</td>
					<td>
						<c:if test="${list.getBD_DEL() eq 1}">
							${list.getMB_LOC()}
						</c:if>
					</td>
					<td>
						<c:choose>
							<c:when test="${list.getBD_DEL() eq -1 and sessionScope.memberInfo.getMb_level() eq 'A'}">
								삭제된게시글
							</c:when>
							<c:otherwise>
								<a href="boardView?MB_ID=${requestScope.MB_ID}&MB_LOC=${requestScope.MB_LOC}&BD_TITLE=${requestScope.BD_TITLE}&BD_REQUEST=${requestScope.BD_REQUEST}&bNum=${list.getBD_NUM()}&page=${requestScope.page.getNowPage()}">
									${list.getBD_TITLE()}
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						${list.getMB_ID()}
					</td>
					<td>
						<c:if test="${list.getBD_DEL() eq 1}">
							<c:choose>
								<c:when test="${list.getBD_STATE() eq 'AO'}">판매 중</c:when>
								<c:when test="${list.getBD_STATE() eq 'AX'}">판매 완료</c:when>
								<c:when test="${list.getBD_STATE() eq 'HO'}">나눔 중</c:when>
								<c:when test="${list.getBD_STATE() eq 'HX'}">나눔 완료</c:when>
							</c:choose>
						</c:if>
					</td>
					<td>
						<c:if test="${list.getBD_DEL() eq 1}">
							${list.getBD_HIT()}
						</c:if>
					</td>
					<td>
						${list.getBD_DATE()}
					</td>
					<c:if test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
						<td>${list.getBR_REPORT()}</td>
						<td>
							<c:choose>
								<c:when test="${list.getBD_DEL() eq 1}">
									<button class="btn btn-primary btn-md" onclick="boardDel('${list.getBD_NUM()}', '${requestScope.page.getNowPage()}')">삭제</button>
								</c:when>
								<c:otherwise>
									<a href="BoardFlashBackController?BD_NUM=${list.getBD_NUM()}&page=${requestScope.page.getNowPage()}&BD_REQUEST=BD1">
										<button class="btn btn-primary btn-md">복구</button>
									</a>
								</c:otherwise>
							</c:choose>
						</td>
					</c:if>
					<td>
						<c:if test="${list.getMB_ID() eq sessionScope.memberInfo.getMb_id() and sessionScope.memberInfo.getMb_level() eq 'A' and list.getBD_DEL() eq 1}">
							<button class="btn btn-primary btn-md" onclick="boardDel('${list.getBD_NUM()}', '${requestScope.page.getNowPage()}')">삭제</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="9" style="text-align: right;">
          			<a href="BoardWriteCallController?MB_ID=${requestScope.MB_ID}&MB_LOC=${requestScope.MB_LOC}&BD_TITLE=${requestScope.BD_TITLE}&BD_REQUEST=${requestScope.BD_REQUEST}&page=${requestScope.page.getNowPage()}">
						<button class="btn btn-primary btn-md">글쓰기</button>
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
		        <a class="page-link" href="bList?MB_ID=${requestScope.MB_ID}&MB_LOC=${requestScope.MB_LOC}&BD_TITLE=${requestScope.BD_TITLE}&BD_REQUEST=${requestScope.BD_REQUEST}&page=${requestScope.page.getNowPage()-1}" aria-label="Previous">
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
							<a class="page-link" href="bList?MB_ID=${requestScope.MB_ID}&MB_LOC=${requestScope.MB_LOC}&BD_TITLE=${requestScope.BD_TITLE}&BD_REQUEST=${requestScope.BD_REQUEST}&page=${i}">
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
	            <a class="page-link" href="bList?MB_ID=${requestScope.MB_ID}&MB_LOC=${requestScope.MB_LOC}&BD_TITLE=${requestScope.BD_TITLE}&BD_REQUEST=${requestScope.BD_REQUEST}&page=${requestScope.page.getNowPage()+1}" aria-label="Next">
                	<span aria-hidden="true">&raquo;</span>
              	</a>
			</c:if>
		</li>
	</ul>
	<%@ include file="ProjFooter.jsp" %>
</body>
<script>
    function boardDel(bd_num, page){
    	if (confirm("정말 삭제하시겠습니까??")) { 		
			location.href = "BD_Delete?bd_num=" + bd_num + "&page=" + page + "&BD_REQUEST=BD1";
		}
	}
</script>
</html>