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
	<!-- μ λͺ© -->
	<div class="room-title">
		<h1>π <b> μμ μμ νκΈ° </b>π </h1>
	</div>
	
	<!-- μΆκ° νΌ -->
	<form action="update" method="post" class="room-from" name="updateForm" enctype="multipart/form-data">
		<!-- hidden -->
		<input type="hidden" name="no" value="${roomDto.no}">
		
		<!-- μ΄λ¦ -->
		<div class="room-div">
			<label for="name">μμ μ΄λ¦</label>
	    	<div>
	    		<input type="text" required id="name" name="name" value="${roomDto.name}" placeholder="μμ μ΄λ¦" autofocus="autofocus">
	    	</div>
    	</div>
    	
    	<!-- νμ -->
    	<div class="room-div">
	    	<label for="type">μμ νμ</label>
	    	<div>
	    		<select id="type" name="type">
	    			<option value="μννΈ" ${roomDto.type == 'μννΈ' ? 'selected="selected"' : ''}>μννΈ</option>
	    			<option value="μ£Όν" ${roomDto.type == 'μ£Όν' ? 'selected="selected"' : ''}>μ£Όν</option>
	    			<option value="νμ" ${roomDto.type == 'νμ' ? 'selected="selected"' : ''}>νμ</option>
	    			<option value="λ―Όλ°" ${roomDto.type == 'λ―Όλ°' ? 'selected="selected"' : ''}>λ―Όλ°</option>
	    		</select>
	    	</div>
		</div>
    	
    	<!-- μ£Όμ -->
    	<div class="room-div">
		   	<label>μμ μ£Όμ</label>
	    	<div>
				<input type="text" name="addr_load" id="sample4_roadAddress" onclick="sample4_execDaumPostcode()" placeholder="λλ‘λͺμ£Όμ" readonly="readonly"
				style="margin-bottom: 10px;" value="${roomDto.addr_load}">
				<input type="hidden" id="sample4_jibunAddress" placeholder="μ§λ²μ£Όμ" size="60">
				<span id="guide" style="color:#999; display:none"></span>
				<input type="text" name="addr_detail" id="sample4_detailAddress" value="${roomDto.addr_detail}" placeholder="μμΈμ£Όμ" size="60">
				<input type="hidden" id="sample4_extraAddress" placeholder="μ°Έκ³ ν­λͺ©" size="60">
				<input type="hidden" id="sample4_engAddress" placeholder="μλ¬Έμ£Όμ" size="60">
	    	</div>
	    </div>
    	
    	<!-- μΈμ -->
    	<div class="room-div">
	    	<label for="max_per">μ΅λ μλ° μΈμ</label>
	    	<div class="detail">
	    		<div style="width: 90%;">
	    			<input type="text" required id="max_per" name="max_per" placeholder="μ΅λ μλ° μΈμ μ" value="${roomDto.max_per}">
	    		</div>
	    		<div class="de-lable">
	    			<label>λͺ</label>
	    		</div>
	    	</div>
	    </div>
    	
    	<!-- μκ° -->
    	<div class="room-div">
    		<label for="content">μμ μκ°</label>
    		<div>
    			<textarea required id="content" name="content" wrap="hard">${roomDto.content}</textarea>
    		</div>
    	</div>
    	
    	<!-- κ°κ²© -->
    	<div class="room-div">
	    	<label for="price">μμ κ°κ²©</label>
	    	<div class="detail">
	    		<div style="width: 90%;">
	    			<input type="text" required id="price" name="price" placeholder="μμ κ°κ²©" value="${roomDto.price}">
	    		</div>
	    		<div class="de-lable">
	    			<label>μ</label>
	    		</div>
	    	</div>
	    </div>
	    
	    <!-- νΈμ€ν μ¬λΆ -->
	    <div class="room-div">
			<label for="hosting">νΈμ€ν μ¬λΆ</label>
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
	    
	    <!-- μ΄λ―Έμ§ -->
	    <div class="room-div">
    		<label for="upload">μμ μμΈ μ΄λ―Έμ§</label>
    		<div>
    			<input type="file" required id="upload" name="upload" accept=".gif, .jpg, .png" multiple="multiple" style="padding-top: 6px;" maxlength="5">
    		</div>
    		
    		<div id="preview"></div>
    	</div>
    	
    	<div class="room-div" style="display: flex; justify-content: center; margin-top: 35px;">
    		<button type="button" id="room-insert" class="btn btn-info">μμ μμ </button>
    	</div>
	</form>
</body>
</html>