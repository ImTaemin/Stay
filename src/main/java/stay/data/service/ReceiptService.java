package stay.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.ReceiptDto;
import stay.data.dto.ResultMapDto;
import stay.data.mapper.ReceiptMapper;

@Service
public class ReceiptService {
	@Autowired
	ReceiptMapper mapper;
	
	public void receInsert(ReceiptDto dto) {
		mapper.receInsert(dto);
	}
	
	public ReceiptDto selectOneReceipt(String no) {
		return mapper.selectOneReceipt(no);
	}
	
	public ResultMapDto getDetailReceipt(String no) {
		return mapper.getDetailReceipt(no);
	}
}