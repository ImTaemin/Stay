package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestCommentMapper {
	public Float getRatingAvg();
	public Integer totalComment();
}