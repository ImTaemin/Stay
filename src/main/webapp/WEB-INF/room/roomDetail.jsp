<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" href="${root}/css/roomDetail.css">
<title>Insert title here</title>
</head>
<body>
	<div class="room-detail">
		<!-- 숙소 이름 -->
		<div class="title">
			<label>${roomDto.name}</label>
		</div>
		
		<!-- 숙소 주소 -->
		<div class="addr">
			<label>주소 | ${roomDto.addr_load} ${roomDto.addr_detail}</label>	
		</div>
		
		<!-- 이미지 & 결제 -->
		<div class="photo-reser">
			<div id="slideShow">
				<ul class="slides">
					<c:forEach var="img" items="${roomDto.photos}">
						<li>
							<img src="../photo/roomPhoto/${img}">
						</li>
					</c:forEach>
				</ul>
				
				<p class="controller">
					<!-- &lang: 왼쪽 방향 화살표 &rang: 오른쪽 방향 화살표 -->
					<span class="prev">&lang;</span>
					<span class="next">&rang;</span>
				</p>
			</div>
			
			<div class="reser">
				<div class="price">
					<fmt:formatNumber value="${roomDto.price}" type="currency" currencySymbol="￦"/>
					<label> / 1박</label>			
				</div>
				
			</div>
		</div>
		
		<script src="${root}/js/roomDetail.js"></script>
	</div>
</body>
</html>