package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.ReservationDto;
import stay.data.dto.RoomReservationDto;
import stay.data.mapper.ReservationMapper;

@Service
public class ReservationService {
	@Autowired
	ReservationMapper mapper;
	
	public void insertReservation(ReservationDto reDto) {
		mapper.insertReservation(reDto);
	}
	
	public List<RoomReservationDto> selectNowGuestReservation(String guestId) {
		return mapper.selectNowGuestReservation(guestId);
	}
	
	public List<RoomReservationDto> selectPreGuestReservation(String guestId) {
		return mapper.selectPreGuestReservation(guestId);
	}
	
	public List<ReservationDto> selectHostReservation(String hostId) {
		return mapper.selectHostReservation(hostId);
	}
	
	public List<ReservationDto> selectHostThreeReservation(String hostId) {
		return mapper.selectHostThreeReservation(hostId);
	}
	
	public ReservationDto selectGuestOneReservation(String no, String guestId) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guestId", guestId);
		
		return mapper.selectGuestOneReservation(map);
	}
	
	public ReservationDto selectHostOneReservation(String no, String hostId) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("hostId", hostId);
		
		return mapper.selectHostOneReservation(map);
	}
	
	public String getRoomNo(String reserNo) {
		return mapper.getRoomNo(reserNo);
	}
}