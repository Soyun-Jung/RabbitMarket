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
<link href="form-validation.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet" />
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
body {
	text-align: center;
}table {
	margin: auto;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="HeaderNav.jsp" %>
	<header class="masthead">
		<form action="UserSetModController" onsubmit="return checkValue()" method="POST" name="modForm">
		<h2>내 정보 수정</h2>
		<div class="container" style="text-align: left; position: relative; left: 10%">
			<div class="col-md-8 col-lg-9">
				<div class="row g-3">
					<div class="col-5">
						<label for="userPw">비밀번호</label>
						<input type="password" class="form-control" name="MB_PW_MOD" id="userPw">
					</div>
					<div class="col-5">
						<label for="checkPw">비밀번호 확인</label>
						<input type="password" class="form-control" name="checkPw" id="checkPw">
					</div>
					<div class="col-10">
						<label for="userLocal">판매지역</label>
						<select class="form-control" name="MB_LOC_MOD" id="userLocal">
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
					</div>
					<div class="col-10">
						<label for="userPhone">연락처</label>
						<input type="text" class="form-control" name="MB_PHONE_MOD" id="userPhone" value="${requestScope.MB_PHONE}">
					</div>
					<div class="col-12">
						<label for="postcode" style="display: block;">주소</label>
						<input type="text" class="form-control w-50" name="sample4_postcode" value="${requestScope.MB_POSTCODE}" style="display: inline;">
						<input type="button" class="btn btn-primary btn-md" onclick="sample4_execDaumPostcode()"value="우편번호 찾기">
					</div>
					<div class="col-5">
						<input type="text" class="form-control" name="sample4_roadAddress" value="${requestScope.MB_ROADADDR}">
					</div>
					<div class="col-5">
						<input type="text" class="form-control" name="sample4_jibunAddress" value="${requestScope.MB_JIBUNADDR}">
					</div>
						<span id="guide" style="color: #999; display: none"></span> 
					<div class="col-5">
						<input type="text" class="form-control" name="sample4_detailAddress" value="${requestScope.MB_DETAILADDR}">
					</div>
					<div class="col-5">
						<input type="text" class="form-control" name="sample4_extraAddress" value="${requestScope.MB_EXADDR}">
					</div>
					<div class="col-10" style="text-align: center;">
						<input type="hidden" name="MB_ID" value="${requestScope.MB_ID}">
						<input type="hidden" name="MB_PW" value="${requestScope.MB_PW}">
						<input type="submit" class="btn btn-primary btn-md" value="수정하기">
						<input type="reset" class="btn btn-primary btn-md" value="다시작성">
					</div>
				</div>
			</div>
		</div>
		<table>
			<tbody>
				<c:choose>
					<c:when test="${sessionScope.memberInfo.getMb_level() eq 'M'}">
						<tr>
							<th>등급 :</th>
							<td><select name="MB_LEVEL_MOD">
									<option value="${requestScope.MB_LEVEL}" selected="selected">선택</option>
									<option value="A">일반회원</option>
									<option value="M">관리자</option>
								</select>
							</td>
						</tr>	
					</c:when>
					<c:otherwise>
						<input type="hidden" name="MB_LEVEL_MOD" value="${requestScope.MB_LEVEL}">
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</form>
	</header>
	<%@ include file="ProjFooter.jsp" %>
</body>
<script>
	function checkValue() {
		if (!document.modForm.MB_PW_MOD.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
		if (document.modForm.MB_PW_MOD.value != document.modForm.checkPw.value) {
			alert("비밀번호 확인을 동일하게 입력하세요.");
			return false;
		}
		if (!document.modForm.MB_PHONE_MOD.value) {
			alert("휴대전화를 11자리숫자로 입력하세요.");
			return false;
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

                document.modForm.sample4_postcode.value= data.zonecode;
                document.modForm.sample4_roadAddress.value = roadAddr;
                document.modForm.sample4_jibunAddress.value= data.jibunAddress;
                
                if(roadAddr !== ''){
                	document.modForm.sample4_extraAddress.value = extraRoadAddr;
                } else {
                	document.modForm.sample4_extraAddress.value.value = '';
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