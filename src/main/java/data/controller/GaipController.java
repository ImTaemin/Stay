package data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GaipController {
	
	@GetMapping("/gaipform")
	public String gaipForm() {
		return "/member/gaipForm";
	}
	
}