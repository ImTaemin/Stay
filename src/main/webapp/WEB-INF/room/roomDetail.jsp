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
<!-- js -->
<!-- 카카오 지도 API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05c1abb954049537a223eedcab5c9b64&libraries=services"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="reservation" method="post">
		<div class="room-detail">
			<c:set var="addr" value="${roomDto.addr_load} ${roomDto.addr_detail}"></c:set>
			
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
					
					<div class="calendar">
						<div class="check-date">
							<div class="check-in-label">
								<label>체크인</label>
								<br>
								<input type="date">
							</div>
							
							<div class="check-out-label">
								<label>체크아웃</label>
								<br>
								<input type="date">
							</div>
						</div>
						
						<div class="reser-btn">
							<button type="submit" id="reserBtn" class="btn btn-primary">숙소 예약하기</button>
						</div>
					</div>
				</div>
			</div>
			
			<hr>
			
			<!-- 설명&지도 -->
			<div class="content-map">
				<!-- 설명 -->
				<div class="content">
					<label class="title">숙소 상세 설명</label>
					<label>${roomDto.content}</label>
				</div>
				
				<!-- 지도 -->
				<div class="map-wrap">
					<div class="map" id="map"></div>
				</div>
			</div>
			
			<hr>
			
			<script src="${root}/js/roomDetail.js"></script>
		</div>
	</form>
	
	<!-- 마커 JS -->
	<script type="text/javascript">
		window.onload = function() {
			// 마커
			var imageSrc = '../../photo/mapMarker.png', // 마커이미지의 주소입니다    
				imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
				imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

			// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
				markerPosition = new kakao.maps.LatLng(37.498095, 127.027610); // 마커가 표시될 위치입니다

			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				position: markerPosition,
				image: markerImage // 마커이미지 설정 
			});

			var addr = '<c:out value="${addr}"/>';
		    
		    // 주소-좌표 변환 객체를 생성합니다.
		    var geocoder = new kakao.maps.services.Geocoder();
			
		    // 주소로 좌표를 검색합니다
		    geocoder.addressSearch(addr, function(result, status) {
		    
		    	// 정상적으로 검색이 완료됐으면 
		    	if (status === kakao.maps.services.Status.OK) {
		    		var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		    		
		    		markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
					markerPosition = new kakao.maps.LatLng(result[0].y, result[0].x);
		    		
		    		// 결과값으로 받은 위치를 마커로 표시합니다
					marker = new kakao.maps.Marker({
						map: map,
						position: coords,
						image: markerImage // 마커이미지 설정 
		            });
		    		
		    		// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		    		map.setCenter(coords);
		        }
		    });
		}
	</script>
</body>
</html>