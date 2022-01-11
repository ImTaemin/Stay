package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.HostCommentDto;

@Mapper
public interface HostCommentMapper {
	public HostCommentDto getOneHostComment(String no);
	public void insertHostComment(HostCommentDto dto);
}