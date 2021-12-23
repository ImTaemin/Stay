var eventSource = new EventSource(`http://localhost:8080/chat/${sender}/${receiver}`);

eventSource.onmessage = (event) => {
	var data = JSON.parse(event.data);
	
	if(data.sender === username){
		//자기자신
		initMyMessage(data);
	} else {
		//상대방
	}
	
	initMyMessage(data);
};

function initMyMessage(historyMsg) {
	var chatBox = document.querySelector("#chat-box");

	var chatOutgoingBox = document.createElement("div");
	chatOutgoingBox.className = "outgoing_msg";
	chatOutgoingBox.innerHTML = getSendMsgBox(data.msg, data.day);

	chatBox.append(chatOutgoingBox);
}

async function addMessage() {
	var msgInput = document.querySelector("#input-msg");

	var chat={
		sender: username,
		receiver: "",
		msg: msgInput.value
	}
	
	//통신이 끝날떄까지 기다려야함
	var response = await fetch("http://localhost:8080/chat",{
		method: "post",
		body: JSON.stringify(chat), //JS->JSON
		headers: {
			"Content-Type":"application/json; charset=utf-8"
		}
	});
	
	var parseResponse = await response.json();
	
	chatOutgoingBox.innerHTML = getSendMsgBox(msgInput.value, now);

	chatBox.append(chatOutgoingBox);

	msgInput.value = "";
}

document.querySelector("#send-btn").addEventListener("click", () => {
	addMessage();
});

document.querySelector("#input-msg").addEventListener("keydown", () => {
	//엔터키
	if (e.keyCode === 13) {
		addMessage();
	}
});