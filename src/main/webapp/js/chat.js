const eventSource = new EventSource("http://localhost:8080/sender/sender/receiver/receiver");

eventSource = (event) => {

	const data = JSON.parse(event.data);

	initMessage(data);
};

function getSendMsgBox(msg, time) {
	return '<div class="sent-msg"> <p>${msg}</p> <span class="time_date"> ${time} </span> </div>';
}

function initMessage(historyMsg) {
	let chatBox = document.querySelector("#chat-box");
	let msgInput = document.querySelector("#chat-outgoing-msg");

	let chatOutgoingBox = document.createElement("div");
	chatOutgoingBox.className = "outgoing_msg";
	chatOutgoingBox.innerHTML = getSendMsgBox(data.msg, data.day);

	chatBox.append(chatOutgoingBox);

	msgInput.value = "";
}

function addMessage() {
	let chatBox = document.querySelector("#chat-box");
	let msgInput = document.querySelector("#chat-outgoing-msg");

	let chatOutgoingBox = document.createElement("div");
	chatOutgoingBox.className = "outgoing_msg";
	
	let date = new Date();
	let now = date.getMonth() + "월" + date.getDate() + "일  " + date.getHours() + ":" + date.getMinutes() + "|" + ;
	
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