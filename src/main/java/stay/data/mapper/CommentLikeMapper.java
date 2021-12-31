package stay.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.CommentLikeDto;

@Mapper
public interface CommentLikeMapper {
	public int countLike(HashMap<String, String> map);
	public void insertLike(CommentLikeDto likeDto);
	public void deleteLike(HashMap<String, String> map);
	public List<CommentLikeDto> getLike(String id);
}