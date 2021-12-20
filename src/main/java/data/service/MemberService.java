package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.MemberDto;
import data.mapper.MemberMapper;

@Service
public class MemberService {
	@Autowired
	MemberMapper mapper;
	
	public void insertPhoto(MemberDto memberDto) {
		mapper.insertPhoto(memberDto);
	}
	
}