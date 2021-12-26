package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.ReservationDto;

@Mapper
public interface ReservationMapper {
	public void insertReservation(ReservationDto reDto);
}