package stay.data.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import stay.data.service.GuestCommentService;
import stay.data.service.RoomService;

@Controller
public class CostController {
	@Autowired
	CostService costService;

	@Autowired
	RoomService roomService;
	
	@Autowired
	GuestCommentService gcommentService;

	// 카드
	@GetMapping("/card/insertform")
	public String cardInsertForm() {
		return "/cost/cardInsertForm";
	}

	@PostMapping("/card/insert")
	public String cardInsert(@ModelAttribute PayCardDto cardDto, HttpSession session) {
		String myid = (String) session.getAttribute("myid");

		String cardNum = cardDto.getNum1() + "-" + cardDto.getNum2() + "-" + cardDto.getNum3() + "-"
				+ cardDto.getNum4();

		cardDto.setId(myid);
		cardDto.setNum(cardNum);

		costService.insertCard(cardDto);

		return "redirect:/";
	}

	// 계좌
}