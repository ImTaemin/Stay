package stay.data.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.MemberDto;
import stay.data.dto.ReportMemberDto;
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
	
	public void updateMember(MemberDto dto) {
		mapper.updateMember(dto);
	}
	
	public void updateImg(MemberDto memberDto) {
		mapper.updateImg(memberDto);
	}
	
	//아이디 찾기
	public String findId(String inputName, String inputHp) {
		
		return mapper.findId(inputName, inputHp);
	}
	
	//비밀번호 찾기
	public void findPw(String e_mail) throws Exception{
		
		mapper.findPw(e_mail);
	}
	
	public void insertSingoMem(ReportMemberDto rmDto) {
		mapper.insertSingoMem(rmDto);
	}
	
	public List<ReportMemberDto> getSingoMem(String black_id) {
		return mapper.getSingoMem(black_id);
	}
}