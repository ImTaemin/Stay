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
<link rel="stylesheet" href="${root}/css/roomDetail.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

<title>Insert title here</title>
</head>
<body>
<div class="hostRoomList-container">
		<div class="roomPlus">숙소${roomList}"<span class="bi bi-plus-circle"></span> </div>
		
		<c:forEach var="list" items="${roomList}">
								<div class="hostMain-reservation" list-no="${list.name}" onclick="btnClick(this)">
									<%-- <div class="hostMain-number">예약번호 : ${list.resDto.no}</div>
									<div class="hostMain-content">
										<div class="content-name">${list.roomDto.name}
											<div class="content-site">${list.roomDto.addr_load}</div>
										</div>
										<div class="content-checkIn">체크인
											<div class="content-inDate">${list.resDto.start_date}</div>
										</div>
										<div class="content-checkOut">체크아웃
											<div class="content-outDate">${list.resDto.end_date}</div>
										</div>
										<div class="content-guest">게스트
											<div class="content-number">${list.joinDto.count}명</div>
										</div>	
										<div class="content-payment">결제세부정보
											<div class="content-cost">
												<fmt:formatNumber value="${list.resDto.price}" type="currency" currencySymbol="￦"/>
											</div>
										</div>
									</div> --%>
								</div>
					</c:forEach>
</div>
</body>
</html>