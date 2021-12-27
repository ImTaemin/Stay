package stay.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.ReservationDto;
import stay.data.mapper.ReservationMapper;

@Service
public class ReservationService {
	@Autowired
	ReservationMapper mapper;
	
	public void insertReservation(ReservationDto reDto) {
		mapper.insertReservation(reDto);
	}
	
	public List<ReservationDto> selectReservation(String guestId) {
		return mapper.selectReservation(guestId);
	}
	
	public List<ReservationDto> selectHostReservation(String hostId) {
		return mapper.selectHostReservation(hostId);
	}
	
	public List<ReservationDto> selectHostThreeReservation(String hostId){
		return mapper.selectHostThreeReservation(hostId);
	}
}