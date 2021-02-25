<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<textarea rows="2" cols="40" id="CM_CONTENT" name="CM_CONTENT"></textarea>
				<input type="button" onclick="ajaxTest()" value="ajaxTest">
			</td>
		</tr>
	</table>
	<table id="comments"></table>
</body>
<script type="text/javascript">
	window.onload = function(){
		ajaxCommentList();
	}
	
	function ajaxCommentList(){
		var httpRequest = new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function(){
			if(httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200){
				CommentList(httpRequest.response);
			}
		}
		
		httpRequest.open("POST", "TestCommentList", true);
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.responseType = 'json';
		httpRequest.send();
	}
	
	function CommentList(cmList){
		var comments = "<tr>"+
							"<th>"+
								"작성자"+
							"</th>"+
							"<th>"+
								"댓글내용"+
							"</th>"+
							"<th>"+
								"작성일"+
							"</th>"+
						"</th>";
		for (var i in cmList) {
			comments += "<tr>"+
							"<td>"+
								cmList[i].CM_MBID+
							"</td>"+
							"<td>"+
								cmList[i].CM_CONTENT+
							"</td>"+
							"<td>"+
								cmList[i].CM_DATE+
							"</td>"+
						"</tr>";
		}
		document.getElementById("comments").innerHTML = comments;
	}
	
	function ajaxTest(){
		var httpRequest = new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function(){
			if(httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200){
				document.getElementById("CM_CONTENT").value = "";
				ajaxCommentList();
			}
		}
		
		httpRequest.open("POST", "TestComment", true);
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.send("CM_CONTENT="+document.getElementById("CM_CONTENT").value);
	}
</script>
</html>