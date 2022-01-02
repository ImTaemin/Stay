package stay.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import stay.data.dto.CanReservationDto;
import stay.data.service.CanReservationService;

@Controller
@RequestMapping("/reser")
public class CanReservationController {
	@Autowired
	CanReservationService canReserService;
	
	@PostMapping("/delete")
	public void reserDelete(
			@ModelAttribute CanReservationDto crDto, @RequestParam String no, @RequestParam String price) {
		crDto.setNo(no);
		crDto.setRefund(price);
		
		canReserService.insertCanReser(crDto);
	}
}