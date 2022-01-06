<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<link href='../../css/calendar.css' rel='stylesheet' />
<link href='../../css/roomCalendar.css' rel='stylesheet' />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- js -->
<script src='../../js/calendar.js'></script>
<script src='../../js/roomCalendar.js'></script>
</head>
<body>
<div class="cal-container">
	<!-- 숙소 및 날짜(년,월) 리스트 -->
	<div class="cal-wrap">
		<!-- 숙소 리스트, 날짜 수정 -->
		<div class="cal-rooms">
			<div class="rooms-list">
				<select id="rooms" class="form-select" onchange="selectRoom()">
					<c:forEach var="list" items="${roomList}">
						<option value="${list.no}">${list.name}</option>
					</c:forEach>
				</select>
			</div>

			<div class="rooms-date">
				<select id="date" class="form-select">
				</select>
			</div>
		</div>
		
		<!-- 달력 -->
		<div class="cal-content">
			<div id='calendar'></div>
		</div>
	</div>
	
	<!-- 날짜별 수정 div -->
	<div class="cal-info">
		<!-- 여기서 동적생성 -->
		<div class="info-date">
			<c:set var="now" value="<%=new java.util.Date()%>" /> 
			<fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일" var="today" />
			<h1 id="select-day">${today}</h1>
		</div>
		
		<hr>
		
		<div class="info-hosting">
			<span>호스팅 여부 선택</span>
			
			<div class="hosting-btn-wrap">
				<!-- 체크버튼 -->
				<i id="hosting-true" class="bi bi-check-circle" check="0" onclick="clickCheck(this)"></i>
				
				<!-- x버튼 -->
				<i id="hosting-false" class="bi bi-x-circle" check="0" onclick="clickCheck(this)"></i>
			</div>
		</div>
			
		<hr>
		
		<div class="info-price">
			<div class="price-title">
				<span class="tag">숙소 요금 변경</span>

				<span class="day">&nbsp;/ 1박 요금</span>
			</div>
			
			<div class="price-box">
				<input id="roomPirce" numberOnlyMinComma="true" koreanCurrency="true"></input>
			</div>
		</div>
		
		<hr>
		
		<div class="info-btn">
			<button class="btn btn-primary">확인</button>
		</div>
	</div>
</div>
</body>
</html>