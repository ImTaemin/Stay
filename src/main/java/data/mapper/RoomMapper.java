package data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.RoomDto;

@Mapper
public interface RoomMapper {
	public int getRoomCount();
	public void insertRoom(RoomDto roomDto);
	public List<RoomDto> getRooms(HashMap<String, Integer> map);
	public RoomDto getRoom(String no);
}