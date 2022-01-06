package stay.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.JoinGuestDto;
import stay.data.dto.ResultMapDto;

@Mapper
public interface JoinGuestMapper {
	public int countJoinGuest(String no);
	public int sameIdinJoin(HashMap<String, String> map);
	public void insertJoinGuest(JoinGuestDto dto);
	public List<ResultMapDto> getAllJoinGuest(String no);
	public void deleteJoinGuest(HashMap<String, String> map);
	public JoinGuestDto selectOneJoin(String no);
	public List<JoinGuestDto> selectListJoin(String no);
}