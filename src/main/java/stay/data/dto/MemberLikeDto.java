package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("mldto")
public class MemberLikeDto {
	private String guest_id;
	private String id;
}