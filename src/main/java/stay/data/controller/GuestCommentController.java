package stay.data.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.GuestCommentDto;
import stay.data.service.GuestCommentService;
import stay.data.service.ReservationService;

@Controller
@RequestMapping("/comment")
public class GuestCommentController {
	@Autowired
	GuestCommentService gCommentService;
	
	@Autowired
	ReservationService reservationService;
	
	@PostMapping("/insert")
	public ModelAndView commentInsert(
			@ModelAttribute GuestCommentDto gCommentDto,
			@RequestParam String reserNo, @RequestParam String rate,
			HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		double rating = Double.parseDouble(rate);
		
		// 숙소 번호 구하기
		String roomNo = reservationService.getRoomNo(reserNo);
		
		gCommentDto.setNo(reserNo);
		gCommentDto.setGuest_id(myid);
		gCommentDto.setRating(rating);
		
		gCommentService.insertGuestComment(gCommentDto);
		
		mview.setViewName("redirect:/room/content?no=" + roomNo);
		
		return mview;
	}
	
	@PostMapping("/update")
	public ModelAndView commentUpdate(
			@ModelAttribute GuestCommentDto gCommentDto,
			@RequestParam String reserNo, @RequestParam String content, @RequestParam String rate) {
		ModelAndView mview = new ModelAndView();
		
		double rating = Double.parseDouble(rate);
		
		gCommentDto.setNo(reserNo);
		gCommentDto.setContent(content);
		gCommentDto.setRating(rating);
		
		gCommentService.updateGuestComment(gCommentDto);
		
		mview.setViewName("redirect:/reser/reservationlist");
		
		return mview;
	}
}