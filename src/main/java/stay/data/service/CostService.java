package stay.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.PayCardDto;
import stay.data.mapper.CostMapper;

@Service
public class CostService {
	@Autowired
	CostMapper mapper;
	
	public void insertCard(PayCardDto cardDto) {
		mapper.insertCard(cardDto);
	}
}