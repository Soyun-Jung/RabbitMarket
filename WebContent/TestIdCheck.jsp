<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<input type="text" id="MB_ID">
				<input type="button" onclick="ajaxtest()" value="ajaxTest">
			</td>
		</tr>
		<tr>
			<td id="result">
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	function ajaxtest(){
		var httpRequest = new XMLHttpRequest(); // XMLHttpRequest 객체를 생성함.
		
		httpRequest.onreadystatechange = function() { // XMLHttpRequest 객체의 현재 상태를 파악함.
			// XMLHttpRequest 객체의 현재 상태가 요청 완료이고, 서버에 문서가 존재하면 받은 데이터를 출력함.
			if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200 ) {
				document.getElementById("result").innerHTML = httpRequest.responseText;
			}
		}
		
		// POST 방식의 비동기식 요청으로 Http 요청을 생성함.
		httpRequest.open("POST", "Test", true);
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.send("MB_ID="+document.getElementById("MB_ID").value);
	}
</script>
</html>