package stay.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChatDto {

	private int id;
	private String msg;
	private String sender;
	private String receiver;
	
	@JsonFormat(pattern = "yy/MM/dd HH:mm")
	private String msgTime;
	

}