package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("hcdto")
public class HostCommentDto {
	private String no;
	private String guest_id;
	private String host_id;
	private String content;
	
	private int count;
}