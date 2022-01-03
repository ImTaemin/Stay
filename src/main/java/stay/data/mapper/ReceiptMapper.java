package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.ReceiptDto;

@Mapper
public interface ReceiptMapper {
	public void receInsert(ReceiptDto dto);
	public ReceiptDto selectOneReceipt(String no);
}