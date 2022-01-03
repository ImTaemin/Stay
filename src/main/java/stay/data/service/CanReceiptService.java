package stay.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.CanReceiptDto;
import stay.data.mapper.CanReceiptMapper;

@Service
public class CanReceiptService {
	@Autowired
	CanReceiptMapper mapper;
	
	public void canRecInsert(CanReceiptDto dto) {
		mapper.canRecInsert(dto);
	}
}