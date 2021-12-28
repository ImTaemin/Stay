package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinGuestMapper {
	public int countJoinGuest(String no);
}