package stay.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.CanReservationDto;
import stay.data.dto.ResultMapDto;

@Mapper
public interface CanReservationMapper {
	public void insertCanReser(CanReservationDto dto);
	public CanReservationDto getOneCanReser(String no);
	public List<ResultMapDto> getAllCanReser(String guest_id);
	public List<ResultMapDto> getAllHostCanReser(String host_id);
}