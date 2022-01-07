package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("rhdto")
public class RoomHostingDto {
	private String no;
	private String change_date;
	private boolean hosting;
}