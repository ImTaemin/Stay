package stay.data.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("mdto")
public class MemberDto {
	private String id;
	private String pass;
	private String name;
	private String birth;
	private String hp;
	private String addr_load;
	private String addr_detail;
	private String photo;
	private MultipartFile upload;
	private int likes;
	private String e_mail;
}
