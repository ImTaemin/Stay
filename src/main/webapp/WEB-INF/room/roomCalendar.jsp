<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='../../css/calendar.css' rel='stylesheet' />
<link href='../../css/roomCalendar.css' rel='stylesheet' />
<script src='../../js/calendar.js'></script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      timeZone: 'UTC',
      initialView: 'dayGridMonth',
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth'
      },
      editable: true,
      dayMaxEventRows: 6,
      eventOrder: ['order_position','strike','order_start','title'],
      events: 'https://api.npoint.io/a07a48ae88d210ab50b2',
    });

    calendar.render();
  });

</script>
</head>
<body>
<div class="cal-container">
	<!-- 숙소 및 날짜(년,월) 리스트 -->
	<div class="cal-wrap">
		
		<!-- 숙소 리스트, 날짜 수정 -->
		<div class="cal-rooms">
			<div class="rooms-list">
				<select id="rooms">
					<!-- 방 동적생성 -->
				</select>
			</div>

			<div class="rooms-date">
				<select id="date">
					<!-- 날짜 생성 (오늘 날짜에 해당하는 달 기본 선택) -->
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
			<h1>2022-01-04</h1>
		</div>
		
		<hr>
		
		<div class="info-hosting">
			<span>호스팅 가능</span>
			<!-- 체크버튼 -->
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
				<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
				<path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
			</svg>
			<!-- 체크버튼 클릭 -->
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
				<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
			</svg>
			
			<!-- x버튼 -->
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
				<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
				<path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
			</svg>
			
			<!-- x버튼 클릭 -->
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
				<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>
			</svg>
		</div>
			
		<hr>
		
		<div class="info-price">
			<h2>숙소 요금 책정</h2>
			<div class="price-box">
				<h6>1박 요금</h6>
				&#8361;<span>123</span>
			</div>
		</div>
		
		<hr>
		
		<div class="info-btn">
			<button class="btn-submit">확인</button>
		</div>
	</div>
</div>
</body>
</html>