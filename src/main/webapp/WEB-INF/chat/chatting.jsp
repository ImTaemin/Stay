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
			<div class="chat-list">
<!-- 				<div class="chat-room"> -->
<!-- 					<img src="../../photo/profile.png" class="room-photo"> -->
<!-- 					유저 아이디 -->
<!-- 				</div> -->
			</div>
		</div>
		
		<!-- 채팅화면 -->
		<div class="chatting">
			<div class="profile">
				<img id="profile-img">
				<span id="profile-name"></span>
			</div>

			<div class="chat-section">
				<!-- 받은메시지 -->
				<!-- <div class="receive-msg-box">
					<div class="receive-msg">
						<p>수현짱</p>
						<span class="time_date"> 11:18 | Today</span>
					</div>
				</div> -->

				<!-- 보낸메시지 -->
				<!-- <div class="send-msg-box" >
					<div class="send_msg">
						<p>태민짱</p>
						<span class="time_date" > 11:18 | Today</span>
					</div>
				</div> -->
			</div>

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
			//채팅방 요청
			var eventSourceRoom = new EventSource("http://localhost:8080/chat/${sessionScope.myid}");
// 			var eventSourceRoom = new EventSource("http://localhost:8080/chat/stay");
			
			eventSourceRoom.onmessage = (event) => {
				var dataRooms = JSON.parse(event.data);

				createRooms(dataRooms);//dataRooms.msg로 찾을 수 있음
			};
			
			//채팅방 생성
			function createRooms(rooms){
				'use strict';
				var s = `
				<div class="chat-room" id="` + rooms.receiver + `"receiver="` + rooms.receiver + `">
					<img src="../../photo/profile.png" class="room-photo">
					<span>` + rooms.receiver + `</span>
				</div>
				`
				$(".chat-list").append(s);
			}
			
			//채팅방 클릭시 채팅
			$(document).on("click", ".chat-room", function(){
				//한번만 클릭할 수 있게
				if($(this).attr("receiver") == $("#profile-name").text()){
					return;
				}
				
				if($("#profile-name").text() != ""){
					var tmp = $("#profile-name").text();
					document.getElementById(tmp).style.cursor = "pointer";
				}
				
				var eventSourceChat;
			
// 				$(".profile").append($(this).attr("receiver"));

				//채팅방 타이틀
				$("#profile-img").attr("src","../../photo/profile.png");
				$("#profile-name").text($(this).attr("receiver"));
				
				$(this).css("cursor","default");
				$(".chat-section").html("");
				
				if(eventSourceChat != null){eventSourceChat.close();}
				
				eventSourceChat = new EventSource("http://localhost:8080/chat/${sessionScope.myid}/" + $(this).attr("receiver"));
				
				eventSourceChat.onmessage = (event) => {
					var dataChats = JSON.parse(event.data);
					console.log(dataChats);

					createChats(dataChats);//dataChats.msg로 찾을 수 있음
				};
			});
			
			//채팅내용 생성
			function createChats(chats){
				'use strict';
				if(chats.sender == "stay" /*`${sessionScope.myid}`*/){
					//보낸내용
					var s=`
						<div class="send-msg-box" >
							<div class="send_msg">
								<p>` + chats.msg + `</p>
								<span class="time_date" >` + chats.msg_time + `</span>
							</div>
						</div>
					`;
					$(".chat-section").append(s);
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
				}
			}

// 			function initMyMessage(historyMsg) {
// 				var chatBox = document.querySelector("#chat-box");
		
// 				var chatOutgoingBox = document.createElement("div");
// 				chatOutgoingBox.className = "send-msg-box";
// 				chatOutgoingBox.innerHTML = getSendMsgBox(data.msg, data.day);
		
// 				chatBox.append(chatOutgoingBox);
// 			}
		
// 			function addMessage() {
// 				var msgInput = document.querySelector("#input-msg");
		
// 				var chat={
// 					sender: username,
// 					receiver: "",
// 					msg: msgInput.value
// 				}
				
// 				//통신이 끝날떄까지 기다려야함
// 				var response = await fetch("http://localhost:8080/chat",{
// 					method: "post",
// 					body: JSON.stringify(chat), //JS->JSON
// 					headers: {
// 						"Content-Type":"application/json; charset=utf-8",
// 						"Connection":"keep-alive",
//						"Cache-Control":"no-cache"
// 					}
// 				});
				
// 				var parseResponse = await response.json();
				
// 				chatOutgoingBox.innerHTML = getSendMsgBox(msgInput.value, now);
		
// 				chatBox.append(chatOutgoingBox);
		
// 				msgInput.value = "";
// 			}
		
// 			document.querySelector("#send-btn").addEventListener("click", () => {
// 				addMessage();
// 			});
		
// 			document.querySelector("#input-msg").addEventListener("keydown", () => {
// 				//엔터키
// 				if (e.keyCode === 13) {
// 					addMessage();
// 				}
// 			});
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