package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.ResultMapDto;
import stay.data.dto.RoomDto;
import stay.data.mapper.RoomMapper;

@Service
public class RoomService {
	@Autowired
	RoomMapper mapper;
	
	public int getRoomCount() {
		return mapper.getRoomCount();
	}

	public int getRoomSearchCount(String addr_load, String from, String to) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("addr_load", addr_load);
		map.put("from", from);
		map.put("to", to);
		return mapper.getRoomSearchCount(map);
	}
	
	public int getRoomMaxNo() {
		return mapper.getRoomMaxNo();
	}
	
	public void insertRoom(RoomDto roomDto) {
		mapper.insertRoom(roomDto);
	}
	
	public List<ResultMapDto> getSearchPageRoom(int start, int perPage, String addr_load, String from, String to){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("perPage", perPage);
		map.put("addr_load", addr_load);
		map.put("from", from);
		map.put("to", to);
		
		return mapper.getSearchPageRoom(map);
	}
	
	public List<ResultMapDto> getPageRoom(int start, int perPage) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("start", start);
		map.put("perPage", perPage);
		
		return mapper.getPageRoom(map);
	}
	
	public RoomDto getRoom(String no) {
		return mapper.getRoom(no);
	}
	
	public ResultMapDto getOneRoom(String no) {
		return mapper.getOneRoom(no);
	}
	
	public void updateRoom(RoomDto roomDto) {
		mapper.updateRoom(roomDto);
	}
	
	public List<RoomDto> getBestRoom() {
		return mapper.getBestRoom();
	}
	
	public List<RoomDto> getRoomSite(String search){
		return mapper.getRoomSite(search);
	}
	
	public List<RoomDto> getRoomList(String hostId){
		return mapper.getRoomList(hostId);
	}
	
	public boolean getRoomHosting(String no) {
		return mapper.getRoomHosting(no);
	}
}