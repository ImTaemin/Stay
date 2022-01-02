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
	var joinNum = parseInt($(e).attr("joinNum"));

	Swal.fire({
		title: '게스트 추가하기',
		input: 'text',
		inputAttributes: {
			autocapitalize: 'off'
		},
		showCancelButton: true,
		cancelButtonText: '취소',
		confirmButtonText: '아이디 찾기',
		showLoaderOnConfirm: true,
		preConfirm: (id) => {
			$.ajax({
				type: "post",
				url: "/join/search",
				data: { "id": id },
				success: function(data) {
					if (data == true) {
						$.ajax({
							type: "post",
							url: "/join/insert",
							data: { "no": no, "id": id },
							success: function() {
								Swal.fire({
									icon: 'success',
									title: '게스트가 추가되었습니다.'
								})
							}
						});
						
						joinNum += 1;
						
						$(e).attr("joinNum", joinNum);
						$("#joinNum").html(joinNum + "명");
					} else if (id == '') {
						Swal.fire({
							icon: 'error',
							title: '아이디를 입력해주세요.',
						})
					} else {
						Swal.fire({
							icon: 'error',
							title: id + '는 없는 아이디 입니다.',
						})
					}
				}
			});
		}
	})
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
}

// 별점 수정
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

	$('input[name=rate]').attr('value', rating);
	$('span[id=starNum]').html(rating);
}

// 엔터값 변경
$("#insert-btn").click(function() {
	var content = $('.content-input').val();
	content = content.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	$(".content-input").val(content);

	document.commentInsert.submit();
});

$("#update-btn").click(function() {
	var content = $('.content-input').val();
	content = content.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	$(".content-input").val(content);

	document.commentUpdate.submit();
});

//엔터값 출력
$(document).ready(function() {
	var content = $(".content-input").val();
	result = content.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');

	$(".content-input").val(result);
})

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