<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
<!-- css -->
<link rel="stylesheet" href="${root}/css/reservationList.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- 탭 목록 -->
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="now-tab" data-bs-toggle="tab" data-bs-target="#now" type="button" role="tab" aria-controls="now" aria-selected="true">예정된 예약</button>
		</li>
		
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="pre-tab" data-bs-toggle="tab" data-bs-target="#pre" type="button" role="tab" aria-controls="pre" aria-selected="false">이전 예약</button>
		</li>
		
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="can-tab" data-bs-toggle="tab" data-bs-target="#can" type="button" role="tab" aria-controls="can" aria-selected="false">취소된 예약</button>
		</li>
	</ul>
		
	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane active" id="now" role="tabpanel" aria-labelledby="now-tab">
			<c:forEach var="list" items="${reserList}">
				${list.no}
			</c:forEach>
		</div>
		
		<div class="tab-pane" id="pre" role="tabpanel" aria-labelledby="pre-tab">
			이전
		</div>
		
		<div class="tab-pane" id="can" role="tabpanel" aria-labelledby="can-tab">
			취소
		</div>
	</div>
</body>
</html>