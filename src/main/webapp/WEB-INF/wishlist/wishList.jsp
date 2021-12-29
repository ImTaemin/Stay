<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link rel="stylesheet" href="${root}/css/wishList.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<!-- js -->
<!-- 카카오 지도 API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05c1abb954049537a223eedcab5c9b64&libraries=services"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- 타이틀 -->
	<div class="wish-title">
		<div class="heart">
			<i id="title-heart" class="bi bi-heart-fill"></i>
		</div>
		<label>위시리스트</label>
	</div>

	<div class="wish-wrap">
		<!-- 숙소 상세 목록 -->
		<div class="list">
			<c:forEach var="list" items="${roomList}">
				<div class="room-list-wrap" id="${list.addr_load}">
					<div class="photo" onclick="location.href='/room/content?no=${list.no}'">
						<img alt="" src="${root}/photo/roomPhoto/${list.photos}">
					</div>
					
					<div class="content">
						<div class="content-top">
							<b onclick="location.href='/room/content?no=${list.no}'">${list.name}</b>
							
							<c:set var="loop_flag" value="false"/>
							<c:forEach var="wish" items="${wishList}">
								<c:if test="${wish.room_no eq list.no}">
									<i roomID="${list.no}" class="bi bi-heart-fill" onclick="heartClick(this)"></i>
									<c:set var="loop_flag" value="true"/>
								</c:if>
							</c:forEach>
							
							<c:if test="${not loop_flag}">
								<i roomID="${list.no}" class="bi bi-heart" onclick="heartClick(this)"></i>
							</c:if>
						</div>
						
						<div class="content-middle" onclick="location.href='/room/content?no=${list.no}'">
							<label style="margin-top: 10px;">${list.addr_load}</label>
							
							<hr style="margin: 10px 0;">
							
							<label style="font-size: 0.9em;">최대 인원 : ${list.max_per}명</label>
						</div>
						
						<div class="star-cost" onclick="location.href='/room/content?no=${list.no}'">
							<div class="star">
								<label><i class="bi bi-star-fill" style="font-size: 1.5rem;"></i>
<%-- 								<b>${avgRating}</b> ( 후기 ${totalComment}개 )</label> --%>
							</div>
							
							<div class="cost">
								<label>
									<b style="font-size: 1.2em;">
										<fmt:formatNumber value="${list.price}" type="currency" currencySymbol="￦"/>
									</b>
								</label>
							</div>
						</div>
					</div>
				</div>
				
				<hr>
			</c:forEach>
		</div>
		
		<!-- 지도 -->
		<div class="map-wrap">
			<div class="map" id="map"></div>
		</div>
		
		<script src="${root}/js/wishList.js"></script>
		
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

				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);
				
				$('.room-list-wrap').mouseover(function(){
				    var divAddr = $(this).attr("id");
				    
				    marker.setMap(null);
				    
				    // 주소-좌표 변환 객체를 생성합니다.
				    var geocoder = new kakao.maps.services.Geocoder();
					
				    // 주소로 좌표를 검색합니다
				    geocoder.addressSearch(divAddr, function(result, status) {
				    
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
				});
			}
		</script>
	</div>
</body>
</html>