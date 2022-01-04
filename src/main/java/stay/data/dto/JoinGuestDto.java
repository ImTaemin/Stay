package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("jgdto")
public class JoinGuestDto {
	private String no;
	private String id;
	
	private int count;
}