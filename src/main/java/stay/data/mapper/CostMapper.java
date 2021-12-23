package stay.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.PayCardDto;

@Mapper
public interface CostMapper {
	// card
	public void insertCard(PayCardDto cardDto);
	public List<PayCardDto> getAllCard(String id);
	
	// account
}