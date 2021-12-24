package stay.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.ReservationDto;
import stay.data.mapper.ReservationMapper;

@Service
public class ReservationService {
	@Autowired
	ReservationMapper mapper;
	
	public void insertReservation(ReservationDto redto) {
		mapper.insertReservation(redto);
	}
}