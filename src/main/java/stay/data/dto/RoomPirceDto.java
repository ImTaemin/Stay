package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("rpdto")
public class RoomPirceDto {
	private String no;
	private String change_date;
	private int price;
}