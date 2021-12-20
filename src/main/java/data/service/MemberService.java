package data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.MemberDto;
import data.dto.RoomDto;
import data.mapper.MemberMapper;
import data.mapper.RoomMapper;

@Service
public class MemberService {
	@Autowired
	MemberMapper mapper;
	
	public void insertPhoto(MemberDto memberDto) {
		mapper.insertPhoto(memberDto);
	}
	
}