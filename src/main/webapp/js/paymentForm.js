// 체크인 날짜 변경
function checkInChange(e) {
	var end_date = $('input[name=endDate]').attr('value');
	var endDate = Date.parse(end_date);
	var roomPrice = $(e).attr("roomPrice");

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	let flatpickrInstance

	swalWithBootstrapButtons.fire({
		title: '변경할 체크인 날짜를 입력하세요.',
		html: '<input class="swal2-input" id="expiry-date">',
		confirmButton: 'Ok',
		stopKeydownPropagation: false,
		preConfirm: () => {
			var changeStart = dateFormat(flatpickrInstance.selectedDates[0]);
			var start = changeStart.split("-");

			$('input[name=startDate]').attr('value', changeStart);
			$('label[id=start1]').html(start[0]);
			$('label[id=start2]').html(start[1]);
			$('label[id=start3]').html(start[2]);

			var reStart = new Date(changeStart);
			var reEnd = new Date(end_date);

			var betweenMs = reEnd.getTime() - reStart.getTime();
			var betweenDay = betweenMs / (1000 * 60 * 60 * 24);

			$('input[name=betweenDay]').attr('value', betweenDay);
			$('label[id=betweenDay]').html(betweenDay);

			var calPrice = roomPrice * betweenDay;
			var commaCal = Number(calPrice).toLocaleString();

			$('label[id=calPrice]').html("￦" + commaCal);
			$('input[name=calPrice]').attr('value', calPrice);

			var taxPrice = calPrice * 0.2;
			var commaTax = Number(taxPrice).toLocaleString();

			$('label[id=taxPrice]').html("￦" + commaTax);
			$('input[name=taxPrice]').attr('value', taxPrice);

			var allPrice = calPrice + taxPrice;
			var commaAll = Number(allPrice).toLocaleString();

			$('label[id=allPrice]').html("￦" + commaAll);
			$('input[name=allPrice]').attr('value', allPrice);

			if (flatpickrInstance.selectedDates[0] < new Date()) {
				Swal.showValidationMessage(`체크인 날짜를 다시 입력해주세요.`)
			} else if (flatpickrInstance.selectedDates[0] > endDate) {
				Swal.showValidationMessage(`체크인 날짜를 다시 입력해주세요.`)
			} else if (betweenDay <= 0) {
				Swal.showValidationMessage(`체크인 날짜를 다시 입력해주세요.`)
			}
		},
		willOpen: () => {
			flatpickrInstance = flatpickr(
				Swal.getPopup().querySelector('#expiry-date')
			)
		}
	})
}

// 체크아웃 날짜 변경
function checkOutChange(e) {
	var start_date = $('input[name=startDate]').attr('value');
	var startDate = Date.parse(start_date);
	var roomPrice = $(e).attr("roomPrice");

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	let flatpickrInstance

	swalWithBootstrapButtons.fire({
		title: '변경할 체크아웃 날짜를 입력하세요.',
		html: '<input class="swal2-input" id="expiry-date">',
		confirmButton: 'Ok',
		stopKeydownPropagation: false,
		preConfirm: () => {
			var changeEnd = dateFormat(flatpickrInstance.selectedDates[0]);
			var end = changeEnd.split("-");

			$('input[name=endDate]').attr('value', changeEnd);
			$('label[id=end1]').html(end[0]);
			$('label[id=end2]').html(end[1]);
			$('label[id=end3]').html(end[2]);

			var reStart = new Date(start_date);
			var reEnd = new Date(changeEnd);

			var betweenMs = reEnd.getTime() - reStart.getTime();
			var betweenDay = betweenMs / (1000 * 60 * 60 * 24);

			$('input[name=betweenDay]').attr('value', betweenDay);
			$('label[id=betweenDay]').html(betweenDay);

			var calPrice = roomPrice * betweenDay;
			var commaCal = Number(calPrice).toLocaleString();

			$('label[id=calPrice]').html("￦" + commaCal);
			$('input[name=calPrice]').attr('value', calPrice);

			var taxPrice = calPrice * 0.2;
			var commaTax = Number(taxPrice).toLocaleString();

			$('label[id=taxPrice]').html("￦" + commaTax);
			$('input[name=taxPrice]').attr('value', taxPrice);

			var allPrice = calPrice + taxPrice;
			var commaAll = Number(allPrice).toLocaleString();

			$('label[id=allPrice]').html("￦" + commaAll);
			$('input[name=allPrice]').attr('value', allPrice);

			if (flatpickrInstance.selectedDates[0] < new Date()) {
				Swal.showValidationMessage(`체크인 날짜를 다시 입력해주세요.`)
			} else if (flatpickrInstance.selectedDates[0] < startDate) {
				Swal.showValidationMessage(`체크인 날짜를 다시 입력해주세요.`)
			} else if (betweenDay <= 0) {
				Swal.showValidationMessage(`체크인 날짜를 다시 입력해주세요.`)
			}
		},
		willOpen: () => {
			flatpickrInstance = flatpickr(
				Swal.getPopup().querySelector('#expiry-date')
			)
		}
	})
}

// Date to String
function dateFormat(date) {
	let month = date.getMonth() + 1;
	let day = date.getDate();

	month = month >= 10 ? month : '0' + month;
	day = day >= 10 ? day : '0' + day;

	return date.getFullYear() + '-' + month + '-' + day;
}

// 결제 완료
function formCheck() {
	var kakao = $("#kakao").attr("check");
	var card = $("#card").attr("check");

	var roomNo = $('input[name=roomNo]').attr('value');
	var startDate = $('input[name=startDate]').attr('value');
	var endDate = $('input[name=endDate]').attr('value');
	var allPrice = $('input[name=allPrice]').attr('value');
	var taxPrice = allPrice * 0.2;

	if (kakao == "0" && card == "0") {
		$("#frm").attr("action", "");

		Swal.fire({
			icon: 'error',
			title: '결제 방법을 선택해주세요.'
		});
	} else if (kakao == "1" && card == "0") {
		$.ajax({
			url: "kakaopay",
			dataType: "json",
			data: { "roomNo": roomNo, "allPrice": allPrice, "startDate": startDate, "endDate": endDate, "taxPrice": taxPrice },
			contentType: "application/json; charset=utf-8",
		}).done(function(resp) {
			if (resp.status === 500) {
				alert("카카오페이결제를 실패하였습니다.")
			} else {
				//결제 고유 번호
				var box = resp.next_redirect_pc_url;
				location.href = box;
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	} else {
		$("#frm").submit();
	}
}