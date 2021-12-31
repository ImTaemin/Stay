package stay.data.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
