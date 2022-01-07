// calender
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		locale: 'ko',
		initialView: 'dayGridMonth',
		headerToolbar: {
			left: '',
			center: 'title',
			right: ''
		},
		timeZone: 'local'
	});

	calendar.render();

	var now = new Date();

	$(document).ready(function() {
		// select date
		var day = [];

		var getDate = CF_toStringByFormatting(now.setMonth(now.getMonth())).split("-");
		var nowDate = getDate[0] + "년 " + getDate[1] + "월";

		var node = document.createElement("option");
		node.setAttribute("value", nowDate);
		node.id = getDate[0] + "-" + getDate[1];

		var text = document.createTextNode(nowDate);
		node.appendChild(text);

		document.getElementById("date").appendChild(node);

		for (var i = 0; i < 11; i++) {
			var getDate = CF_toStringByFormatting(now.setMonth(now.getMonth() + 1)).split("-");
			day[i] = getDate[0] + "년 " + getDate[1] + "월";

			var node = document.createElement("option");
			node.setAttribute("value", day[i]);
			node.id = getDate[0] + "-" + getDate[1];

			var text = document.createTextNode(day[i]);
			node.appendChild(text);

			document.getElementById("date").appendChild(node);
		}

		// select room
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
				if (data.hosting == true) {
					$('#hosting-true').attr('class', 'bi bi-check-circle-fill');
				} else {
					$('#hosting-false').attr('class', 'bi bi-x-circle-fill');
				}

				// 오늘 날짜 클릭 이벤트
				$('.fc-day-toeday').click(function() {
					var click = $(this).attr('data-date');
					var clickDate = click.split("-");

					$("#select-day").html(clickDate[0] + "년 " + clickDate[1] + "월 " + clickDate[2] + "일");
					$('#roomPirce').attr('value',roomPcie);

					// 호스팅 여부 출력
					if (data.hosting == true) {
						$('#hosting-true').attr('class', 'bi bi-check-circle-fill');
						$('#hosting-false').attr('class', 'bi bi-x-circle');
					} else {
						$('#hosting-true').attr('class', 'bi bi-check-circle');
						$('#hosting-false').attr('class', 'bi bi-x-circle-fill');
					}
				});

				// 미래 날짜 클릭 이벤트
				$('.fc-day-future').click(function() {
					var click = $(this).attr('data-date');
					var clickDate = click.split("-");

					$("#select-day").html(clickDate[0] + "년 " + clickDate[1] + "월 " + clickDate[2] + "일");
					$("input[id=roomPirce]").attr("value", "￦ " + roomPrice.toLocaleString());

					// 호스팅 여부 출력
					if (data.hosting == true) {
						$('#hosting-true').attr('class', 'bi bi-check-circle-fill');
						$('#hosting-false').attr('class', 'bi bi-x-circle');
					} else {
						$('#hosting-true').attr('class', 'bi bi-check-circle');
						$('#hosting-false').attr('class', 'bi bi-x-circle-fill');
					}
				});
			}
		});
	});

	window.onload = function() {
		document.getElementById('date').addEventListener('change', function() {
			$("span[class=cal-price]").remove();

			var selectMonth = new Date($("#date option:selected").attr("id") + "-01");
			calendar.gotoDate(selectMonth);

			// select room
			var select = document.getElementById("rooms");
			var roomNo = select.options[select.selectedIndex].value;

			$.ajax({
				type: "post",
				dataType: "json",
				url: "/calendar/searchroom",
				data: { "roomNo": roomNo },
				success: function(data) {
					// 달력 요금 출력
					var roomPrice = data.price;

					var priceText = '<span class="cal-price">' + "￦ " + roomPrice.toLocaleString() + '</span>';
					var nullText = '<span class="cal-null-price">&nbsp;</span>';

					var pastDiv = $('.fc-day-past').children('.fc-scrollgrid-sync-inner');
					$(pastDiv).append(nullText);

					var todayDiv = $('.fc-day-today').children('.fc-scrollgrid-sync-inner');
					$(todayDiv).append(priceText);

					var futureDiv = $('.fc-day-future').children('.fc-scrollgrid-sync-inner');
					$(futureDiv).append(priceText);
				}
			});

			// 미래 날짜 클릭 이벤트 (월 변경 시)
			$('.fc-day-future').click(function() {
				var clickDate = $(this).attr('data-date').split("-");

				$("#select-day").html(clickDate[0] + "년 " + clickDate[1] + "월 " + clickDate[2] + "일");
			});
		});
	};
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
	if ($(e).attr("id") == "hosting-true") {
		$(e).attr("class", "bi bi-check-circle-fill");
		$('#hosting-false').attr('class', 'bi bi-x-circle');
	} else if ($(e).attr("id") == "hosting-false") {
		$(e).attr("class", "bi bi-x-circle-fill");
		$('#hosting-true').attr('class', 'bi bi-check-circle');
	}
}

// 버튼 이벤트
function checkChange() {
	var hostingCheck = '';

	var select = document.getElementById("rooms");
	var roomNo = select.options[select.selectedIndex].value;

	$.ajax({
		type: "post",
		dataType: "json",
		url: "/calendar/searchroom",
		data: { "roomNo": roomNo },
		success: function(data) {
			var roomPrice = data.price;

			// 달력 값 변경
			var selectDate = $('#select-day').html();
			var splitDate = selectDate.replace('년 ', '-').replace('월 ', '-').replace('일', '');

			// 호스팅 버튼 여부
			if ($('#hosting-true').attr("class") == "bi bi-check-circle-fill") {
				hostingCheck = true;

				$('td[data-date=' + splitDate + '] span').html('￦ ' + roomPrice.toLocaleString());

				if (splitDate == CF_toStringByFormatting(new Date)) {
					$('td[data-date=' + splitDate + ']').css('background-color', '#fffadf');
				} else {
					$('td[data-date=' + splitDate + ']').css('background-color', 'white');
				}

				$.ajax({
					type: "post",
					dataType: "json",
					url: "/calendar/updatehosting",
					data: { "roomNo": roomNo, "chageDate": splitDate, "hosting": hostingCheck }
				});

				// 가격 변경 여부
				var chagePrice = $('#roomPirce').val().replace('￦ ', '').replace(',', '');

				if (roomPirce != chagePrice) {
					$('td[data-date=' + splitDate + '] span').html($('#roomPirce').val());

					$.ajax({
						type: "post",
						dataType: "json",
						url: "/calendar/updateprice",
						data: { "roomNo": roomNo, "chageDate": splitDate, "roomPrice": chagePrice }
					});
				}
			} else if ($('#hosting-false').attr("class") == "bi bi-x-circle-fill") {
				hostingCheck = false;

				$('td[data-date=' + splitDate + ']').css('background-color', '#F5F5F5');
				$('td[data-date=' + splitDate + '] span').html('<span class="cal-null-price">&nbsp;</span>');

				$.ajax({
					type: "post",
					dataType: "json",
					url: "/calendar/updatehosting",
					data: { "roomNo": roomNo, "chageDate": splitDate, "hosting": hostingCheck }
				});
			}
		}
	});
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