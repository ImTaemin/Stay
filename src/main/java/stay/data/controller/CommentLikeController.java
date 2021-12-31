package stay.data.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import stay.data.dto.CommentLikeDto;
import stay.data.service.CommentLikeService;

@Controller
@RequestMapping("/like")
public class CommentLikeController {
	@Autowired
	CommentLikeService likeService;
	
	@PostMapping("/insert")
	public void likeInsert(
			@ModelAttribute CommentLikeDto likeDto,
			@RequestParam String reserNo, @RequestParam String guestId, HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		likeDto.setNo(reserNo);
		likeDto.setGuest_id(guestId);
		likeDto.setId(myid);
		
		likeService.insertLike(likeDto);
	}
	
	@PostMapping("/delete")
	public void likeDelete(@RequestParam String reserNo, @RequestParam String guestId, HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		likeService.deleteLike(reserNo, guestId, myid);
	}
}