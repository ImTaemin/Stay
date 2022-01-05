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
				type: "get",
				url: "/reser/delete",
				data: { "no": no, "price": price }
			});
		}
	})
}

// 환불 진행
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
		title: '환불을 진행하시겠습니까?',
		icon: 'warning',
		cancelButtonText: '취소',
		showCancelButton: true,
		showCloseButton: true,
		confirmButtonText: '환불',
	}).then((result) => {
		if (result.isConfirmed) {
			swalWithBootstrapButtons.fire(
				'환불이 진행중입니다.',
				'환불 규정에 맞춰 환불이 진행될 예정입니다.',
				'success'
			)
			$(e).attr("class", "btn btn-secondary");
			$(e).attr("onclick", "");
			$(e).attr("style", "pointer-events: none;");
			$(e).html("결제 환불이 진행 중입니다.");

			$.ajax({
				type: "post",
				url: "/reser/refund",
				data: { "no": no }
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
				if (element.memDto.photo.indexOf("@") != -1) {
					s += '<img id="join-guest" src="../../photo/memberPhoto/' + element.memDto.photo + '">';
				} else {
					s += '<img id="join-guest" src="' + element.memDto.photo + '">';
				}
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