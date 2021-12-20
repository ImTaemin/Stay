package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.MemberDto;
import data.mapper.MemberMapper;
import data.repository.MemberRepository;

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
	
	public void insertPhoto(MemberDto memberDto) {
		mapper.insertPhoto(memberDto);
	}
	
}