package stay.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.MemberDto;
import stay.data.mapper.MemberMapper;
import stay.data.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	MemberMapper mapper;
	
	@Autowired
    private MemberRepository memberRepository;

    // 아이디 중복 체크
    public int idCheck(String id) {
        int cnt = memberRepository.idCheck(id);
        return cnt;
    }
    
    public MemberDto getMember(String id) {
    	return mapper.getMember(id);
    }
	
	public void insertPhoto(MemberDto memberDto) {
		mapper.insertPhoto(memberDto);
	}
	
	
}