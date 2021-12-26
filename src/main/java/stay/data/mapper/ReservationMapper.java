package stay.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.ReservationDto;

@Mapper
public interface ReservationMapper {
	public void insertReservation(ReservationDto reDto);
	public List<ReservationDto> selectReservation(String guestId);
}