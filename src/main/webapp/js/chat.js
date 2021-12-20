

const eventSource = new EventSource(`http://localhost:8080/sender/${sender}/receiver/${receiver}`);

eventSource.onmessage = (event) => {
	const data = JSON.parse(event.data);
	
	if(data.sender === username){
		//자기자신
		initMyMessage(data);
	} else {
		//상대방
	}
	
	initMyMessage(data);
};

function getSendMsgBox(msg, time) {
	return `<div class="sent-msg"> <p>${msg}</p> <span class="time_date"> ${time} </span> </div>`;
}

function initMyMessage(historyMsg) {
	let chatBox = document.querySelector("#chat-box");

	let chatOutgoingBox = document.createElement("div");
	chatOutgoingBox.className = "outgoing_msg";
	chatOutgoingBox.innerHTML = getSendMsgBox(data.msg, data.day);

	chatBox.append(chatOutgoingBox);
}

async function addMessage() {
	let msgInput = document.querySelector("#chat-outgoing-msg");

	let chat={
		sender: username,
		receiver: "",
		msg: msgInput.value
	}
	
	//통신이 끝날떄까지 기다려야함
	let response = await fetch("http://localhost:8080/chat",{
		method: "post",
		body: JSON.stringify(chat), //JS->JSON
		headers: {
			"Content-Type":"application/json; charset=utf-8"
		}
	});
	
	let parseResponse = await response.json();
	
	chatOutgoingBox.innerHTML = getSendMsgBox(msgInput.value, now);

	chatBox.append(chatOutgoingBox);

	msgInput.value = "";
}

document.querySelector("#chat-outgoing-button").addEventListener("click", () => {
	addMessage();
});

document.querySelector("#chat-outgoing-msg").addEventListener("keydown", () => {

	//엔터키
	if (e.keyCode === 13) {
		addMessage();
	}
});