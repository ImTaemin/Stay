package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("canredto")
public class CanReceiptDto {
	private String id;
	private String no;
}