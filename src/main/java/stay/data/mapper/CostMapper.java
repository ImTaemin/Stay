package stay.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.PayCardDto;
import stay.data.dto.ReceiveAccountDto;

@Mapper
public interface CostMapper {
	// card
	public void insertCard(PayCardDto cardDto);
	public List<PayCardDto> getAllCard(String id);
	
	// account
	public void insertAccount(ReceiveAccountDto receiveAccountDto);
	public List<ReceiveAccountDto> getAllAccount(String id);
}