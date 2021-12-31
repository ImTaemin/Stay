package stay.data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("rmdto")
public class ReportMemberDto {

	private int no;
	private String black_id;
	private String report_id;
	private String reason;
}
