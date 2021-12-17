<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/gaipForm.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>프로필 화면</title>
</head>
<body>
	<h2>프로필</h2>
	<form action="profileform" method="post">
		<table class="table table-bordered" style="width: 900px;">
			<tr>
				<th rowspan="2">${photo}</th>
				<th colspan="2"></th>
			</tr>
			<tr>
				<td rowspan="2">${name}님의 프로필입니다</td>
				<td>
					<span class="glyphicon glyphicon-heart likes"
					style="width: 30px; cursor: pointer; color: red" num="${num}"></span>
       				<span>LIKE ${likes}</span>
				</td>
			</tr>
			<tr>
				<td>
					<button type="button" class="btn btn-warning btn-sm" 
    				onclick="location.href='warning.jsp'">신고하기</button>
				</td>
				<td>
					<span class="glyphicon glyphicon-envelope message"
					style="width: 30px; cursor: pointer;"></span>
       				<span>MESSAGE ${message}</span>
				</td>
			</tr>
		</table>
		<table class="table table-bordered" style="width: 900px;">
			<h3>
				<b>숙소 후기</b>
			</h3>
			<tr>
				<th></th>
			</tr>
		</table>
	</form>

	<script src="js/profileForm.js"></script>
</body>
</html>