package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("gcdto")
public class GuestCommentDto {
	private String no;
	private String guest_id;
	private String content;
	private double rating;
	private int likes;
}