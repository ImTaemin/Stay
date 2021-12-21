package stay.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.PayCardDto;

@Mapper
public interface CostMapper {
	// card
	public void insertCard(PayCardDto cardDto);
	
	// account
}