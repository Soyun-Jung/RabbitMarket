<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.79.0">
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/checkout/">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/form-validation.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="form-validation.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet" />
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="HeaderNav.jsp" %>
	<header class="masthead">
		<div class="container" style="text-align: left; position: relative; left: 10%">
			<form action="memberJoin" onsubmit="return checkValue()" name=memberInfo method="post" >
				<h2>회원가입</h2>
				<div class="col-md-8 col-lg-9">
					<div class="row g-3">
						<div class="col-12">
							<label for="userId" style="display: block;">아이디</label>
							<input type="text" class="form-control w-50" name="userId" id="userId" style="display: inline;" required="required">
							<button class="btn btn-primary btn-md" onclick="idCheck('<%= request.getParameter("MB_LEVEL") %>')">중복확인</button>
						</div>
						<div class="col-5">
							<label for="userPw">비밀번호</label>
							<input type="password" class="form-control" name="userPw" id="userPw" required="required">
						</div>
						<div class="col-5">
							<label for="userPwCheck">비밀번호 확인</label>
							<input type="password" class="form-control" name="userPwCheck" id="userPwCheck">
						</div>
						<div class="col-10">
							<label for="userName">이름</label>
							<input type="text" class="form-control" name="userName" id="userName" required="required">
						</div>
						<% if(request.getParameter("MB_LEVEL").equals("A")){ %>
						<div class="col-10">
							<label for="userLocal">판매지역</label>
							<select class="form-control" name="userLocal" id="userLocal" required="required">
								<option value="기타" selected="selected">기타</option>
								<option value="중구">중구</option>
								<option value="동구">동구</option>
								<option value="미추홀구">미추홀구</option>
								<option value="연수구">연수구</option>
								<option value="남동구">남동구</option>
								<option value="부평구">부평구</option>
								<option value="계양구">계양구</option>
								<option value="서구">서구</option>
								<option value="강화구">강화구</option>
								<option value="옹진구">옹진구</option>
							</select>
						</div>
						<div class="col-10">
							<label for="userPhone">연락처</label>
							<input type="text" class="form-control" name="userPhone" id="userPhone" required="required" placeholder="'-'제외">
						</div>
						<div class="col-12">
							<label for="postcode" style="display: block;">주소</label>
							<input type="text" class="form-control w-50" name="sample4_postcode" placeholder="우편번호" style="display: inline;">
							<input type="button" class="btn btn-primary btn-md" onclick="sample4_execDaumPostcode()"value="우편번호 찾기">
						</div>
						<div class="col-5">
							<input type="text" class="form-control" name="sample4_roadAddress" placeholder="도로명주소">
						</div>
						<div class="col-5">
							<input type="text" class="form-control" name="sample4_jibunAddress" placeholder="지번주소">
						</div>
						<span id="guide" style="color: #999; display: none"></span> 
						<div class="col-5">
							<input type="text" class="form-control" name="sample4_detailAddress" placeholder="상세주소">
						</div>
						<div class="col-5">
							<input type="text" class="form-control" name="sample4_extraAddress" placeholder="참고항목">
						</div>
						<% } %>
						<div class="col-10" style="text-align: center; margin-top: 10px;">
							<input type="hidden" name="MB_LEVEL" value="<%= request.getParameter("MB_LEVEL") %>">
							<input type="submit" class="btn btn-primary btn-md" value="가입완료">
							<input type="reset" class="btn btn-primary btn-md" value="다시 작성하기">
						</div>
					</div>
				</div>
			</form>
		</div>
	</header>
	<%@ include file="ProjFooter.jsp" %>
</body>
<script>
        function checkValue()
        {
            if(!document.memberInfo.userId.value){
                alert("아이디를 입력하세요.");
                return false;
            }
            if(!document.memberInfo.userPw.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            if(document.memberInfo.userPw.value != document.memberInfo.userPwCheck.value){
                alert("비밀번호를 동일하게 입력하세요.");
                return false;
            }
            if(!document.memberInfo.userName.value){
                alert("이름을 입력하세요.");
                return false;
            }
            if (!document.memberInfo.sample4_postcode.value) {
    			alert("주소를 입력하세요.");
    			return false;
    		}
            if(document.memberInfo.userPhone.value.length != 11){
                alert("휴대전화를 11자리숫자로 입력하세요.");
                return false;
            }
        }
        function idCheck(MB_LEVEL) {
    		if (!document.memberInfo.userId.value) {
    			alert("아이디를 입력하세요.");
    		} else {
    			location.href = "joinIdCheck?userId="+ document.memberInfo.userId.value + "&MB_LEVEL=" + MB_LEVEL;
    		}
    	}
        function sample4_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	               
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수

	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }

	                document.memberInfo.sample4_postcode.value= data.zonecode;
	                document.memberInfo.sample4_roadAddress.value = roadAddr;
	                document.memberInfo.sample4_jibunAddress.value= data.jibunAddress;
	                
	                if(roadAddr !== ''){
	                	document.memberInfo.sample4_extraAddress.value = extraRoadAddr;
	                } else {
	                	document.memberInfo.sample4_extraAddress.value.value = '';
	                }
	                
	                var guideTextBox = document.getElementById("guide");
	                
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';

	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
    </script>
</html>