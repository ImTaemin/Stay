package stay.data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("rmdto")
public class ReportMemberDto {

	private String no;
	private String black_id;
	private String report_id;
	private String reason;
	private Timestamp report_date;
	private Timestamp approve_date;
	private String approve_check;
}
