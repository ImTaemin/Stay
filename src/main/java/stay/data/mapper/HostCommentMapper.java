package stay.data.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.HostCommentDto;

@Mapper
public interface HostCommentMapper {
	public HostCommentDto getOneHostComment(String no);
	public void insertHostComment(HostCommentDto dto);
	public void updateHostComment(HostCommentDto dto);
	public void deleteHostComment(HashMap<String, String> map);
	public int checkRecommentGuest(HashMap<String, String> map);
	public int checkRecommentHost(HashMap<String, String> map);
}