<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="https://fonts.googleapis.com/css2?family=Dokdo&family=Gaegu&family=
Gugi&family=Nanum+Pen+Script&display=Nanum+Gothic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- css -->
<link rel="stylesheet" href="${root}/css/hostRoomList.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

<title>Insert title here</title>
</head>
<body>
<div class="hostRoomList-container">

		<div class="roomPlus">숙소<span class="bi bi-plus-circle" onclick="location.href='insertform'"></span></div>
		
		<c:forEach var="list" items="${roomList}">
			<div class="roomList">
				<div class="room-name">${list.name}
					<div class="room-type">${list.type}</div>
				</div>
				<div class="room-addr">${list.addr_load}</div>
			    <div class="room-maxPer">최대인원
					<div class="room-maxPerData">${list.max_per}명</div>
				</div>
				<div class="room-price">가격
					<div class="room-priceData">
						<fmt:formatNumber value="${list.price}" type="currency" currencySymbol="￦"/>
					</div>
				</div>
				<div class="glyphicon glyphicon-pencil update" onclick="location.href='updateform?no=${list.no}'"></div>
			</div>
		</c:forEach>
		
</div>
</body>
</html>