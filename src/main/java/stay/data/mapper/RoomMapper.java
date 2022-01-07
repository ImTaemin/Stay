package stay.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.ResultMapDto;
import stay.data.dto.RoomDto;

@Mapper
public interface RoomMapper {
	public int getRoomCount();
	public int getRoomSearchCount(HashMap<String,String> map);
	public int getRoomMaxNo();
	public void insertRoom(RoomDto roomDto);
	public List<ResultMapDto> getSearchPageRoom(HashMap<String, Object> map);
	public List<ResultMapDto> getPageRoom(HashMap<String, Integer> map);
	public RoomDto getRoom(String no);
	public ResultMapDto getOneRoom(String no);
	public void updateRoom(RoomDto roomDto);
	public List<RoomDto> getBestRoom();
	public List<RoomDto> getRoomSite(String search);
	public List<RoomDto> getRoomList(String hostId);
	public boolean getRoomHosting(String no);
}