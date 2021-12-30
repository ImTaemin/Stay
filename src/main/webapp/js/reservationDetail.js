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

// 별점 주기
function getStarNum(e) {
	var clickId = $(e).attr("id");
	var rating = "0.0";
	
	if(clickId == "star-1") {
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
	
	if(clickId == "star-1") {
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