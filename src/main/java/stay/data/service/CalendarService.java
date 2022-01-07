package stay.data.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.RoomHostingDto;
import stay.data.dto.RoomPirceDto;
import stay.data.mapper.CalendarMapper;

@Service
public class CalendarService {
	@Autowired
	CalendarMapper mapper;

	// hosting
	public void insertHosting(RoomHostingDto dto) {
		mapper.insertHosting(dto);
	}

	public void deleteHosting(String no, String change_date) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("no", no);
		map.put("change_date", change_date);

		mapper.deleteHosting(map);
	}
	
	public RoomHostingDto getDateRoomHosting(String no, String change_date) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("no", no);
		map.put("change_date", change_date);

		return mapper.getDateRoomHosting(map);
	}
	
	// price
	public void insertPrice(RoomPirceDto dto) {
		mapper.insertPrice(dto);
	}

	public void deletePrice(String no, String change_date) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("no", no);
		map.put("change_date", change_date);

		mapper.deletePrice(map);
	}
	
	public RoomPirceDto getDateRoomPrice(String no, String change_date) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("no", no);
		map.put("change_date", change_date);

		return mapper.getDateRoomPrice(map);
	}
	
	public void updatePrice(RoomPirceDto dto) {
		mapper.updatePrice(dto);
	}
}