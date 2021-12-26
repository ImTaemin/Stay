function formCheck(){
	var kakao = $("#kakao").attr("check");
	var card = $("#card").attr("check");
	
	if (kakao == "0" && card == "0") {
		$("#frm").attr("action", "");
	
		Swal.fire({
			icon: 'error',
			title: '결제 방법을 선택해주세요.'
		});
	} else if(kakao == "1" && card == "0"){
		$.ajax({
			url:"kakaopay",
			dataType:"json",
		}).done(function(resp){
			if(resp.status === 500){
				alert("카카오페이결제를 실패하였습니다.")
			} else{
				 // alert(resp.tid); //결제 고유 번호
				var box = resp.next_redirect_pc_url;
				//window.open(box); // 새창 열기
				location.href = box;
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	} else {
		$("#frm").submit();
	}
}