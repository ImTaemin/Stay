package stay.data.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.GuestCommentDto;
import stay.data.dto.HostCommentDto;
import stay.data.mapper.GuestCommentMapper;
import stay.data.mapper.HostCommentMapper;

@Service
public class HostCommentService {
	
	@Autowired
	GuestCommentMapper gmapper;
	
	@Autowired
	HostCommentMapper hmapper;
	
	public void insertHostComment(HostCommentDto hcdto) {
		
		hmapper.insertHostComment(hcdto);
	}
	
	public void updateHostComment(HostCommentDto hcdto) {
		
		hmapper.updateHostComment(hcdto);
	}
	
	public void deleteHostComment(String no) {
		
		hmapper.deleteHostComment(no);
	}
	
	public GuestCommentDto getGuestComment(String no, String guest_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guest_id", guest_id);
		
		return hmapper.getGuestComment(map);
	}
	
	public HostCommentDto getHostComment(String no, String host_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("host_id", host_id);
		
		return hmapper.getHostComment(map);
	}
	
//	public int countHostComment(String no, String id) {
//		
//		return hmapper.countHostComment(no, id);
//	}
	
	public int checkComment(String no, String host_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("host_id", host_id);
		
		return hmapper.checkComment(map);
	}

}
