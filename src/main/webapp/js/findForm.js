//핸드폰 번호 하이픈(-) 자동 입력
$(document).on("keyup", ".phoneNumber", function() { 
	$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
});

$(function(){
  $("#searchBtn").click(function(){
  	$(".modal").fadeIn();
  });
  
  $(".close").click(function(){
  	$(".modal").fadeOut();
  });

});

/*//아이디 검색 결과 모달
$(document).ready(function() {
	/////////모///달///기///능///////////
	// 1. 모달창 히든 불러오기
	$('#searchBtn').click(function() {
		$('#background_modal').show();
	});
	// 2. 모달창 닫기 버튼
	$('.close').on('click', function() {
		$('#background_modal').hide();
	});
	// 3. 모달창 위도우 클릭 시 닫기
	$(window).on('click', function() {
		if (event.target == $('#background_modal').get(0)) {
            $('#background_modal').hide();
         }
	});
});*/

//*****아이디 찾기*****
$(document).on('click','#searchBtn',function(inputName, inputHp){

  var name=$('#inputName').val();
  var hp=$('#inputHp').val();
  
  var postdata={'name':inputName,'hp':inputHp};
  
  postdata= JSON.stringify(postdata);
  
  // 아이디 값 받고 출력하는 ajax
	$.ajax({
		url:"/member/findIdprocess",
		type:"POST",
		data:postdata,
		dataType : "json",
		success:function(data){
			var findId=data.id;
			if(data == 0){
				$('#id_value').text("회원 정보를 확인해주세요");	
			} else {
				$('#id_value').text(findId);
			}
		}
	});
 });
	
	