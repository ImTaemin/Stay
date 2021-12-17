package data.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("rdto")
public class RoomDto {
	private String no;
	private String host_id;
	private String type;
	private String addr1;
	private String addr2;
	private String addr3;
	private String addr4;
	private String max_per;
	private String content;
	private String name;
	private String photos;
	private int price;
	private boolean hosting;
	private String latitude;
	private String longitude;
	// 업로드
	private MultipartFile upload;
}