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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<!-- js -->
<!-- 카카오 지도 API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05c1abb954049537a223eedcab5c9b64&libraries=services"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="/pay/paymentform" method="post">
		<!-- hidden -->
		<input type="hidden" name="roomNo" value="${roomDto.no}">
		<input type="hidden" name="startDate">
		<input type="hidden" name="endDate">
		<input type="hidden" name="betweenDay">
		<input type="hidden" name="roomPrice">
		<input type="hidden" name="allPrice">
		
		<div class="room-detail">
			<c:set var="roomAddr" value="${roomDto.addr_load} ${roomDto.addr_detail}"></c:set>
			<c:set var="roomPrice" value="${roomDto.price}"></c:set>
			
			<!-- 숙소 이름 -->
			<div class="title-heart">
				<div class="title">
					<label>${roomDto.name}</label>
				</div>
				
				<c:set var="loop_flag" value="false"/>
				<c:forEach var="wish" items="${wishList}">
					<c:if test="${wish.room_no eq roomDto.no}">
						<i roomID="${roomDto.no}" class="bi bi-heart-fill" onclick="heartClick(this)"></i>
						<c:set var="loop_flag" value="true"/>
					</c:if>
				</c:forEach>
				
				<c:if test="${not loop_flag}">
					<i roomID="${roomDto.no}" class="bi bi-heart" onclick="heartClick(this)"></i>
				</c:if>
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
				
				<div class="price-cal">
					<div class="price">
						<fmt:formatNumber value="${roomDto.price}" type="currency" currencySymbol="￦"/>
						<label> / 1박</label>			
					</div>
					
					<div class="calendar">
						<div class="check-date">
							<div class="check-in-label">
								<label>체크인</label>
								<br>
								
								<jsp:useBean id="now" class="java.util.Date"/>
								<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
								
								<input type="date" id="check-in" min="${today}">
							</div>
							
							<div class="check-out-label">
								<label>체크아웃</label>
								<br>
								
								<input type="date" id="check-out" min="${today}">
							</div>
						</div>
						
						<div class="cost-btn">
							<button type="button" id="cost-btn" class="btn btn-primary">금액 확인하기</button>
						</div>
					</div>
					
					<div class="reser"></div>
					
					<script type="text/javascript">
						// 날짜 비교
						$(function() {
							$("#cost-btn").click(function() {
								var startDate = $('#check-in').val();
								var endDate = $('#check-out').val();
	
								//-을 구분자로 연,월,일로 잘라내어 배열로 반환
								var startArray = startDate.split('-');
								var endArray = endDate.split('-');
	
								//배열에 담겨있는 연,월,일을 사용해서 Date 객체 생성
								var start_date = new Date(startArray[0], startArray[1], startArray[2]);
								var end_date = new Date(endArray[0], endArray[1], endArray[2]);
								
								var s = "";
								
								//날짜를 숫자형태의 날짜 정보로 변환하여 비교한다.
								if (start_date.getTime() == end_date.getTime()) {
									Swal.fire({
										icon: 'error',
										title: '날짜를 다시 선택해주세요.',
										text: '체크인 날짜와 체크아웃 날짜가 동일합니다.'
									});
									
									s = "";
									$(".reser").html(s);
	
									document.getElementById("check-out").value = '';
	
									return false;
								} else if (start_date.getTime() > end_date.getTime()) {
									Swal.fire({
										icon: 'error',
										title: '날짜를 다시 선택해주세요.',
										text: '체크아웃 날짜보다 체크인 날짜가 작아야합니다.'
									});
									
									s = "";
									$(".reser").html(s);
	
									document.getElementById("check-in").value = '';
									document.getElementById("check-out").value = '';

									return false;
								} else if (document.getElementById("check-in").value == '') {
									Swal.fire({
										icon: 'error',
										title: '날짜를 다시 선택해주세요.',
										text: '체크인 날짜을 입력해주세요.'
									});
									
									s = "";
									$(".reser").html(s);
								} else if (document.getElementById("check-out").value == '') {
									Swal.fire({
										icon: 'error',
										title: '날짜를 다시 선택해주세요.',
										text: '체크아웃 날짜을 입력해주세요.'
									});
									
									s = "";
									$(".reser").html(s);
								} else {
									var betweenMs = end_date.getTime() - start_date.getTime();
									var betweenDay = betweenMs / (1000 * 60 * 60 * 24);
									
									var roomPrice = '<c:out value="${roomPrice}"/>';
									var calPrice = roomPrice * betweenDay;
									var commaCal = Number(calPrice).toLocaleString();
									
									var taxPrice = calPrice * 0.2;
									var commaTax = Number(taxPrice).toLocaleString();

									var allPrice = calPrice + taxPrice;
									var commaAll = Number(allPrice).toLocaleString();
									
									$('input[name=startDate]').attr('value', startDate);
									$('input[name=endDate]').attr('value', endDate);
									$('input[name=betweenDay]').attr('value', betweenDay);
									$('input[name=roomPrice]').attr('value', roomPrice);
									$('input[name=allPrice]').attr('value', allPrice);
									
									s += "<div class='reser-price'>";
									s += "<div class='room-price'>";
									s += "<div><fmt:formatNumber value='${roomDto.price}' type='currency' currencySymbol='￦'/> X " + betweenDay + "박</div>";
									s += "<div><b>￦" + commaCal + "</b></div>";
									s += "</div>";
									s += "<div class='tax-price'>";
									s += "<div>수수료 및 부과세</div>";
									s += "<div><b>￦" + commaTax + "</b></div>";
									s += "</div>";
									s += "<hr>"
									s += "<div class='all-price'>"
									s += "<div><b>총 합계</b></div>";
									s += "<div><b>￦" + commaAll + "</b></div>";
									s += "</div>";
									s += "<div class='price-btn'>"
									s += "<c:if test='${roomDto.host_id != myid}'>"
									s += "<button class='btn btn-primary' type='submit' id='reser-btn'>예약하기</button>"
									s += "</c:if>"
									s += "</div>";
									s += "</div>";
									
									$(".reser").html(s);
								}
							});
						});
					</script>
				</div>
			</div>
			
			<hr>
			
			<!-- 설명&지도 -->
			<div class="content-map">
				<!-- 설명 -->
				<div class="content-wrap">
					<div class="maxper-type">
						<div class="content-detail">
							<i class="bi bi-person-fill"></i>
							<label class="title">최대 인원</label>
							<label class="content">${roomDto.max_per}명</label>
						</div>
						
						<div class="content-detail">
							<i class="bi bi-house-fill"></i>
							<label class="title">숙소 타입</label>
							<label class="content">${roomDto.type}</label>
						</div>
					</div>
					
					<div class="content-detail">
						<label class="title" style="margin-bottom: 15px;">
							<i class="bi bi-journal-text" style="margin-right: 5px;"></i>
							숙소 설명
						</label>
						<label class="content">${roomDto.content}</label>
					</div>
				</div>
				
				<!-- 지도 -->
				<div class="map-wrap">
					<i class="bi bi-pin-map-fill"></i>
					<label class="title" style="margin-bottom: 15px;">숙소 호스팅 지역</label>
					<div class="map" id="map"></div>
				</div>
			</div>
			
			<hr>
			
			<!-- 후기 -->
			<div class="comment-wrap">
				<div class="comment-title">
					<i class="bi bi-star-fill" style="font-size: 2.0rem;"></i>
					<label class="title">${avgRating} 점  |  후기  ${totalComment} 개</label>
				</div>
				
				<div class="comment">
					<c:if test="${totalComment == 0}">
						<div>등록된 후기가 없습니다.</div>
					</c:if>
					
					<c:if test="${totalCount > 0}">
						
					</c:if>
				</div>
			</div>
			
			<hr>
			
			<!-- 호스트 정보 -->
			<div class="host-wrap">
				<div class="photo">
					<img alt="" src="../photo/memberPhoto/${memDto.photo}">
				</div>
				
				<div class="content">
					<label class="name">호스트 : ${memDto.id} 님</label>
					<br>
					<label class="email">${memDto.e_mail}</label>
				</div>
				
				<div class="message">
					<button type="button" class="btn btn-default" id="message-btn">호스트에게 연락하기</button>
				</div>
			</div>
			
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

			var addr = '<c:out value="${roomAddr}"/>';
		    
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