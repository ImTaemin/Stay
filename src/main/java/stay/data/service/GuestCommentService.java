package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.GuestCommentDto;
import stay.data.dto.ResultMapDto;
import stay.data.mapper.GuestCommentMapper;

@Service
public class GuestCommentService {
	@Autowired
	GuestCommentMapper mapper;
	
	public GuestCommentDto getOneComment(String no, String guest_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guest_id", guest_id);
		
		return mapper.getOneComment(map);
	}
	
	public List<ResultMapDto> getRoomComment(String roomNo) {
		return mapper.getRoomComment(roomNo);
	}
	
	public void insertGuestComment(GuestCommentDto gcdto) {
		mapper.insertGuestComment(gcdto);
	}
	
	public void updateGuestComment(GuestCommentDto gcdto) {
		mapper.updateGuestComment(gcdto);
	}
	
	public void deleteGuestComment(String no) {
		mapper.deleteGuestComment(no);
	}
	
	public List<ResultMapDto> selectOneGuest(String id) {
		return mapper.selectOneGuest(id);
	}
	
	public int countGuestComment(String id) {
		return mapper.countGuestComment(id);
	}
	
	public void updateLikes(String no, String guest_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guest_id", guest_id);
		
		mapper.updateLikes(map);
	}
}