// 이미지 슬라이드
let slides = document.querySelector(".slides");
let slideImg = document.querySelectorAll(".slides li");

currentIdx = 0;
slideCount = slideImg.length;
prev = document.querySelector(".prev");
next = document.querySelector(".next");

slideWidth = 800;
slideMargin = 100;
slides.style.width = (slideWidth + slideMargin) * slideCount + "px";

function moveSlide(num) {
	// 왼쪽으로 400px씩 이동
	slides.style.left = -num * 700 + "px";
	currentIdx = num;
}

prev.addEventListener('click', function() {
	// 첫 번째 슬라이드로 표시 됐을때는 이전 버튼 눌러도 아무런 반응 없게 하기 위해
	// currentIdx !==0일때만 moveSlide 함수 불러옴
	if (currentIdx !== 0) moveSlide(currentIdx - 1);
});

next.addEventListener('click', function() {
	var slideCount = $(".slides").find("li").length;

	// 마지막 슬라이드로 표시 됐을때는 다음 버튼 눌러도 아무런 반응 없게 하기 위해
	// currentIdx !==slideCount - 1 일때만 moveSlide 함수 불러옴
	if (currentIdx !== slideCount - 1) moveSlide(currentIdx + 1);
});

// 공동 게스트 추가
function addGuest(e) {
	var no = $(e).attr("no");
	var hostId = $(e).attr("hostId");
	var maxPer = $(e).attr("maxPer");
	var myid = $(e).attr("myid");
	var joinNum = parseInt($('input[name=joinNum]').attr('value'));

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '게스트 추가하기',
		input: 'text',
		inputAttributes: {
			autocapitalize: 'off'
		},
		showCancelButton: true,
		cancelButtonText: '취소',
		confirmButtonText: '아이디 추가',
		inputPlaceholder: '아이디를 입력하세요.',
		showLoaderOnConfirm: true,
		preConfirm: (id) => {
			$.ajax({
				type: "post",
				url: "/join/search",
				data: { "no": no, "id": id },
				success: function(data) {
					if (id == hostId) {
						swalWithBootstrapButtons.fire({
							icon: 'error',
							title: '추가할 수 없는 아이디 입니다.',
							text: '호스트 아이디와 동일합니다.'
						})
					} else if (id == myid) {
						swalWithBootstrapButtons.fire({
							icon: 'error',
							title: '추가할 수 없는 아이디 입니다.',
							text: '메인 게스트와 동일한 아이디입니다.'
						})
					} else if (id == '') {
						swalWithBootstrapButtons.fire({
							icon: 'error',
							title: '아이디를 입력해주세요.'
						})
					} else if (data == "empty") {
						swalWithBootstrapButtons.fire({
							icon: 'error',
							title: id + '는 없는 아이디 입니다.'
						})
					} else if (data == "same") {
						swalWithBootstrapButtons.fire({
							icon: 'error',
							title: id + '는 이미 추가된 아이디 입니다.'
						})
					} else if (data == "true") {
						swalWithBootstrapButtons.fire({
							title: id + ' 님을 추가하시겠습니까?',
							text: "추가할 경우 해당 예약 내역이 공유됩니다.",
							icon: 'question',
							showCancelButton: true,
							confirmButtonText: '추가',
							cancelButtonText: '취소'
						}).then((result) => {
							if (result.isConfirmed) {
								$.ajax({
									type: "post",
									url: "/join/insert",
									data: { "no": no, "id": id },
									success: function() {
									}
								});

								swalWithBootstrapButtons.fire({
									icon: 'success',
									title: '게스트가 추가되었습니다.',
									confirmButtonText: '확인'
								})

								joinNum += 1;

								$("#joinNum").html(joinNum + "명");
								$('input[name=joinNum]').attr('value', joinNum);

								if (maxPer == joinNum) {
									$(e).hide();
								}
							} else if (
								result.dismiss === Swal.DismissReason.cancel
							) {
								swalWithBootstrapButtons.fire(
									'취소되었습니다.',
									'게스트 추가가 취소되었습니다.',
									'error'
								)
							}
						})
					}
				}
			});
		}
	})
}

