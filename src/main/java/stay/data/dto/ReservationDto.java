package stay.data.dto;


import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("redto")
public class ReservationDto {
	private String no;
	private String guest_id;
	private String host_id;
	private String start_date;
	private String end_date;
	private String price;
	private String pay_method;
	private String card_num;
	private String account_num;
	private Timestamp pay_date;
	private String room_no;
}