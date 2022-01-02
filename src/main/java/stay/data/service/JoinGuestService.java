package stay.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.JoinGuestDto;
import stay.data.mapper.JoinGuestMapper;

@Service
public class JoinGuestService {
	@Autowired
	JoinGuestMapper mapper;
	
	public int countJoinGuest(String no) {
		return mapper.countJoinGuest(no);
	}
	
	public void insertJoinGuest(JoinGuestDto dto) {
		mapper.insertJoinGuest(dto);
	}
}