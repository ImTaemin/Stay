//아이디 중복 확인
function checkId(){
    var id = $('#id').val().trim(); //id값이 "id"인 입력란의 값을 저장
    
    $.ajax({
        url:'/member/idCheck', //Controller에서 인식할 주소
        type:'post', //POST 방식으로 전달
        data:{id:id},
        success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
            if(id=="" && cnt=='0'){ //공백일때
       			$('.id_ok').css("display", "none"); 
        		$('.id_already').css("display", "none");
            } else if(cnt != 1) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                $('.id_ok').css("display", "inline-block"); 
                $('.id_already').css("display", "none");
       		} else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                $('.id_ok').css("display", "none");
                $('.id_already').css("display", "inline-block");
       		}
        },
        error:function(){
            alert("아이디 중복확인 에러");
        }
    });
};


//비밀번호 일치 확인
function checkPw(){

    var pw = document.getElementById('pw').value;
    var SC = ["!","@","#","$","%"];
    var check_SC = 0;
 
    if(pw.length < 6 || pw.length>16){
    	$('.glyphicon-ok-sign').css("display", "none");
        $('.pw_equal').css("display", "none");
        $('.glyphicon-remove-sign').css("display", "inline-block");
        $('.pw_not_equal').css("display", "none");
        $('.pw_not_length').css("display", "inline-block");
        $('.pw_not_sc').css("display", "none");
    } else{
    	$('.glyphicon-ok-sign').css("display", "none");
        $('.pw_equal').css("display", "none");
        $('.glyphicon-remove-sign').css("display", "none");
        $('.pw_not_equal').css("display", "none");
        $('.pw_not_length').css("display", "none");
        $('.pw_not_sc').css("display", "none");
    }
    
    
    for(var i=0;i<SC.length;i++){
        if(pw.indexOf(SC[i]) != -1){
            check_SC = 1;
        }
    }
    if(check_SC == 0){
    	$('.glyphicon-ok-sign').css("display", "none");
        $('.pw_equal').css("display", "none");
        $('.glyphicon-remove-sign').css("display", "inline-block");
        $('.pw_not_equal').css("display", "none");
        $('.pw_not_length').css("display", "none");
        $('.pw_not_sc').css("display", "inline-block");
    }

    
    if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
        if(document.getElementById('pw').value==document.getElementById('pw2').value){
        	$('.glyphicon-ok-sign').css("display", "inline-block");
            $('.pw_equal').css("display", "inline-block");
            $('.glyphicon-remove-sign').css("display", "none");
        	$('.pw_not_equal').css("display", "none");
        	$('.pw_not_length').css("display", "none");
        	$('.pw_not_sc').css("display", "none");
        }
        else{
        	$('.glyphicon-ok-sign').css("display", "none");
            $('.pw_equal').css("display", "none");
            $('.glyphicon-remove-sign').css("display", "inline-block");
        	$('.pw_not_equal').css("display", "inline-block");
        	$('.pw_not_length').css("display", "none");
        	$('.pw_not_sc').css("display", "none");
        }
    } else if(document.getElementById('pw').value ==''){
    	$('.glyphicon-ok-sign').css("display", "none");
        $('.pw_equal').css("display", "none");
        $('.glyphicon-remove-sign').css("display", "none");
        $('.pw_not_equal').css("display", "none");
        $('.pw_not_length').css("display", "none");
        $('.pw_not_sc').css("display", "none");
    }
    
};

// 카카오 주소 API
// 본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("sample4_roadAddress").value = roadAddr;
			document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

			document.getElementById("sample4_engAddress").value = data.addressEnglish;
			
			// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
			if (roadAddr !== '') {
				document.getElementById("sample4_extraAddress").value = extraRoadAddr;
			} else {
				document.getElementById("sample4_extraAddress").value = '';
			}

			var guideTextBox = document.getElementById("guide");
			// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
			if (data.autoRoadAddress) {
				var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
				guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
				guideTextBox.style.display = 'block';

			} else if (data.autoJibunAddress) {
				var expJibunAddr = data.autoJibunAddress;
				guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
				guideTextBox.style.display = 'block';
			} else {
				guideTextBox.innerHTML = '';
				guideTextBox.style.display = 'none';
			}
		}
	}).open();
};