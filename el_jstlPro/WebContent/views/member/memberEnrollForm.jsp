<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<style>
	.container{
		margin-top : 40px;
	}

</style>
</head>
<body>
	<%@ include file = "/views/common/menubar.jsp" %>
	<div class="container">
		<h2><b>회원가입</b></h2>
		<hr><br>
		<form id="enrollForm" method="POST" class="was-validated"> <!-- 모든 항목을 제대로 입력했는지 확인 -->
			<!-- was-validated : form submit하기 전 유효한지 점검 / 유효하지 않은 형식에 대해 빨간 표시 -->
			<!-- 아래부터 차례로 아이디, 비밀번호, 비밀번호 확인, 이름, 나이, 전화번호, 이메일을 각각 입력할 form-group div -->
			<!-- 모두 required 처리해서 필수적으로 입력하도록 하였음 -->
			<!-- form-group : 하위 label, input 등을 form-group으로 그룹화 후, form-group 간 아래 15px 여백 자동 처리 -->
			<!-- form-control : input 크기를 full width로 지정 -->
			<div class="form-group">
				<label for="id">아이디 : </label>
				<input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요" name="id" required>
				<div id="checkResult" style="display:none; font-size=0.8em"></div> <!-- 아이디 중복 체크 결과 나타낼 부분 -->
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호 : </label>
				<input type="password" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요" name="pwd" required>
			</div>
			<div class="form-group">
				<label for="pwd2">비밀번호 확인 : </label>
				<input type="password" class="form-control" id="pwd2" placeholder="비밀번호를 확인" name="pwd2" required>
			</div>
			<div class="form-group">
				<label for="userName">이름 : </label>
				<input type="text" class="form-control" id="userName" placeholder="이름을 입력하세요" name="userName" required>
			</div>
			<div class="form-group">
				<label for="age">나이 : </label>
				<input type="number" class="form-control" id="age" placeholder="나이는 숫자로 입력하세요" name="age" required>
			</div>
			<div class="form-group">
				<label for="phone">전화번호 : </label>
				<input type="number" class="form-control" id="phone" placeholder="-없이 숫자로만 입력해주세요" name="phone" required>
			</div>
			<div class="form-group">
				<label for="email">이메일 : </label>
				<input type="email" class="form-control" id="email" placeholder="이메일을 입력해주세요" name="email" required>
			</div>
			
			<label for="address"> &nbsp; 주소 :</label>
            <div class="form-inline">
				<label> &nbsp; 우편번호 : &nbsp;</label>
				<!-- mr : margin-right 간격 -->
				<!-- 클래스명 postcodify_postcode5 : 새 우편번호 -->
				<input type="text"  name="post" class="form-control mr-2 postcodify_postcode5" value="${ post }" size="6" required>
				<button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
			</div>
			<br>
			<label> &nbsp; 도로명주소 : </label>
			<!-- 클래스명 postcodify_address : 도로명 주소 -->
			<!-- 클래스명 미작성시, input value로 값을 넣어주지 않음 -->
			<input type="text" name="address1" class="form-control postcodify_address" value="${ address1 }" size="30" required>
			<br>
			<label> &nbsp; 상세주소 : </label>
			<!-- 클래스명 postcodify_extra_info : 참고항목(법정동, 건물명 등) / 상세주소 -->
			<input type="text" name="address2" class="form-control postcodify_extra_info" value="${ address2 }" size="30" required>
			<br>
			
			<!-- jQuery와 Postcodify를 로딩한다. -->
			<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script> <!--postcodify api 설치 -->
			<script>
				// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
				$(function(){
					$("#postcodify_search_button").postcodifyPopUp(); //postcodifyPopUp()은 postcodify api에서 제공되는 메소드
				});
			</script>
		
			<button type="submit" id="enrollBtn" class="btn btn-primary">가입하기</button>
			<button type="button" class="btn btn-danger" onclick="goBack();">취소하기</button>
		</form>
		
		<script>
			function goBack(){ //취소하기 버튼 누르면 뒤로가기 처리하였음
				alert("뒤로 가기");
				window.history.back();
			}
			
			//중복체크 여부에 따라 #enrollBtn 버튼의 disabled 여부 결정
			function idCheckValidate(num){ 
	    		
	    		if(num == 0){ // 아직 중복체크를 하지 않는 경우 (아이디 적어도 5글자 이상은 되어야만 본격적으로 중복체크할 꺼임)
	    			$("#checkResult").hide();
	    			$("#enrollBtn").attr("disabled", true);
	    			
	    		}else if(num == 1){ // 중복된 아이디가 존재할 경우 
	    			$("#checkResult").css("color", "red").text("중복된 아이디가 존재합니다. 사용이 불가능합니다.");
	    			$("#checkResult").show();
	    			$("#enrollBtn").attr("disabled", true);
	    			
	    		}else if(num == 2){ // 중복된 아이디가 존재하지 않을 경우 ->사용 가능
	    			$("#checkResult").css("color", "green").text("사용 가능한 아이디입니다. ");
	    			$("#checkResult").show();
	    			$("#enrollBtn").removeAttr("disabled");
	    			
	    		}
	    	}
	  
	    	$(function(){
	    		
	    		var $idInput = $("#enrollForm input[name=id]"); // 아이디 입력하는 input 요소(input 태그의 name=id인 요소에 접근)
	    		
	    		$idInput.keyup(function(){
	    			
	    			// 아이디는 최소 5글자인 경우에 비동기 통신 실행
	    			if($idInput.val().length >= 5){ // 5글자 이상되었을 때 본격적으로 중복체크
	    				
	   					$.ajax({
	   						url:"idCheck.me",
	   						data:{userId:$idInput.val()},
	   						type:"post",
	   						success:function(result){
	   							if(result > 0){ //   중복된 아이디 존재
	   								idCheckValidate(1);
	   							}else{
	   								idCheckValidate(2);
	   							}
	   						},error:function(){
	   							console.log("아이디 중복체크용 ajax 통신 실패");
	   						}
	   					});
	   					
	    			}else{
	    				idCheckValidate(0);
	    			}
	    			
	    		});
	    	});
		</script>
		
	</div>
</body>
</html>