package stay.data.dto;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChatDto {

	private int id;
	private String msg;
	private String sender;
	private String receiver;
	private String photo;
	
	@JsonFormat(pattern = "yy/MM/dd HH:mm")
	private Timestamp msg_time;
}
