package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("wldto")
public class WishListDto {
	private String room_no;
	private String guest_id;
}