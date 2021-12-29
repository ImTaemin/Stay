package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("radto")
public class ReceiveAccountDto {

	private String account;
	private String id;
	private String bank;
	
	//계좌번호
	private String account1;
	private String account2;
	private String account3;
}
