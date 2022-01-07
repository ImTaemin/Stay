package stay.data.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.RoomHostingDto;
import stay.data.dto.RoomPirceDto;

@Mapper
public interface CalendarMapper {
	// hosting
	public void insertHosting(RoomHostingDto dto);
	public void deleteHosting(HashMap<String, String> map);
	public RoomHostingDto getDateRoomHosting(HashMap<String, String> map);
	
	// price
	public void insertPrice(RoomPirceDto dto);
	public void deletePrice(HashMap<String, String> map);
	public RoomPirceDto getDateRoomPrice(HashMap<String, String> map);
	public void updatePrice(RoomPirceDto dto);
}