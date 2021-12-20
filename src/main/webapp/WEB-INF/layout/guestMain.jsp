<%@page import="data.dto.RoomDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- css -->
<link rel="stylesheet" href="../css/guestMain.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<div class="guestMain-top">
		<!-- 검색핉터창 -->
		<div class="guestMain-filter">
			<div class="guestMain-map"><button type="button">위치 검색</button>  |  </div>
			<div class="guestMain-start"><button type="button">시작 날짜</button>  |  </div>
			<div class="guestMain-end"><button type="button">종료 날짜</button></div>
		</div>
		<div class="glyphicon glyphicon-search guestMain-search"></div>
	</div>
	
	<div class="guestMain-bottom">
		<!-- 인기숙소 -->
		<div class="guestMain-toproom">인기 숙소</div>
		<div class="guestMain-list">
			<div class="glyphicon glyphicon-chevron-left"></div>
			<div class="guestMain-info">
				<div class="guestMain-room">
					<div class="guestMain-image"><img alt="" src="../photo/숙소1_1.jpg"></div>
					<div class="guestMain-detail">
						<div class="guestMain-name">숙소이름</div>
						<div class="guestMain-site">숙소위치</div>
					</div>
				</div>
				<div class="guestMain-room">
					<div class="guestMain-image"><img alt="" src="../photo/숙소1_1.jpg"></div>
					<div class="guestMain-detail">
						<div class="guestMain-name">숙소이름</div>
						<div class="guestMain-site">숙소위치</div>
					</div>
				</div>
				<div class="guestMain-room">
					<div class="guestMain-image"><img alt="" src="../photo/숙소1_1.jpg"></div>
					<div class="guestMain-detail">
						<div class="guestMain-name">숙소이름</div>
						<div class="guestMain-site">숙소위치</div>
					</div>
				</div>
				<div class="guestMain-room">
					<div class="guestMain-image"><img alt="" src="../photo/숙소1_1.jpg"></div>
					<div class="guestMain-detail">
						<div class="guestMain-name">숙소이름</div>
						<div class="guestMain-site">숙소위치</div>
					</div>
				</div>
			</div>
			<div class="glyphicon glyphicon-chevron-right"></div>
		</div>
	</div>
</body>
</html>