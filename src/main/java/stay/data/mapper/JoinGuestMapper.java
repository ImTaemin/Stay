package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.JoinGuestDto;

@Mapper
public interface JoinGuestMapper {
	public int countJoinGuest(String no);
	public void insertJoinGuest(JoinGuestDto dto);
}