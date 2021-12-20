package stay.data.repository;

public interface MemberRepository {
	// 아이디 중복 체크
    public int idCheck(String id);
}