// 공동 게스트 삭제
function delGuest(e) {
	var no = $(e).attr("resNo");
	var id = $(e).attr("guestId");
	var maxPer = $(e).attr("maxPer");
	var joinNum = parseInt($('input[name=joinNum]').attr('value'));

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '게스트를 삭제하시겠습니까?',
		icon: 'warning',
		cancelButtonText: '취소',
		showCancelButton: true,
		showCloseButton: true,
		confirmButtonText: '삭제',
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: "post",
				url: "/join/delete",
				data: { "no": no, "id": id }
			});

			joinNum -= 1;

			$("#joinNum").html(joinNum + "명");
			$('input[name=joinNum]').attr('value', joinNum);

			document.getElementById(id).remove();

			if (maxPer > joinNum) {
				$("#addGuest").show();
			}
		}
	})
}

// 조인 게스트 출력
function guestInfo(no, maxPer) {
	// 메인 게스트 이미지 설정 (kakao)
	$("#main-img").attr("src", $("#img").attr("src"));

	var joinNum = parseInt($('input[name=joinNum]').attr('value'));

	var s = "";

	$.ajax({
		type: "post",
		dataType: "json",
		data: { "no": no },
		url: "/join/guestlist",
		success: function(data) {
			data.forEach(function(element) {
				s += '<div class="join-guest-wrap" id="' + element.memDto.id + '">';
				s += '<hr>';
				s += '<div class="join-guest-de">';
				s += '<div class="join-guest-img">';
				s += '<img id="join-guest" src="../../photo/memberPhoto/' + element.memDto.photo + '">';
				s += '</div>';
				s += '<label>' + element.memDto.id + '</label>';
				s += '<button type="button" id="joinDel" class="btn btn-danger" onclick="delGuest(this)"';
				s += 'resNo="' + element.joinDto.no + '" guestId="' + element.memDto.id + '" joinNum="' + joinNum + '"';
				s += 'maxPer="' + maxPer + '">게스트 삭제</button>';
				s += '</div>';
				s += '</div>';
			});

			$(".join-guest").html(s);
		}
	});
}

// 별점 주기
function getStarNum(e) {
	var clickId = $(e).attr("id");
	var rating = "0.0";

	if (clickId == "star-1") {
		rating = "1.0";
	} else if (clickId == "star-2") {
		rating = "2.0";
	} else if (clickId == "star-3") {
		rating = "3.0";
	} else if (clickId == "star-4") {
		rating = "4.0";
	} else if (clickId == "star-5") {
		rating = "5.0";
	}

	$('input[name=rate]').attr('value', rating);
	$('span[id=starNum]').html(rating);
}

// 별점 입력
function changeStarNum(e) {
	var clickId = $(e).attr("id");
	var rating = "0";

	if (clickId == "star-1") {
		rating = "1";
	} else if (clickId == "star-2") {
		rating = "2";
	} else if (clickId == "star-3") {
		rating = "3";
	} else if (clickId == "star-4") {
		rating = "4";
	} else if (clickId == "star-5") {
		rating = "5";
	}

	$('input[name=rate-u]').attr('value', rating);
	$('span[id=starNum]').html(rating);
}

// 업데이트 별점 수정
function changeStarNumUpdate(e) {
	var clickId = $(e).attr("id");
	var rating = "0";

	if (clickId == "star-1-u") {
		rating = "1";
	} else if (clickId == "star-2-u") {
		rating = "2";
	} else if (clickId == "star-3-u") {
		rating = "3";
	} else if (clickId == "star-4-u") {
		rating = "4";
	} else if (clickId == "star-5-u") {
		rating = "5";
	}

	$('input[name=rate-u]').attr('value', rating);
	$('span[id=starNum]').html(rating);
}


// 후기 입력
$("#insert-btn").click(function() {
	var content = $('.content-input').val();
	content = content.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	$(".content-input").val(content);

	//rate하나만 선택해도 star1에는 값 정해짐 (초기값 0.0)
	$.ajax({
		data: { "reserNo": $("#reserNo").val(), "content": content, "rate": $("#star-1").val() },
		type: "post",
		dataType: "json",
		url: "/comment/insert",
		success: function(data) {
			$("#updateDeleteContainer").show();
			$("#insertContainer").hide();

			var s = `
		    	<span class="date">작성일 | ` + data.write_day.substr(0, 4) + "년 " + data.write_day.substr(5, 2) + "월 " + (Number(data.write_day.substr(8, 2)) + 1) + "일 " + `</span>
			`;

			$(".date-part").html(s);

			$("#starNum").text($("#star-1").val().substr(0, 1));
			$("input[name=rate-u]").val(data.rating + ".0");
			$("#star-" + data.rating + "-u").attr("checked", "checked");

			//input-container 초기화
			$("input[name=rate]").attr("value", "0.0");
			$("input[name=rate]").attr("checked", "false");
			$(".content-input").val("");
			$(".content-update").val(data.content);
		}
	});
	//document.commentInsert.submit();
});

