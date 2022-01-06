package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.JoinGuestDto;
import stay.data.dto.ResultMapDto;
import stay.data.mapper.JoinGuestMapper;

@Service
public class JoinGuestService {
	@Autowired
	JoinGuestMapper mapper;
	
	public int countJoinGuest(String no) {
		return mapper.countJoinGuest(no);
	}
	
	public int sameIdinJoin(String no, String id) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("no", no);
		map.put("id", id);
		
		return mapper.sameIdinJoin(map);
	}
	
	public void insertJoinGuest(JoinGuestDto dto) {
		mapper.insertJoinGuest(dto);
	}
	
	public List<ResultMapDto> getAllJoinGuest(String no) {
		return mapper.getAllJoinGuest(no);
	}
	
	public void deleteJoinGuest(String no, String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("id", id);
		
		mapper.deleteJoinGuest(map);
	}
	
	public JoinGuestDto selectOneJoin(String no) {
		return mapper.selectOneJoin(no);
	}

	public List<JoinGuestDto> selectListJoin(String no) {
		return mapper.selectListJoin(no);
	}
}