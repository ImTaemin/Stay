// 멤버 좋아요 클릭
var flag = true;

function heartClick(e) {
	var check = $(e).attr("class");
	
	var id = $(e).attr("id");
	var cnt = parseInt($(e).attr("cnt"));

	if (check == "bi bi-heart co-heart") {
		$(e).attr("class", "bi bi-heart-fill co-heart");
		flag = false;

		$.ajax({
			type: "get",
			url: "/profile/updatelikes",
			data: { "id": id }
		});

		cnt += 1;

		$(e).attr("cnt", cnt);
		$("#" + heartCount).html(cnt);
	} else if (check == "bi bi-heart-fill co-heart") {
		$(e).attr("class", "bi bi-heart co-heart");
		flag = true;

		$.ajax({
			type: "get",
			url: "/profile/deletelikes",
			data: { "id": id }
		});

		cnt -= 1;

		$(e).attr("cnt", cnt);
		$("#" + heartCount).html(cnt);
	} else {
		$(e).attr("class", "bi bi-heart co-heart");
		flag = true;

		$.ajax({
			type: "get",
			url: "/profile/deletelikes",
			data: { "id": id }
		});

		cnt -= 1;

		$(e).attr("cnt", cnt);
		$("#" + heartCount).html(cnt);
	}
}

// 신고 모달
$(".openBtn").click(function() {
	$("#singo-id").val($("#report_id").text());
});

$("#bodyBtn").click(function() {
	var black_id = $("#report_id").text();
	var reason = $('#singo-reason').val();
	console.log(black_id);
	$.ajax({
		type: "post",
		dataType: "text",
		url: "/profile/singo",
		data: { "black_id": black_id, "reason": reason },
		success: function(data) {
			location.reload();
		}
	});
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