//후기 수정
$("#update-btn").click(function() {
	var content = $('.content-update').val();
	content = content.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	$(".content-input").val(content);

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '댓글을 수정하시겠습니까?',
		icon: 'warning',
		cancelButtonText: '취소',
		showCancelButton: true,
		showCloseButton: true,
		confirmButtonText: '수정',
	}).then((result) => {
		$.ajax({
			data: { "reserNo": $("#reserNo").val(), "content": content, "rate": $("#star-1-u").val() },
			type: "post",
			dataType: "json",
			url: "/comment/update",
			success: function(data) {
				$("#content-update").val(data.content);
				$("#insertContainer").hide();
				$("#updateDeleteContainer").show();
			},
			error: function() {
				$("#insertContainer").hide();
				$("#updateDeleteContainer").show();
			}
		});
	});
	//document.commentUpdate.submit();
});

//후기 삭제
$("#delete-btn").click(function() {

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	});

	swalWithBootstrapButtons.fire({
		title: '후기를 삭제하시겠습니까?',
		icon: 'warning',
		cancelButtonText: '취소',
		showCancelButton: true,
		showCloseButton: true,
		confirmButtonText: '삭제',
	}).then((result) => {

		$.ajax({
			type: "post",
			url: "/comment/delete",
			data: { "no": $("#reserNo").val() },
			success: function() {
				$("input:[name=rate]").attr("value", "0.0");
				$("input[name=rate]").removeAttr("checked");
				$("input[name=rate-u]").attr("value", "0.0");
				$("input[name=rate-u]").removeAttr("checked");
				$(".numb::before").css("content", "0");
				$(".numa::before").css("content", "0");
				$(".content-input").val("");
				$(".content-update").val("");
				$("#updateDeleteContainer").hide();
				$("#insertContainer").show();
			},
			error: function() {
				$("input[name=rate]").attr("value", "0.0");
				$("input[name=rate]").removeAttr("checked");
				$("input[name=rate-u]").attr("value", "0.0");
				$("input[name=rate-u]").removeAttr("checked");
				$(".numb::before").css("content", "0");
				$(".numa::before").css("content", "0");
				$(".content-input").val("");
				$(".content-update").val("");
				$("#updateDeleteContainer").hide();
				$("#insertContainer").show();
			}
		});
	});
});

//엔터값 출력
$(document).ready(function() {
	var content = $(".content-input").val();
	result = content.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');

	$(".content-input").val(result);
});

// 예약 취소
function reserCan(e) {
	var no = $(e).attr("no");
	var price = $(e).attr("price");

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: '예약을 취소하시겠습니까?',
		text: "취소 후 숙소 재예약이 어려울 수 있습니다.",
		icon: 'warning',
		cancelButtonText: '취소',
		showCancelButton: true,
		showCloseButton: true,
		confirmButtonText: '예약 취소',
	}).then((result) => {
		if (result.isConfirmed) {
			swalWithBootstrapButtons.fire(
				'예약이 취소되었습니다.',
				'환불 규정에 맞춰 환불이 진행될 예정입니다.',
				'success'
			)

			$(e).attr("class", "btn btn-secondary");
			$(e).attr("onclick", "");
			$(e).attr("style", "pointer-events: none;");
			$(e).html("예약 취소가 진행 중입니다.");

			$.ajax({
				type: "post",
				url: "/reser/delete",
				data: { "no": no, "price": price }
			});
		}
	})
}

// 모달 창 닫을 때 이벤트 (body 화면 줄어듦)
$(document).ready(function() {
	$('#guestList').on('hidden.bs.modal', function() {
		document.body.style.padding = "0";
	});
});

// 영수증 출력
function postPopUp() {
	frm = document.getElementById("receiptPop");

	window.open('', 'popup', 'width=1100, height=700, top=30, left=120');

	frm.action = "/receipt/detail";
	frm.target = "popup";
	frm.method = "post";
	frm.submit();
}