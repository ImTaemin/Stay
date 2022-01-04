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

//*****아이디 찾기*****
$(document).on('click','#searchBtn',function(inputName, inputHp){

  var name=$('#inputName').val();
  var hp=$('#inputHp').val();
  
  var postdata={'name':name,'hp':hp};
  
  // 아이디 값 받고 출력하는 ajax
	$.ajax({
		url:"/member/findIdprocess",
		type:"post",
		data:postdata,
		dataType : "json",
		success:function(data){
			console.log(data);
			var findId=data.id;
			if(findId == null){
				$('#id_user').text();	
				$('#id_value').html("일치하는 회원정보가<br> 없습니다");
			} else {
				$('#id_user').text("회원님의 아이디는");
				$('#id_value').text(findId);
			}
		}
	});
 });
 
 
//*****비밀번호 찾기*****
$(document).on('click','#searchBtn2',function(){
	$.ajax({
		url : "/member/findPw",
		type : "POST",
		data : {
			id : $("#id").val(),
			e_mail : $("#e_mail").val()
		},
		success : function(result) {
			alert(result);
		},
	});
});
