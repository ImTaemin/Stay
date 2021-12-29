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
<link rel="stylesheet" href="../css/accountInsertForm.css">
<body>
	<!-- 제목 -->
	<div class="account-title">
	<h1>💰<b>계좌번호 등록</b>💰</h1>
	</div>
	
	<!-- 추가 폼 -->
	<form action="insert" method="post" class="account-form" enctype="multipart/form-data">
		<!-- 이름 -->
		<div class="account-div">
			<label for="bank">은행</label>
	    	<div>
	    		<input type="text" required id="name" name="bank" placeholder="은행" autofocus="autofocus">
	    	</div>
    	</div>
    	
    	<!-- 번호 -->
    	<div class="account-div">
	    	<label for="num">계좌번호</label>
	    	<div class="detail">
	    		<div style="width: 30%;">
	    			<input type="text" required class="inputs" id="account1" name="account1" placeholder="1234">
	    		</div>
	    		
	    		<div class="de-lable" style="width: 10%; line-height: 30px;">
	    			<label>-</label>
	    		</div>
	    		<div style="width: 30%;">
	    			<input type="text" required class="inputs" id="account2" name="account2" placeholder="1234">
	    		</div>
	    		
	    		<div class="de-lable" style="width: 10%; line-height: 30px;">
	    			<label>-</label>
	    		</div>
	    		
	    		<div style="width: 30%;">
	    			<input type="text" required class="inputs" id="account3" name="account3" placeholder="1234">
	    		</div>
	    	
	    	</div>
		</div>
	    
    	<div class="account-div" style="display: flex; justify-content: center; margin-top: 35px;">
    		<button type="submit" id="account-insert" class="btn btn-info">계좌 등록</button>
    	</div>
	</form>
</body>
</html>