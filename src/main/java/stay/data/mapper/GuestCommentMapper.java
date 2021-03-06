package stay.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.GuestCommentDto;
import stay.data.dto.ResultMapDto;

@Mapper
public interface GuestCommentMapper {
	public GuestCommentDto getOneComment(String no);
	public List<ResultMapDto> getRoomComment(String roomNo);
	public void insertGuestComment(GuestCommentDto gcdto);
	public void updateGuestComment(GuestCommentDto gcdto);
	public void deleteGuestComment(String no);
	public List<ResultMapDto> selectOneGuest(String id);
	public int countGuestComment(String id);
	public int checkCommentGuest(HashMap<String, String> map);
	public int checkCommentHost(HashMap<String, String> map);
	
	public void updateLikes(HashMap<String, String> map);
}