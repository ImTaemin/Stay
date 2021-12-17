package data.mapper;

import org.apache.ibatis.annotations.Mapper;

import data.dto.RoomDto;

@Mapper
public interface RoomMapper {
	public int getRoomCount();
	public void insertRoom(RoomDto roomDto);
}