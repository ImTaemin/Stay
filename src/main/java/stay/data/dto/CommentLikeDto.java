package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cldto")
public class CommentLikeDto {
	private String no;
	private String guest_id;
	private String id;
}