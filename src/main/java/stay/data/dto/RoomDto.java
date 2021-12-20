package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("rdto")
public class RoomDto {
	private String no;
	private String host_id;
	private String type;
	private String addr_load;
	private String addr_detail;
	private String max_per;
	private String content;
	private String name;
	private String photos;
	private int price;
	private boolean hosting;
}