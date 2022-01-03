package stay.data.repository;

import javax.servlet.http.HttpServletResponse;

import stay.data.dto.MemberDto;

public interface MemberRepository {
	// 아이디 중복 체크
    public int idCheck(String id);
    
    //이메일발송
  	public void sendEmail(MemberDto mdto, String div) throws Exception;

  	//비밀번호찾기
  	public void findPw(MemberDto mdto, HttpServletResponse response) throws Exception;
}
