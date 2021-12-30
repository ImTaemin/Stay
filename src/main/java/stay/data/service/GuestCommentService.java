package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.GuestCommentDto;
import stay.data.mapper.GuestCommentMapper;

@Service
public class GuestCommentService {
	@Autowired
	GuestCommentMapper mapper;
	
	public Float getRatingAvg() {
		return mapper.getRatingAvg();
	}
	
	public Integer totalComment() {
		return mapper.totalComment();
	}
	
	public List<GuestCommentDto> getAllComment() {
		return mapper.getAllComment();
	}
	
	public GuestCommentDto getOneComment(String no, String guest_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guest_id", guest_id);
		
		return mapper.getOneComment(map);
	}
	
	public void insertGuestComment(GuestCommentDto gcdto) {
		mapper.insertGuestComment(gcdto);
	}
	
	public void updateGuestComment(GuestCommentDto gcdto) {
		mapper.updateGuestComment(gcdto);
	}
}