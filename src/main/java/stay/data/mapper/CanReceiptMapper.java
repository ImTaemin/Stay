package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.CanReceiptDto;

@Mapper
public interface CanReceiptMapper {
	public void canRecInsert(CanReceiptDto dto);
}