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
	slides.style.left = -num * 820 + "px";
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


// 지도 생성
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center: new kakao.maps.LatLng(37.498095, 127.027610), // 지도의 중심좌표
		draggable: false,	// 드래그 막음
		level: 3 // 지도의 확대 레벨
	};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);


// 컨트롤러
// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


// 하트 클릭 이벤트
var tmp = true;

function heartClick(e) {
	var roomId = $(e).attr("roomID");
	
	if(tmp) {
		$(e).attr("class", "bi bi-heart-fill");
		tmp = false;
		
		$.ajax({
			type: "post",
			url: "/wish/insert",
			data: {"roomId" : roomId}
		});
	} else {
		$(e).attr("class", "bi bi-heart");
		tmp = true;
		
		$.ajax({
			type: "post",
			url: "/wish/delete",
			data: {"roomId" : roomId},
			error: function(request,error) {
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}