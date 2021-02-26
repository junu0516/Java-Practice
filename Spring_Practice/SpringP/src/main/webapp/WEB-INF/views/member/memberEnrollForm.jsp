<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <!-- 이쪽에 메뉴바 포함 할꺼임 -->
    <jsp:include page="../common/menubar.jsp"/>
    
    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form id="enrollForm" action="insert.me" method="post">
                <div class="form-group">
                    <label for="userId">* ID :</label>
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="Please Enter ID" required>
                    <div id="checkResult" style="display:none; font-size:0.8em"></div>
                    <br>
                    
                    <label for="userPwd">* Password :</label>
                    <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Please Enter Password" required><br>
                    
                    <label for="checkPwd">* Password Check :</label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required><br>
                    
                    <label for="userName">* Name :</label>
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="Please Enter Name" required><br>
                    
                    <label for="email"> &nbsp; Email :</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Please Enter Email"><br>
                    
                    <label for="age"> &nbsp; Age :</label>
                    <input type="number" class="form-control" id="age" name="age" placeholder="Please Enter Age"><br>
                    
                    <label for="phone"> &nbsp; Phone :</label>
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="Please Enter Phone (-없이)"><br>
                    
                    <label for="address"> &nbsp; Address :</label>
                   <%--  <input type="text" class="form-control" id="address" name="address" placeholder="Please Enter Address"><br>--%>
             		<div class="form-inline">
					<label> &nbsp; 우편번호 : &nbsp;</label>
					<input type="text"  name="post" class="form-control mr-2 postcodify_postcode5"  size="6">
					<button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
					</div>
					<br>
					<label> &nbsp; 도로명주소 : </label>
					<input type="text" name="address1" class="form-control postcodify_address" size="30">
					<br>
				    <label> &nbsp; 상세주소 : </label>
					<input type="text" name="address2" class="form-control postcodify_extra_info"  size="30">
					<br>
				
				<!-- jQuery와 Postcodify를 로딩한다. -->
				<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
				<script>
					// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
					$(function(){
						$("#postcodify_search_button").postcodifyPopUp();
					});
				</script>
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Male" value="M">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Female" value="F">
                    <label for="Female">여자</label><br>
                    
                </div>
                <br>
                <div class="btns" align="center">
                    <button type="submit" id="enrollBtn" class="btn btn-primary" >회원가입</button><!-- disabled  중복처리하고나서-->
                    <button type="reset" class="btn btn-danger"> 초기화</button>
                </div>
            </form>
        </div>
        <br><br>
    </div>
    
    <script>
	// 아이디 중복체크 아직 안하는 경우 : 메세지 보여지지 않고 버튼 비활성화
	// 아이디 중복체크 후 사용불가능한 아이디일 경우 : 메세지로 "중복아이디 존재 사용불가능" 띄워주고 버튼 비활성화
	// 아이디 중복체크 후 사용가능한 아이디일 경우 : 메세지로 "사용 가능한 아이디" 띄워주고 버튼 활성화
  
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
    		
    		var $idInput = $("#enrollForm input[name=userId]"); // 아이디 입력하는 input 요소
    		
    		$idInput.keyup(function(){
    			
    			// 아이디는 최소 5글자 ~ 
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

    <!-- 이쪽에 푸터바 포함할꺼임 -->
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>