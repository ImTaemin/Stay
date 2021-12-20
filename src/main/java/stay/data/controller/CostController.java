package stay.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CostController {
	@GetMapping("/card/insertform")
	public String cardInsertForm() {
		return "/cost/cardInsertForm";
	}
}