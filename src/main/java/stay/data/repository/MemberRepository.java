package stay.data.repository;

public interface MemberRepository {
	// 아이디 중복 체크
    public int idCheck(String id);
    
    // 아이디,이메일 체크
    public int emailCheck(String id, String e_mail);
}
