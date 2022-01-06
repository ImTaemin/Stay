// calender
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		headerToolbar: {
			left: '',
			center: 'title',
			right: ''
		},
	});

	calendar.render();
});

$(document).ready(function() {
	// select date
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

	var select = document.getElementById("rooms");
	var roomNo = select.options[select.selectedIndex].value;

	$.ajax({
		type: "post",
		dataType: "json",
		url: "/calendar/searchroom",
		data: { "roomNo": roomNo },
		success: function(data) {
			// 숙소 요금 출력
			var roomPrice = data.price;

			$("input[id=roomPirce]").attr("value", "￦ " + roomPrice.toLocaleString());

			// 달력 요금 출력
			var priceText = '<span class="cal-price">' + "￦ " + roomPrice.toLocaleString() + '</span>';
			var nullText = '<span class="cal-null-price">&nbsp;</span>';

			var pastDiv = $('.fc-day-past').children('.fc-scrollgrid-sync-inner');
			$(pastDiv).append(nullText);

			var todayDiv = $('.fc-day-today').children('.fc-scrollgrid-sync-inner');
			$(todayDiv).append(priceText);

			var futureDiv = $('.fc-day-future').children('.fc-scrollgrid-sync-inner');
			$(futureDiv).append(priceText);
			
			// 호스팅 여부 출력
			if(data.hosting == true) {
				$('#hosting-true').attr('class', 'bi bi-check-circle-fill');
				$('#hosting-true').attr('check', '1');
			} else {
				$('#hosting-false').attr('class', 'bi bi-x-circle-fill');
				$('#hosting-false').attr('check', '1');
			}
		}
	});

	// 오늘 날짜 클릭 이벤트
	$('.fc-day-today').click(function() {
		var clickDate = $(this).attr('data-date').split("-");
		
		$("#select-day").html(clickDate[0] + "년 " + clickDate[1] + "월 " + clickDate[2] + "일");
	});
	
	// 미래 날짜 클릭 이벤트
	$('.fc-day-future').click(function() {
		var clickDate = $(this).attr('data-date').split("-");
		
		$("#select-day").html(clickDate[0] + "년 " + clickDate[1] + "월 " + clickDate[2] + "일");
	});
});

// select room 이벤트
function selectRoom() {
	var select = document.getElementById("rooms");
	var roomNo = select.options[select.selectedIndex].value;

	$.ajax({
		type: "post",
		dataType: "json",
		url: "/calendar/searchroom",
		data: { "roomNo": roomNo },
		success: function(data) {
			var roomPrice = data.price;

			$("input[id=roomPirce]").attr("value", "￦ " + roomPrice.toLocaleString());

			// 달력 요금 출력
			$(".cal-price").html("￦ " + roomPrice.toLocaleString());
			
			// 날짜 변경
			var now = new Date();
			var getDate = CF_toStringByFormatting(now).split("-");
			$("#select-day").html(getDate[0] + "년 " + getDate[1] + "월 " + getDate[2] + "일");
		}
	});
}

// check 버튼 클릭 이벤트
function clickCheck(e) {
	if($(e).attr("id") == "hosting-true") {
		$(e).attr("class","bi bi-check-circle-fill")
		$('#hosting-false').attr('class', 'bi bi-x-circle');
	} else if ($(e).attr("id") == "hosting-false") {
		$(e).attr("class","bi bi-x-circle-fill")
		$('#hosting-true').attr('class', 'bi bi-check-circle');
	}
}

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

// select room input
$(document).on("keyup", "input:text[numberOnlyMinComma]", function() {
	var strVal = $(this).val();

	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;

	if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105)
		|| keyID == 46 || keyID == 8 || keyID == 109
		|| keyID == 189 || keyID == 9
		|| keyID == 37 || keyID == 39) {

		if (strVal.length > 1 && (keyID == 109 || keyID == 189)) {
			return false;
		} else {
			return;
		}
	} else {
		return false;
	}
});

$(document).on("keyup", "input:text[numberOnlyMinComma]", function() {
	$(this).val($(this).val().replace(/[^-\.0-9]/gi, ""));
	$(this).val($(this).val().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
});

$(document).on("focusout", "input:text[koreanCurrency]", function() {
	$(this).val($(this).val().replace(",", ""));
	$(this).val($(this).val().replace(/[^-\.0-9]/gi, ""));
	$(this).val($(this).val().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
	if ($(this).val() != '') {
		$(this).val("￦ " + $(this).val());
	}
});

$(document).on("focus", "input:text[koreanCurrency]", function() {
	$(this).val($(this).val().replace("￦ ", ""));
});