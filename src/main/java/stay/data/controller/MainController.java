package stay.data.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.MemberDto;
import stay.data.dto.RoomDto;
import stay.data.service.GuestCommentService;
import stay.data.service.MemberService;
import stay.data.service.RoomService;

@Controller
public class MainController {
	
	@Autowired
	RoomService roomService;
	@Autowired
	MemberService memberService;
	@Autowired
	GuestCommentService gcommentService;
	

   @GetMapping("/")
   public ModelAndView home(HttpSession session) {
		
	   ModelAndView mview = new ModelAndView();
		
	   List<RoomDto> bestList = roomService.getBestRoom();
		
		for(RoomDto dto : bestList) {
			String photos[] = dto.getPhotos().split(",");
			
			dto.setPhotos(photos[0]);
		}
		
		mview.addObject("bestList", bestList);
		
		String myid = (String)session.getAttribute("myid");
		String loginok = (String)session.getAttribute("loginok");
		
		if(loginok != null) {
			MemberDto memberDto = memberService.getMember(myid);
			
			String photo = memberDto.getPhoto();
			
			session.setAttribute("photo", photo);
		}
		
		mview.setViewName("/layout/guestMain");
		
		return mview;
   }
   
   @GetMapping("/host/main")
   public String hostmain() {
      return "/layout/hostMain";
   }
   
   @GetMapping("/changeMode")
   public String modeChange(HttpSession session) {
	   if(((String)session.getAttribute("mode")).equals("guest")) {
		   session.setAttribute("mode", "host");
//		   session.removeAttribute("mode");
//		   session.setAttribute("mode", "host");
		   return "redirect:/host/main";
	   } else {
		   session.setAttribute("mode", "guest");
//		   session.removeAttribute("mode");
//		   session.setAttribute("mode", "host");
		   return "redirect:/";
	   }
	   
   }
   
  
}