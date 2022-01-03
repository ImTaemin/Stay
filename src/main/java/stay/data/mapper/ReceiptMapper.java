package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.ReceiptDto;
import stay.data.dto.ResultMapDto;

@Mapper
public interface ReceiptMapper {
	public void receInsert(ReceiptDto dto);
	public ReceiptDto selectOneReceipt(String no);
	public ResultMapDto getDetailReceipt(String no);
}