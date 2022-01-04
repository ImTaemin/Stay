package stay.data.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.MemberLikeDto;
import stay.data.mapper.MemberLikeMapper;

@Service
public class MemberLikeService {
	@Autowired
	MemberLikeMapper mapper;
	
	public int getSameLike(String guest_id, String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("guest_id", guest_id);
		map.put("id", id);
		
		return mapper.getSameLike(map);
	}
	
	public int getGuestLikeNum(String id) {
		return mapper.getGuestLikeNum(id);
	}
	
	public void insertLike(MemberLikeDto dto) {
		mapper.insertLike(dto);
	}
	
	public void deleteLike(String guest_id, String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("guest_id", guest_id);
		map.put("id", id);
		
		mapper.deleteLike(map);
	}
}