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
$(document).on('click','#searchBtn2',function(id, e_mail){

  var id=$('#id').val();
  var e_mail=$('#e_mail').val();
  
  var postdata={'id':id,'e_mail':e_mail};
  
  //정보 일치 확인 ajax
	$.ajax({
		url:"/member/findPwprocess",
		type:"get",
		data:postdata,
		dataType : "json",
		success:function(res){
			if (res['check']) {
	                swal("발송 완료!", "입력하신 이메일로 임시비밀번호가 발송되었습니다.", "success").then((OK) = > {
	                    if(OK) {
	                        $.ajax({
	                            type: "POST",
	                            url: "/member/findPwprocess/sendEmail",
	                            data: {
	                                "id": id,
	                                "e_mail": e_mail
	                            }
	                        })
	                        window.location = "/login";
	                    }
	
	
	                }
	            )
	                $('#id_value').html();
	            } else {
	                $('#id_user').html();	
					$('#id_value').html("일치하는 회원정보가<br> 없습니다");
	            }
       	   }
    });
});
	
	