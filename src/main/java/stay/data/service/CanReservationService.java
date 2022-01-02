package stay.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.CanReservationDto;
import stay.data.dto.ResultMapDto;
import stay.data.mapper.CanReservationMapper;

@Service
public class CanReservationService {
	@Autowired
	CanReservationMapper mapper;
	
	public void insertCanReser(CanReservationDto dto) {
		mapper.insertCanReser(dto);
	}
	
	public CanReservationDto getOneCanReser(String no) {
		return mapper.getOneCanReser(no);
	}
	
	public List<ResultMapDto> getAllCanReser(String guest_id) {
		return mapper.getAllCanReser(guest_id);
	}
}