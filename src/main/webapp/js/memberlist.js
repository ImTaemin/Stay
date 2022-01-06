// 예약 취소
function reserRef(e) {
	var no = $(e).attr("no");

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '신고 승인 하시겠습니까?',
		text: "승인 후 신고 철회가 어려울 수 있습니다.",
		icon: 'warning',
		cancelButtonText: '승인 거절',
		showCancelButton: true,
		showCloseButton: true,
		confirmButtonText: '신고 승인',
	}).then((result) => {
		if (result.isConfirmed) {
			swalWithBootstrapButtons.fire(
				'신고가 승인 되었습니다.',
				'본사 규정에 맞춰 신고 절차에 따라 진행될 예정입니다.',
				'success'
			)

			$(e).attr("class", "btn btn-success");
			$(e).attr("onclick", "");
			$(e).attr("style", "pointer-events: none;");
			$(e).html("승인 완료");

			$.ajax({
				type: "post",
				url: "/report/oksingo",
				data: {"no": no}
			});
		}
	})
}