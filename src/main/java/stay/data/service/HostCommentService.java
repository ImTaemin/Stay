package stay.data.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.HostCommentDto;
import stay.data.mapper.HostCommentMapper;

@Service
public class HostCommentService {
	@Autowired
	HostCommentMapper mapper;

	public HostCommentDto getOneHostComment(String no) {
		return mapper.getOneHostComment(no);
	}

	public void insertHostComment(HostCommentDto dto) {
		mapper.insertHostComment(dto);
	}

	public void updateHostComment(HostCommentDto dto) {
		mapper.updateHostComment(dto);
	}

	public void deleteHostComment(String no, String host_id) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("no", no);
		map.put("host_id", host_id);

		mapper.deleteHostComment(map);
	}

	public int checkRecommentGuest(String no, String guest_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guest_id", guest_id);
		
		return mapper.checkRecommentGuest(map);
	}
	
	public int checkRecommentHost(String no, String host_id) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("no", no);
		map.put("host_id", host_id);

		return mapper.checkRecommentHost(map);
	}
}
