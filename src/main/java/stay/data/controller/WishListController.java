package stay.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import stay.data.service.WishListService;

@Controller
@RequestMapping("/wish")
public class WishListController {
	@Autowired
	WishListService wishService;
	
	@GetMapping("/list")
	public String wishList() {
		return "/wishlist/wishList";
	}
}