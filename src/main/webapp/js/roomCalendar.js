// calender
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		headerToolbar: {
			left: '',
			center: 'title',
			right : ''
		},
	});
	
	calendar.render();
});

// 오늘, 년, 월, 일, 1일, 말일
$(document).ready(function() {
	var now = new Date();
	var day = [];

	var getDate = CF_toStringByFormatting(now.setMonth(now.getMonth())).split("-");
	var nowDate = getDate[0] + "년 " + getDate[1] + "월";

	var node = document.createElement("option");
	node.setAttribute("value", nowDate);

	var text = document.createTextNode(nowDate);
	node.appendChild(text);

	document.getElementById("date").appendChild(node);

	for (var i = 0; i < 11; i++) {
		var getDate = CF_toStringByFormatting(now.setMonth(now.getMonth() + 1)).split("-");
		day[i] = getDate[0] + "년 " + getDate[1] + "월";

		var node = document.createElement("option");
		node.setAttribute("value", day[i]);

		var text = document.createTextNode(day[i]);
		node.appendChild(text);

		document.getElementById("date").appendChild(node);
	}
});

// date to string
function CF_toStringByFormatting(source) {
	var date = new Date(source);
	const year = date.getFullYear();
	const month = CF_leftPad(date.getMonth() + 1);
	const day = CF_leftPad(date.getDate());
	return [year, month, day].join('-');
}

function CF_leftPad(value) {
	if (Number(value) >= 10) {
		return value;
	}
	return "0" + value;
}

// check 버튼 클릭 이벤트
var checkFlag = true;

function clickCheck(e) {
	var className = $(e).attr("class");

	if (className = "bi bi-check-circle") {
		$(e).attr('class', 'bi bi-check-circle-fill');
	} else if (className = "bi bi-check-circle-fill") {
		$(e).attr('class', 'bi bi-check-circle');
	}
}

// cancle 버튼 클릭 이벤트
var canFlag = true;

function clickCan(e) {
	var className = $(e).attr("class");

	if (className = "bi bi-x-circle") {
		$(e).attr('class', 'bi bi-x-circle-fill');
	} else if (className = "bi bi-x-circle-fill") {
		$(e).attr('class', 'bi bi-x-circle');
	}
}