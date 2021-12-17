<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!-- css -->
<link rel="stylesheet" href="${root}/css/roomInsertForm.css">
<!-- js -->
<script src="${root}/js/roomInsertForm.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
	<!-- 제목 -->
	<div class="room-title">
		<h1>🏠<b> 새로운 숙소 등록하기 </b>🏠</h1>
	</div>
	
	<!-- 추가 폼 -->
	<form action="insert" method="post" class="room-from" enctype="multipart/form-data">
		<!-- 이름 -->
		<div class="room-div">
			<label for="name">숙소 이름</label>
	    	<div>
	    		<input type="text" required id="name" name="name" placeholder="숙소 이름" autofocus="autofocus">
	    	</div>
    	</div>
    	
    	<!-- 타입 -->
    	<div class="room-div">
	    	<label for="type">숙소 타입</label>
	    	<div>
	    		<select id="type" name="type">
	    			<option value="아파트" selected="selected">아파트</option>
	    			<option value="주택">주택</option>
	    			<option value="펜션">펜션</option>
	    			<option value="펜션">민박</option>
	    		</select>
	    	</div>
		</div>
    	
    	<!-- 주소 -->
    	<div class="room-div">
		   	<label for="addr">숙소 주소</label>
	    	<div>
				<input type="text" id="sample4_roadAddress" onclick="sample4_execDaumPostcode()" placeholder="도로명주소" readonly="readonly"
				style="margin-bottom: 10px;">
				<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" size="60">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" placeholder="상세주소" size="60">
				<input type="hidden" id="sample4_extraAddress" placeholder="참고항목" size="60">
				<input type="hidden" id="sample4_engAddress" placeholder="영문주소" size="60">
	    	</div>
	    </div>
    	
    	<!-- 인원 -->
    	<div class="room-div">
	    	<label for="people">최대 숙박 인원</label>
	    	<div class="detail">
	    		<div style="width: 90%;">
	    			<input type="text" required id="people" name="people" placeholder="최대 숙박 인원 수">
	    		</div>
	    		<div class="de-lable">
	    			<label>명</label>
	    		</div>
	    	</div>
	    </div>
    	
    	<!-- 소개 -->
    	<div class="room-div">
    		<label for="content">숙소 소개</label>
    		<div>
    			<textarea required id="content" name="content"></textarea>
    		</div>
    	</div>
    	
    	<!-- 가격 -->
    	<div class="room-div">
	    	<label for="price">숙소 가격</label>
	    	<div class="detail">
	    		<div style="width: 90%;">
	    			<input type="text" required id="price" name="price" placeholder="숙소 가격">
	    		</div>
	    		<div class="de-lable">
	    			<label>원</label>
	    		</div>
	    	</div>
	    </div>
	    
	    <!-- 호스팅 여부 -->
	    <div class="room-div">
			<label for="hosting">호스팅 여부</label>
    	</div>
    	<div class="room-check">
			<input type="checkbox" id="hosting" name="hosting" onclick="checkHosting(this)" value="yes" checked="checked">
   			<label>YES</label>
   			<label></label>
			<input type="checkbox" id="hosting" name="hosting" onclick="checkHosting(this)" value="no">
			<label>NO</label>
	    </div>
	    
	    <!-- 이미지 -->
	    <div class="room-div">
    		<label for="photos">숙소 상세 이미지</label>
    		<div>
    			<input type="file" required id="photos" name="photos" accept=".gif, .jpg, .png" multiple="multiple" style="padding-top: 6px;" maxlength="5">
    		</div>
    		
    		<div id="preview"></div>
    	</div>
    	
    	<div class="room-div">
    		<button type="submit" id="room-insert" class="btn btn-info">숙소 추가</button>
    	</div>
	</form>
</body>
</html>