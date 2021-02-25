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
}
</style>
<title>Insert title here</title>
</head>
<body>
	<table class="table table-striped">
		<tr>
			<th>보낸 사람 : </th>
			<td>
				${requestScope.msg.getMS_SENDID()}			
			</td>
		</tr>
		<tr>
			<th>받는 사람 : </th>
			<td>
				${requestScope.msg.getMS_RECID()}			
			</td>
		</tr>
		<tr>
			<td>제목 : </td>
			<td>
				${requestScope.msg.getMS_TITLE()}
			</td>
		</tr>
		<tr>
			<th>내용 : </th>
			<td style="display: pre;">
				<textarea name="MS_TEXT" rows="5" cols="30" readonly="readonly" style="resize: none;">${requestScope.msg.getMS_TEXT()}</textarea>
			</td>
		</tr>
		<c:if test="${requestScope.msg.getMS_RECID() eq sessionScope.memberInfo.getMb_id()}">
		<tr>
			<td colspan="2">
				<button class="btn btn-primary btn-md" onclick="sendMsg('${requestScope.msg.getMS_RECID()}', '${requestScope.msg.getMS_SENDID()}', '${requestScope.msg.getMS_TITLE()}')">
					답장보내기
				</button>
			</td>
		</tr>
		</c:if>
	</table>
</body>
<script>
	function sendMsg(MS_SENDID, MS_RECID, MS_TITLE){
		location.href = "sendmsginfo?MS_SENDID=" + MS_SENDID + "&MS_RECID=" + MS_RECID + "&MS_TITLE=re:" + MS_TITLE;
	}
</script>
</html>