package stay.data.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentLikeMapper {
	public int countLike(HashMap<String, String> map);
}