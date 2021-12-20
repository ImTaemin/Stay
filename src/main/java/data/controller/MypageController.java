package data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
	
	@GetMapping("/mypageform")
	public String mypageForm() {
		return "/member/mypageForm";
	}
	
}