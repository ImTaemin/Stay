package stay.data.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.PayCardDto;
import stay.data.dto.RoomDto;
import stay.data.service.CostService;
import stay.data.service.RoomService;

@Controller
public class CostController {
	@Autowired
	CostService costService;
	
	@Autowired
	RoomService roomService;
	
	// 카드
	@GetMapping("/card/insertform")
	public String cardInsertForm() {
		return "/cost/cardInsertForm";
	}
	
	@PostMapping("/card/insert")
	public String cardInsert(@ModelAttribute PayCardDto cardDto, HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		String cardNum = cardDto.getNum1() + "-" + cardDto.getNum2() + "-" + cardDto.getNum3() + "-" + cardDto.getNum4();
		
		cardDto.setId(myid);
		cardDto.setNum(cardNum);
		
		costService.insertCard(cardDto);
		
		return "redirect:/";
	}
	
	// 계좌
	
	// 결제
	@PostMapping("/pay/paymentform")
	public ModelAndView paymentForm(
			@RequestParam String roomNo, @RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String betweenDay, @RequestParam String roomPrice, @RequestParam String allPrice,
			HttpSession session
			) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		RoomDto roomDto = roomService.getRoom(roomNo);
		
		// 날짜 분리
		String start[] = startDate.split("-");
		String end[] = endDate.split("-");
		
		// 카드 리스트
		List<PayCardDto> cardList = costService.getAllCard(myid);
		
		for(PayCardDto p : cardList) {
			String num = p.getNum();
			String numArray[] = num.split("-");
			
			p.setNum(numArray[3]);
		}
		
		mview.addObject("roomDto", roomDto);
		mview.addObject("start", start);
		mview.addObject("end", end);
		mview.addObject("betweenDay", betweenDay);
		mview.addObject("roomPrice", roomPrice);
		mview.addObject("allPrice", allPrice);
		mview.addObject("cardList", cardList);
		
		mview.setViewName("/cost/paymentForm");
		
		return mview;
	}
}