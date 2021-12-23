
function payClick(e){
	
	
	if($(e).attr("id") == "kakaoI"){
		if($("#kakaochk") == null){
			//카카오 체크
			$(e).attr("class", "bi bi-check-circle-fill");
			
			var chkKakao = document.createElement("input");
			chkKakao.setAttribute("type","hidden");
			chkKakao.setAttribute("value","kakao");//받은 값이 kakao
			chkKakao.setAttribute("name","kakao");//값 넘기려고
			chkKakao.setAttribute("id","kakaochk");
			
			document.querySelector("#kakao").append(chkKakao);
			
			if(document.getElementById("cardchk") == null){
				document.getElementById("cardchk").remove();
			}
			$("cardI").attr("class", "bi bi-circle");
		}
	}else if($(e).attr("id") == "cardI"){
		if( $("#cardchk") == null){
			//카드 체크
			$(e).attr("class", "bi bi-check-circle-fill");
			var chkCard = document.createElement("input");
			chkCard.setAttribute("type","hidden");
			chkCard.setAttribute("value","card");
			chkCard.setAttribute("name","card");
			chkCard.setAttribute("id","cardchk");
			document.querySelector("#card").append(chkCard);
			
			$("#kakaoI").attr("class", "bi bi-circle");
			if(document.getElementById("kakaochk") == null){
				document.getElementById("kakaochk").remove();
			}
			var s = "";
			s += ``;
			$(".wallet").html(s);	
		}
	}
}
//
//function kakaoClick(e) {
//	
//	var chkKakao;
//	
//	if(tmp) {
//		$(e).attr("class", "bi bi-check-circle-fill");
//		chkKakao = document.createElement("input");
//		chkKakao.setAttribute("type","hidden");
//		chkKakao.setAttribute("value","kakao");
//		chkKakao.setAttribute("name","kakao");
//		chkKakao.setAttribute("id","kakaochk");
//		document.querySelector("#kakao").append(chkKakao);
//		
//		document.getElementById("cardchk").remove();
//		$("cardI").attr("class", "bi bi-circle");
//		
//		tmp = false;
//	} else {
//		$("cardI").attr("class", "bi bi-circle");
//		document.getElementById("kakaochk").remove();
//		tmp = true;
//	}
//}
//
//function cardClick(e) {
//	
//	var chkCard;
//	
//	if(tmp) {
//		$(e).attr("class", "bi bi-check-circle-fill");
//		tmp = false;
//		
//		chkCard = document.createElement("input");
//		chkCard.setAttribute("type","hidden");
//		chkCard.setAttribute("value","card");
//		chkCard.setAttribute("name","card");
//		chkCard.setAttribute("id","cardchk");
//		document.querySelector("#card").append(chkCard);
//		
//		$("#kakaoI").attr("class", "bi bi-circle");
//		document.getElementById("kakaochk").remove();
//		
//		var s = "";
//		s += ``;
//		
//		$(".wallet").html(s);
//	} else {
//		$("#kakaoI").attr("class", "bi bi-circle");
//		document.getElementById("kakaochk").remove();
//		tmp = true;
//	}
//}