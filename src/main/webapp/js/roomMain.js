// 지도 생성
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center: new kakao.maps.LatLng(37.498095, 127.027610), // 지도의 중심좌표
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


// 스크롤 div
$(document).ready(function() {
	// 기존 css에서 플로팅 배너 위치(top)값을 가져와 저장한다.
	var floatPosition = parseInt($(".map-wrap").css('top'));

	$(window).scroll(function() {
		// 현재 스크롤 위치를 가져온다.
		var scrollTop = $(window).scrollTop();
		var newPosition = scrollTop + (floatPosition - 60) + "px";

		if (scrollTop != 0) {
			$(".map-wrap").css('top', newPosition);
		} else {
			$(".map-wrap").css('top', floatPosition);
		}
	}).scroll();
});

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
			data: {"roomId" : roomId}
		});
	}
}