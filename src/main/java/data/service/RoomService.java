package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.RoomDto;
import data.mapper.RoomMapper;

@Service
public class RoomService {
	@Autowired
	RoomMapper mapper;
	
	public int getRoomCount() {
		return mapper.getRoomCount();
	}
	
	public void insertRoom(RoomDto roomDto) {
		mapper.insertRoom(roomDto);
	}
}