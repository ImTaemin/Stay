function check(f) {
	//id는 선택, pass는 필수
	//비밀번호 체크
	if(f.pass.value!=f.pass_check.value) {
		alert("비밀번호가 일치하지 않습니다.");
		f.pass.value="";
		f.pass_check.value="";
		return false; //action 호출 x
	}
}