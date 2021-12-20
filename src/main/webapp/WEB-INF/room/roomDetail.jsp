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
		<div class="title">
			<label>${roomDto.name}</label>
		</div>
		
		<div class="room-photo">
			<c:forEach var="photo" items="${photoList}">
<!-- 				<img alt="" src="../photo/"> -->
			</c:forEach>
		</div>
	</div>
</body>
</html>