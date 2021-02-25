<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
		<div class="container-fluid">
			<a class="navbar-brand js-scroll-trigger" href="Main.jsp">
				<img src="assets/img/hare.png" alt="" />
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars ml-1"></i>
			</button>
			
			<c:if test="${!empty sessionScope.memberInfo}">
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav text-uppercase ml-auto">
						<c:if test="${sessionScope.memberInfo.getMb_level() eq 'A'}">
							<li class="nav-item">
								<a class="nav-link js-scroll-trigger" href="#" onclick="sendMsg('admin', '${sessionScope.memberInfo.getMb_id()}')">문의</a>
							</li>
						</c:if>
						<li class="nav-item"><a class="nav-link js-scroll-trigger" href="bList?BD_REQUEST=BD6">Notice</a></li>
						<li class="nav-item"><a class="nav-link js-scroll-trigger" href="MyPage.jsp">MyPage</a></li>
						<li class="nav-item"><a class="nav-link js-scroll-trigger" href="logout?MB_ID=${sessionScope.memberInfo.getMb_id()}">Logout</a></li>
					</ul>
				</div>
			</c:if>
		</div>
	</nav>
</body>
<script>
	function sendMsg(MS_RECID, MS_SENDID){
		window.open("sendmsginfo?MS_RECID=" + MS_RECID + "&MS_SENDID=" + MS_SENDID, "", "menubar=1, width=500px, height=400px");
	}
</script>
</html>