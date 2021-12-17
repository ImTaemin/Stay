<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<!-- css -->
<link rel="stylesheet" href="../css/cardInsertForm.css">
<!-- js -->
<script type="text/javascript">
	$(function() {
	    $(".inputs").keyup (function () {
	        var charLimit = $(this).attr("maxlength");
	        if (this.value.length >= charLimit) {
	            $(this).next('.inputs').focus();
	            return false;
	        }
	    });
	});
</script>
<body>
	<!-- 제목 -->
	<div class="card-title">
		<h1>💳<b> 카드 추가하기 </b>💳</h1>
	</div>
	
	<!-- 추가 폼 -->
	<form action="insert" method="post" class="card-from" enctype="multipart/form-data">
		<!-- 이름 -->
		<div class="card-div">
			<label for="name">카드 이름</label>
	    	<div>
	    		<input type="text" required id="name" name="name" placeholder="카드 이름" autofocus="autofocus">
	    	</div>
    	</div>
    	
    	<!-- 번호 -->
    	<div class="card-div">
	    	<label for="num">카트 번호</label>
	    	<div class="detail">
	    		<div style="width: 20%;">
	    			<input type="text" required class="inputs" id="num1" name="num1" placeholder="1234" maxlength="4">
	    		</div>
	    		
	    		<div class="de-lable" style="width: 10%; line-height: 30px;">
	    			<label>-</label>
	    		</div>
	    		<div style="width: 20%;">
	    			<input type="text" required class="inputs" id="num2" name="num2" placeholder="1234" maxlength="4">
	    		</div>
	    		
	    		<div class="de-lable" style="width: 10%; line-height: 30px;">
	    			<label>-</label>
	    		</div>
	    		
	    		<div style="width: 20%;">
	    			<input type="password" required class="inputs" id="num3" name="num3" placeholder="****" maxlength="4"
	    			style="padding-top: 5px;">
	    		</div>
	    		<div class="de-lable" style="width: 10%; line-height: 30px;">
	    			<label>-</label>
	    		</div>
	    		
	    		<div style="width: 20%;">
	    			<input type="password" required class="inputs" id="num4" name="num4" placeholder="****" maxlength="4"
	    			style="padding-top: 5px;">
	    		</div>
	    	</div>
		</div>
    	
    	<!-- 유효기간 -->
    	<div class="card-div">
		   	<label for="end-date">유효 기간</label>
	    	<div>
	    		<input type="date" required id="end-date" name="end-date">
	    	</div>
	    </div>
    	
    	<!-- CVC -->
    	<div class="card-div">
	    	<div class="cvc-pass">
	    		<label for="cvc">CVC</label>
		    	<div>
		    		<input type="text" required id="cvc" name="cvc" placeholder="CVC" maxlength="3">
		    	</div>
		    	
		    	<div style="width: 5%;"></div>
		    	
		    	<label for="pass">비밀번호</label>
	    		<div class="detail" style="display: flex;">
		    		<div style="width: 50%;">
		    			<input type="password" required id="pass" name="pass" placeholder="앞 2자리" maxlength="2">
		    		</div>
		    		<div class="de-lable" style="width: 50%; padding-top: 10px;">
		    			<label>**</label>
		    		</div>
	    		</div>
	    	</div>
	    </div>
	    
    	<div class="card-div" style="display: flex; justify-content: center; margin-top: 35px;">
    		<button type="submit" id="card-insert" class="btn btn-info">카드 추가</button>
    	</div>
	</form>
</body>
</html>