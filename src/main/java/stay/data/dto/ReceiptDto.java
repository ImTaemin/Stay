package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("recedto")
public class ReceiptDto {
	private String id;
	private String no;
}