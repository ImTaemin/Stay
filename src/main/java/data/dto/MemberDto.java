package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("mdto")
public class MemberDto {
	private String id;
	private String pass;
	private String name;
	private String birth;
	private String hp;
	private String addr;
	private String addr1;
	private String addr2;
	private String photo;
	private int likes;
	private String e_mail;
}
