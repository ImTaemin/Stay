<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../css/chat.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>

<body>
	<div class="chat-container">
		
		<!-- 채팅방 목록 -->
		<div class="chat-room-list">
			<div class="chat-title">
				<span>채팅방 목록</span>
			</div>
			<!-- 채팅방들 -->
			<div class="chat-list"></div>
		</div>
		
		<!-- 채팅화면 -->
		<div class="chatting">
			<div class="profile">
				<img id="profile-img">
				<span id="profile-name"></span>
			</div>

			<!-- 메세지들 -->
			<div class="chat-section"></div>

			<!-- 메시지 입력 -->
			<div class="input-msg-box">
				<input type="text" id="input-msg" class="input-msg" placeholder="메시지 입력..." />
				<button class="send-btn" id="send-btn" type="button">
					<i class="fa fa-paper-plane" aria-hidden="true"></i>
				</button>
			</div>
			
		</div>
	</div>

	<script type="text/javascript">
		$(function(){
			$.ajax({
				dataType: "json",
				type: "post",
				data: {"sender":"${sessionScope.myid}"},
				url: "/chat/recorded",
				success: function(data){
					data.forEach(element=>createRooms(element));
				}
			});
			
			//채팅방 요청
			var eventSourceRoom = new EventSource("http://localhost:8080/chat/chatting/${sessionScope.myid}");
	
			eventSourceRoom.onmessage = function(event) {
				var dataRooms = JSON.parse(event.data);
				
				createRooms(dataRooms); //dataRooms.msg로 찾을 수 있음
			};
			
			//채팅방 생성
			function createRooms(rooms){
				'use strict';
				
				var s="";
				
				if(rooms.receiver == "${sessionScope.myid}"){
					return;
				}
				
				if(rooms.receiver.indexOf("@") != -1){
					
					s = `
						<div class="chat-room" id="` + rooms.receiver + `" receiver="` + rooms.receiver + `">
							<img src="` + rooms.photo + `" class="room-photo">
							<span>` + rooms.receiver + `</span>
						</div>
					`;
				} else if(rooms.receiver.indexOf("@") == -1){
					s = `
						<div class="chat-room" id="` + rooms.receiver + `" receiver="` + rooms.receiver + `">
							<img src="../photo/memberPhoto/`+ rooms.photo+`" class="room-photo">
							<span>` + rooms.receiver + `</span>
						</div>
					`;
				}
				$(".chat-list").append(s);
				$(".chat-list").scrollTop(document.body.scrollHeight);
			}
			
			//채팅방 클릭시 채팅
			$(document).on("click", ".chat-room", function(){
				'use strict';
				
				var profileName = $(this).attr("receiver");
				
				//한번만 클릭
				if($(this).attr("receiver") == $("#profile-name").text()){
					return;
				}
				
				//원래 채팅방 연결해제
				if(eventSourceChat != null){
					eventSourceChat.close();	
				}
				
				if($("#profile-name").text() != ""){
					var tmp = $("#profile-name").text();
					document.getElementById(tmp).style.cursor = "pointer";
				}

				//채팅방 타이틀
				$("#profile-img").attr("src",$(this).children().attr("src"));
				$("#profile-name").text(profileName);
				
				$(this).css("cursor","default");
				$(".chat-section").html("");
				
				//원래 채팅내역 가져옴
				$.ajax({
					dataType: "json",
					type: "post",
					data: {"sender":"${sessionScope.myid}","receiver": profileName},
					url: "http://localhost:8080/chat/chatting",
					success: function(data){
						data.forEach(element=>createChats(element));
					}
				});
				
				var eventSourceChat = new EventSource("http://localhost:8080/chat/chatting/${sessionScope.myid}/" + profileName);
				eventSourceChat.onmessage = (event) => {
					var dataChats = JSON.parse(event.data);
					createChats(dataChats);//dataChats.msg로 찾을 수 있음
				};
			});

			//채팅내용 생성
			function createChats(chats){
				'use strict';
				if(chats.sender == '${sessionScope.myid}'){
					//보낸내용
					var s=`
						<div class="send-msg-box" >
							<div class="send_msg">
								<p>` + chats.msg + `</p>
								<span class="time_date" >` + chats.msg_time + `</span>
							</div>
						</div>
					`;
					
					var chatSection = $(".chat-section");
					$(".chat-section").append(s);
					$(".chat-section").scrollTop($(".chat-section")[0].scrollHeight);
				} else {
					//받은 내용
					var s=`
						<div class="receive-msg-box">
							<div class="receive-msg">
								<p>` + chats.msg + `</p>
								<span class="time_date">` + chats.msg_time + `</span>
							</div>
						</div>
					`;
					$(".chat-section").append(s);
					$(".chat-section").scrollTop($(".chat-section")[0].scrollHeight)
				}
			}
	
			function addMessage() {
				var msgInput = document.querySelector("#input-msg");
	
				var chat={
					sender: "${sessionScope.myid}",
					receiver: $("#profile-name").text(),
					msg: msgInput.value
				}
			
				//통신이 끝날떄까지 기다려야함
				/*var response = await */
				fetch("http://localhost:8080/chat/chatting/insert",{
					method: "post",
					body: JSON.stringify(chat), //JS->JSON
					headers: {
						"Content-Type":"application/json; charset=utf-8",
					}
				});
	
				msgInput.value = "";
			}
	
			document.querySelector("#send-btn").addEventListener("click", (e) => {
				addMessage();
			});
	
			document.querySelector("#input-msg").addEventListener("keydown", (e) => {
				if($("#input-msg").text()!="")
					return;
				
				//엔터키
				if (e.keyCode === 13) {
					
					addMessage();
				}
			});

		});
		
	</script>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>

</html>