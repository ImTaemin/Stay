package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.CommentLikeDto;
import stay.data.mapper.CommentLikeMapper;

@Service
public class CommentLikeService {
	@Autowired
	CommentLikeMapper mapper;
	
	public int countLike(String no, String guest_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guest_id", guest_id);
		
		return mapper.countLike(map);
	}
	
	public void insertLike(CommentLikeDto likeDto) {
		mapper.insertLike(likeDto);
	}
	
	public void deleteLike(String no, String guest_id, String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guest_id", guest_id);
		map.put("id", id);
		
		mapper.deleteLike(map);
	}
	
	public List<CommentLikeDto> getLike(String id) {
		return mapper.getLike(id);
	}
}
