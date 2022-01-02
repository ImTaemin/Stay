package stay.data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("crdto")
public class CanReservationDto {
	private String no;
	private Timestamp can_date;
	private String refund;
	private String refund_check;
}