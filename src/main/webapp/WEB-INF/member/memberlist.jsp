<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Dokdo&family=Gaegu&family=Gugi&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!-- css -->
<link rel="stylesheet" href="${root}/css/memberlist.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>관리자 페이지 - 회원목록</title>
</head>
<body>
	<div class="report-main-wrap">
		<!-- 신고 관리 목록 -->
		<div class="report-list">
			<table class="table table-bordered" style="width: 100%;">
				<caption><b>신고 관리 목록</b></caption>
				<tr bgcolor="#eee">
					<th width="50">No</th>
					<th width="70">Black_Id</th>
					<th width="70">Report_Id</th>
					<th width="150">Reason</th>
					<th width="130">Admission</th>
				</tr>

				<c:forEach var="list" items="${reportList}" varStatus="i">
					<tr>
						<td align="center">${i.count}</td>
						<td>${list.black_id}</td>
						<td>${list.report_id}</td>
						<td>${list.reason}</td>
						<td>
							<!-- <button type="button" class="btn btn-danger btn-xs">접수</button>
							<button type="button" class="btn btn-warning btn-xs del">보류</button>
							<button type="button" class="btn btn-success btn-xs">승인</button> -->

							<c:if test="${preCheck == false}">
								<!-- 상태 버튼 -->
								<div class="can-wrap">
									<c:if test="${reMemDto.approve_check == 'ing'}">
										<button class="btn btn-info" id="can-reser" no="${reMemDto.no}"
											onclick="reserCan(this)">승인대기</button>
									</c:if>

									<c:if test="${reMemDto.approve_check == 'end'}">
										<button class="btn btn-secondary can-btn" id="can-reser"
											style="pointer-events: none;">승인완료</button>
									</c:if>

								</div>
							</c:if>

						</td>
					</tr>
				</c:forEach>
			</table>
			
			<%-- <!-- 페이징 처리 -->
			<c:if test="${totalCount > 0}">
				<div style="width: 100%; text-align: center;">
					<ul class="pagination">
						<!-- 이전 -->
						<c:if test="${startPage > 1}">
							<li>
								<a href="main?currentPage=${startPage - 1}">이전</a>
							</li>
						</c:if>
						
						<c:forEach var="pp" begin="${startPage}" end="${endPage}">
							<c:if test="${currentPage == pp}">
								<li class="active">
									<a href="main?currentPage=${pp}">${pp}</a>
								</li>
							</c:if>
							
							<c:if test="${currentPage != pp}">
								<li>
									<a href="main?currentPage=${pp}">${pp}</a>
								</li>
							</c:if>
						</c:forEach>
						
						<!-- 다음 -->
						<c:if test="${endPage < totalPage}">
							<li>
								<a href="main?currentPage=${endPage + 1}">이전</a>
							</li>
						</c:if>
					</ul>
				</div>
			</c:if> --%>
		</div>
	</div>
	
	<!-- js -->
	<script src="${root}/js/memberlist.js"></script>
</body>
</html>