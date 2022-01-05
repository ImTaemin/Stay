// 멤버 좋아요 클릭
var flag = true;

function heartClick(e) {
	var check = $(e).attr("class");

	var guestId = $(e).attr("guestId");
	var cnt = parseInt($(e).attr("cnt"));

	if (check == "bi bi-heart co-heart") {
		$(e).attr("class", "bi bi-heart-fill co-heart");
		flag = false;

		$.ajax({
			type: "post",
			url: "/profile/insertlike",
			data: { "guestId": guestId }
		});

		cnt += 1;

		$(e).attr("cnt", cnt);
		$("#heartCount").html(cnt);
	} else if (check == "bi bi-heart-fill co-heart") {
		$(e).attr("class", "bi bi-heart co-heart");
		flag = true;

		$.ajax({
			type: "post",
			url: "/profile/deletelike",
			data: { "guestId": guestId }
		});

		cnt -= 1;

		$(e).attr("cnt", cnt);
		$("#heartCount").html(cnt);
	} else {
		$(e).attr("class", "bi bi-heart co-heart");
		flag = true;

		$.ajax({
			type: "post",
			url: "/profile/deletelike",
			data: { "guestId": guestId }
		});

		cnt -= 1;

		$(e).attr("cnt", cnt);
		$("#heartCount").html(cnt);
	}
}

// 신고 모달
$(".openBtn").click(function() {
	$("#singo-id").val($("#report_id").text());
});

$("#bodyBtn").click(function() {
	var black_id = $("#report_id").text();
	var reason = $('#singo-reason').val();

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	if (reason == "" || reason == null) {
		swalWithBootstrapButtons.fire(
			'신고사유를 작성해주세요.',
			'신고사유 작성은 필수 작성요소입니다.',
			'error'
		)
	} else {
		swalWithBootstrapButtons.fire({
			title: '신고하시겠습니까?',
			text: "신고할 경우 취소는 불가능합니다.",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: '승인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					type: "post",
					url: "/profile/report",
					dataType: "json",
					data: { "blackId": black_id, "reason": reason},
					success: function(data) {
						if (data == false) {
							swalWithBootstrapButtons.fire(
								'이미 신고한 아이디입니다.',
								'아이디당 신고는 한 번만 가능합니다.',
								'error'
							)
						} else if (data == true) {
							swalWithBootstrapButtons.fire(
								'신고되었습니다.',
								'관리자가 신고를 승인한 경우 블랙회원 처리됩니다.',
								'success'
							)
						}
					}
				});
				
				$("#singo-reason").val('');
			}
		})
	}
});

// 후기 좋아요 클릭 이벤트
var flag = true;

function coHeartClick(e1) {
	var check = $(e1).attr("class");

	var reserNo = $(e1).attr("reserNo");
	var guestId = $(e1).attr("guestId");
	var myId = $(e1).attr("myid");
	var cnt = parseInt($(e1).attr("cnt"));

	if (myId == "") {
		Swal.fire({
			icon: 'error',
			title: '로그인이 필요합니다.',
			text: '로그인 후 이용가능한 서비스입니다.'
		});
	} else {
		if (check == "bi bi-heart co-heart") {
			$(e1).attr("class", "bi bi-heart-fill co-heart");
			flag = false;

			$.ajax({
				type: "post",
				url: "/like/insert",
				data: { "reserNo": reserNo, "guestId": guestId }
			});

			cnt += 1;

			$(e1).attr("cnt", cnt);
			$("#" + reserNo).html(cnt);
		} else if (check == "bi bi-heart-fill co-heart") {
			$(e1).attr("class", "bi bi-heart co-heart");
			flag = true;

			$.ajax({
				type: "post",
				url: "/like/delete",
				data: { "reserNo": reserNo, "guestId": guestId }
			});

			cnt -= 1;

			$(e1).attr("cnt", cnt);
			$("#" + reserNo).html(cnt);
		} else {
			$(e1).attr("class", "bi bi-heart co-heart");
			flag = true;

			$.ajax({
				type: "post",
				url: "/like/delete",
				data: { "reserNo": reserNo, "guestId": guestId }
			});

			cnt -= 1;

			$(e1).attr("cnt", cnt);
			$("#" + reserNo).html(cnt);
		}
	}
}

// 모달 창 닫을 때 이벤트 (body 화면 줄어듦)
$(document).ready(function() {
	$('#singoModal').on('hidden.bs.modal', function() {
		document.body.style.padding = "0";
	});

	var myid = $("input[name=myid]").attr("value");
	var guestId = $("input[name=guestId]").attr("value");

	if (myid == guestId) {
		$("#openBtn").attr("style", "pointer-events: none;");
		$("#openBtn").attr("class", "btn btn-secoundry");
		$(".update").attr("data-target", "");
	}
});