package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.ReservationDto;
import stay.data.dto.ResultMapDto;
import stay.data.mapper.ReservationMapper;

@Service
public class ReservationService {
	@Autowired
	ReservationMapper mapper;
	
	public void insertReservation(ReservationDto reDto) {
		mapper.insertReservation(reDto);
	}
	
	public String getReserMaxNo() {
		return mapper.getReserMaxNo();
	}
	
	public List<ResultMapDto> selectNowGuestReservation(String guestId) {
		return mapper.selectNowGuestReservation(guestId);
	}
	
	public List<ResultMapDto> selectNowHostReservation(String hostId) {
		return mapper.selectNowHostReservation(hostId);
	}
	
	public List<ResultMapDto> selectPreGuestReservation(String guestId) {
		return mapper.selectPreGuestReservation(guestId);
	}
	
	public List<ResultMapDto> selectPreHostReservation(String hostId) {
		return mapper.selectPreHostReservation(hostId);
	}
	
	public List<ReservationDto> selectHostReservation(String hostId) {
		return mapper.selectHostReservation(hostId);
	}
	
	public List<ResultMapDto> selectHostThreeReservation(String hostId) {
		return mapper.selectHostThreeReservation(hostId);
	}
	
	public List<ResultMapDto> selectCheckInHostReservation(String hostId) {
		return mapper.selectCheckInHostReservation(hostId);
	}
	
	public List<ResultMapDto> selectCheckOutHostReservation(String hostId) {
		return mapper.selectCheckOutHostReservation(hostId);
	}
	
	public List<ResultMapDto> selectHostingHostReservation(String hostId) {
		return mapper.selectHostingHostReservation(hostId);
	}
	
	public ReservationDto selectGuestOneReservation(String no, String guestId) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("guestId", guestId);
		
		return mapper.selectGuestOneReservation(map);
	}
	
	public ReservationDto selectHostOneReservation(String no, String hostId) {
		
		System.out.println(no +" " + hostId);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("no", no);
		map.put("hostId", hostId);
		
		return mapper.selectHostOneReservation(map);
	}
	
	public String getRoomNo(String reserNo) {
		return mapper.getRoomNo(reserNo);
	}
}