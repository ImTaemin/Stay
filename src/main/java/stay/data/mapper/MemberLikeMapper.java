package stay.data.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.MemberLikeDto;

@Mapper
public interface MemberLikeMapper {
	public int getSameLike(HashMap<String, String> map);
	public int getGuestLikeNum(String id);
	public void insertLike(MemberLikeDto dto);
	public void deleteLike(HashMap<String, String> map);
}