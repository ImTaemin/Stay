package stay.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import stay.data.dto.CanReceiptDto;
import stay.data.dto.CanReservationDto;
import stay.data.dto.ReceiptDto;
import stay.data.service.CanReceiptService;
import stay.data.service.CanReservationService;
import stay.data.service.ReceiptService;

@Controller
@RequestMapping("/reser")
public class CanReservationController {
	@Autowired
	CanReservationService canReserService;
	
	@Autowired
	ReceiptService receiptService;
	
	@Autowired
	CanReceiptService canreceiptService;
	
	@GetMapping("/delete")
	public void reserDelete(
			@ModelAttribute CanReservationDto crDto, @ModelAttribute CanReceiptDto canreDto,
			@RequestParam String no, @RequestParam String price) {
		// 취소 영수증 추가
		ReceiptDto receipt = receiptService.selectOneReceipt(no);
		
		canreDto.setId(receipt.getId());
		canreDto.setNo(receipt.getNo());
		
		canreceiptService.canRecInsert(canreDto);
		
		// 취소 예약 추가
		crDto.setNo(no);
		crDto.setRefund(price);
		
		canReserService.insertCanReser(crDto);
	}
	
	@PostMapping("/refund")
	public void reserRefund(
			@ModelAttribute CanReservationDto crDto, @RequestParam String no) {
		crDto.setNo(no);
		canReserService.updateRefReser(crDto);
	}
}