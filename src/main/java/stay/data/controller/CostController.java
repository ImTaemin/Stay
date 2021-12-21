package stay.data.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import stay.data.dto.PayCardDto;
import stay.data.service.CostService;

@Controller
public class CostController {
	@Autowired
	CostService costService;
	
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
}