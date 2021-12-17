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
<link rel="stylesheet" href="${root}/css/roomMain.css">
<!-- js -->
<!-- 카카오 지도 API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	05c1abb954049537a223eedcab5c9b64"></script>
</head>
<body>
	<div class="room-main-wrap">
		<!-- 숙소 상세 목록 -->
		<div class="list">
			${totalCount}
		</div>
		
<!-- 		<!-- 지도 --> -->
<!-- 		<div class="map-wrap"> -->
<!-- 			<div class="map" id="map"></div> -->
<!-- 		</div> -->
		
<%-- 		<script src="${root}/js/roomMain.js"></script> --%>
	</div>
</body>
</html>