<%@page import="stay.data.dto.RoomDto"%>
<%@page import="java.util.List"%>
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
	<!-- css -->
<link rel="stylesheet" href="../css/guestMain.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- Swiper -->
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
 <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" /> 
 <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<div class="guestMain-top">
		<!-- 검색핉터창 -->
	  
		<div class="guestMain-filter">
			<div class="guestMain-map">
				<div class="guestMain-siteBtn"><button type="button" class="guestMain-site">위치 검색</button></div>
				<div class="bar">|</div>
				<div class="site-search">
					<input id="search-addr" onchange="search(this)" type="text" placeholder="위치를 검색하세요.">
					<hr>
					<script type="text/javascript">
						/* function search(){
							success:function(data){
								.forEach(item, index, array){
									s="";
									
								}
								$("#search-result").html(s);
							}
						} */
						
						function search(){
							var search = $("#search-addr").val();
							
							$.ajax({
								url:"searchRoomSite",
								type:"get",
								data: {"search": search},
								success:function(data){
									var s="";
									$.each(data, function(i, item){
										s+="<b>"+item.addr_load+"</b>";
									});
									$('#search-result').html(s);
								},
								error : function() {
									alert("error");
								}
							});
						}
						
						
						
					</script>
					<div id="search-result"></div>
				</div>
			</div>
			<div class="guestMain-start">  
								<jsp:useBean id="now" class="java.util.Date"/>
								<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
								<label for="now">체크인</label>
								<input type="date" id="check-in" min="${today}">  |  
			</div>
			<div class="guestMain-end">
								<label for="now">체크아웃</label>
								<input type="date" id="check-out" min="${today}">
			</div>
		</div>
		<div class="guestMain-search">
			<button type="button" class="glyphicon glyphicon-search"></button>
		</div>
	 
	</div>
	
	<div class="guestMain-bottom">
		<!-- 인기숙소 -->
		<div class="guestMain-toproom">
				<div class="heart">
					<i class="bi bi-heart-fill"></i>
				</div>
				<div class="title">인기 숙소</div>
				<div class="heart">
					<i class="bi bi-heart-fill"></i>
				</div>
		</div>
		
		<div class="guestMain-list">
			<!-- Slider main container -->
		    <div class="swiper-container">
		        <!-- Additional required wrapper -->
		        <div class="swiper-wrapper">
		            <!-- Slides ::슬라이더 -->
		            <c:forEach var="list" items="${bestList }">
		            	<div class="swiper-slide">
		            		<div class="guestMain-room"  onclick="location.href='room/content?no=${list.no}'">
									<div class="guestMain-image"><img alt="" src="${root}/photo/roomPhoto/${list.photos}"></div>
									
									<div class="guestMain-detail">
										<hr>
									
										<div class="guestMain-name">
											<span>${list.name}</span>
										</div>
										
										<div class="guestMain-site">
											<span>${list.addr_load}</span>
										</div>
									</div>
							</div>
		            	</div>
		            </c:forEach>
		        </div>
			
		        <!-- If we need navigation buttons ::양옆 좌우 버튼(선택) -->
		        <div class="swiper-button-prev"></div> 
		        <div class="swiper-button-next"></div>
		    </div>
	
		</div>
	</div>
	<script src="${root}/js/guestMain.js"></script>
</body>
</html>