package stay.data.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.GuestCommentDto;
import stay.data.dto.HostCommentDto;

@Mapper
public interface HostCommentMapper {
//	public List<ResultMapDto> getRoomComment(String roomNo);
	
	public void insertHostComment(HostCommentDto hcdto);
	public void updateHostComment(HostCommentDto hcdto);
	public void deleteHostComment(String no);
	public GuestCommentDto getGuestComment(HashMap<String, String> map);
	public HostCommentDto getHostComment(HashMap<String, String> map);
	public int countHostComment(String no, String id);
	public int checkComment(HashMap<String, String> map);
	
}