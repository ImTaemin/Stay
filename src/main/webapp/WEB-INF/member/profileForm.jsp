<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/profileForm.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<title>프로필 화면</title>
</head>
<body>
	<div class="profile-main">
		<%-- <c:forEach var="memlist" items="${memList}"> --%>
		<div class="profile-top">
			<div class="profile-first">
				<!-- <input type="file" name="photo" id="photo" style="display: none;" required="required" multiple="multiple">
     			<span class="glyphicon glyphicon-user" style="cursor: pointer;"></span> -->
     			<div class="photo">
					<img alt="" src="${root}/photo/memberPhoto/${memberDto.photo}">
				</div>
				<!-- <button type="button" class="modal_btn" style="width: 200px; padding-top: 6px;">신고하기</button> -->
				<button class="openBtn">신고하기</button>

				<!-- Modal -->
				<!-- <div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">

						Modal content
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">댓글 수정</h4>
							</div>

							<div class="modal-body">
								<input type="text" id="ucontent" class="form-control">
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-warning"
									data-dismiss="modal" id="btnaupdate">신고하기</button>
							</div>
						</div>

					</div>
				</div>
 -->
				<div class="modal hidden">
					<div class="bg"></div>
					<div class="modalBox">
						<p></p>
						<label for="id">신고 할 아이디&nbsp;</label>
  	 						<input type="text" required="required" value="${sessionScope.myid }"><br><br>
  	 					<label for="content">&nbsp;&nbsp;&nbsp;&nbsp;신고 사유&nbsp;&nbsp;&nbsp;&nbsp;</label>
  	 						<input type="text" required="required">
						<button class="closeBtn">✖</button>
					</div>
				</div>

			</div>
			<div class="profile-second">
				<%-- <h2>${name}님의 프로필입니다.😊</h2> --%>
				<c:if test="${sessionScope.loginok!=null }">
				<h2><b>${sessionScope.myid } 님의<br><br>프로필입니다.😊</b></h2>
				</c:if>
			</div>
			<div class="profile-third">
				<div class="profile-like">
					<span class="glyphicon glyphicon-heart likes"
						style="width: 30px; cursor: pointer; color: red" num="${num}"></span>
					<span>LIKE ${memberDto.likes}</span>
				</div>
				<div class="profile-message">
					<span class="glyphicon glyphicon-envelope message"
						style="width: 30px; cursor: pointer;"></span>
					<span>MESSAGE ${message}</span>
				</div>
			</div>
		</div>
		<div class="profile-review">
			<h2>숙소 후기</h2>
		</div>
		<%-- </c:forEach> --%>
	</div>
	<script src="/js/profileForm.js"></script>
</body>
</html>