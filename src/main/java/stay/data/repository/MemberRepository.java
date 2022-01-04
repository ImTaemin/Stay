package stay.data.repository;

import javax.servlet.http.HttpServletResponse;


public interface MemberRepository {
	// 아이디 중복 체크
    public int idCheck(String id);
    
    //이메일발송
  	public void sendEmail(String id, String e_mail, String div) throws Exception;

  	//비밀번호찾기
  	public void findPw(String id, String e_mail, HttpServletResponse response) throws Exception;
}
