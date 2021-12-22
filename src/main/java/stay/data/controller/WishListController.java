package stay.data.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import stay.data.dto.WishListDto;
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
	
	@PostMapping("/insert")
	public void wishInsert(
			@ModelAttribute WishListDto wishDto,
			@RequestParam String roomId,
			HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		wishDto.setGuest_id(myid);
		wishDto.setRoom_no(roomId);
		
		wishService.insertWishList(wishDto);
	}
	
	@PostMapping("/delete")
	public void wishDelete(@RequestParam String roomId, HttpSession session) {
		String guest_id = (String)session.getAttribute("myid");
		
		wishService.deleteWishList(roomId, guest_id);
	}
}