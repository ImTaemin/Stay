package stay.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.GuestCommentDto;

@Mapper
public interface GuestCommentMapper {
	public Float getRatingAvg();
	public Integer totalComment();
	public List<GuestCommentDto> getAllComment();
	public GuestCommentDto getOneComment(HashMap<String, String> map);
	public void insertGuestComment(GuestCommentDto gcdto);
	public void updateGuestComment(GuestCommentDto gcdto);
}