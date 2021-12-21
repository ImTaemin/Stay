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
<link rel="stylesheet" href="${root}/css/roomUpdateForm.css">
<!-- js -->
<script src="${root}/js/roomUpdateForm.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
	<!-- 제목 -->
	<div class="room-title">
		<h1>🏠<b> 숙소 수정하기 </b>🏠</h1>
	</div>
	
	<!-- 추가 폼 -->
	<form action="update" method="post" class="room-from" name="updateForm" enctype="multipart/form-data">
		<!-- hidden -->
		<input type="hidden" name="no" value="${roomDto.no}">
		
		<!-- 이름 -->
		<div class="room-div">
			<label for="name">숙소 이름</label>
	    	<div>
	    		<input type="text" required id="name" name="name" value="${roomDto.name}" placeholder="숙소 이름" autofocus="autofocus">
	    	</div>
    	</div>
    	
    	<!-- 타입 -->
    	<div class="room-div">
	    	<label for="type">숙소 타입</label>
	    	<div>
	    		<select id="type" name="type">
	    			<option value="아파트" ${roomDto.type == '아파트' ? 'selected="selected"' : ''}>아파트</option>
	    			<option value="주택" ${roomDto.type == '주택' ? 'selected="selected"' : ''}>주택</option>
	    			<option value="펜션" ${roomDto.type == '펜션' ? 'selected="selected"' : ''}>펜션</option>
	    			<option value="민박" ${roomDto.type == '민박' ? 'selected="selected"' : ''}>민박</option>
	    		</select>
	    	</div>
		</div>
    	
    	<!-- 주소 -->
    	<div class="room-div">
		   	<label>숙소 주소</label>
	    	<div>
				<input type="text" name="addr_load" id="sample4_roadAddress" onclick="sample4_execDaumPostcode()" placeholder="도로명주소" readonly="readonly"
				style="margin-bottom: 10px;" value="${roomDto.addr_load}">
				<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" size="60">
				<span id="guide" style="color:#999; display:none"></span>
				<input type="text" name="addr_detail" id="sample4_detailAddress" value="${roomDto.addr_detail}" placeholder="상세주소" size="60">
				<input type="hidden" id="sample4_extraAddress" placeholder="참고항목" size="60">
				<input type="hidden" id="sample4_engAddress" placeholder="영문주소" size="60">
	    	</div>
	    </div>
    	
    	<!-- 인원 -->
    	<div class="room-div">
	    	<label for="max_per">최대 숙박 인원</label>
	    	<div class="detail">
	    		<div style="width: 90%;">
	    			<input type="text" required id="max_per" name="max_per" placeholder="최대 숙박 인원 수" value="${roomDto.max_per}">
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
    			<textarea required id="content" name="content" wrap="hard">${roomDto.content}</textarea>
    		</div>
    	</div>
    	
    	<!-- 가격 -->
    	<div class="room-div">
	    	<label for="price">숙소 가격</label>
	    	<div class="detail">
	    		<div style="width: 90%;">
	    			<input type="text" required id="price" name="price" placeholder="숙소 가격" value="${roomDto.price}">
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
			<input type="checkbox" id="hosting" name="hosting" onclick="checkHosting(this)" value="1"
			${roomDto.hosting == true ? 'checked="checked"' : ''}>
   			<label>YES</label>
   			<label></label>
			<input type="checkbox" id="hosting" name="hosting" onclick="checkHosting(this)" value="0"
			${roomDto.hosting == false ? 'checked="checked"' : ''}>
			<label>NO</label>
	    </div>
	    
	    <!-- 이미지 -->
	    <div class="room-div">
    		<label for="upload">숙소 상세 이미지</label>
    		<div>
    			<input type="file" required id="upload" name="upload" accept=".gif, .jpg, .png" multiple="multiple" style="padding-top: 6px;" maxlength="5">
    		</div>
    		
    		<div id="preview"></div>
    	</div>
    	
    	<div class="room-div" style="display: flex; justify-content: center; margin-top: 35px;">
    		<button type="button" id="room-insert" class="btn btn-info">숙소 수정</button>
    	</div>
	</form>
</body>
</html>