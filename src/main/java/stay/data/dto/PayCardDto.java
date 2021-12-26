package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("pcdto")
public class PayCardDto {
	private String num;
	private String id;
	private String name;
	private String end_date;
	private String cvc;
	private String pass;
	
	// card 번호
	private String num1;
	private String num2;
	private String num3;
	private String num4;
	
	// 카드 뒷번호
	private String last_num;
}