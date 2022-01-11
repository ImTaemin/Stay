package stay.data.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import stay.data.dto.HostCommentDto;
import stay.data.service.HostCommentService;

@Controller
@RequestMapping("/recomment")
public class HostCommentController {
	@Autowired
	HostCommentService hcommentService;
	
	@PostMapping("/search")
	public @ResponseBody HostCommentDto searchHostComment(@RequestParam String reserNo) {
		HostCommentDto hcommentDto = hcommentService.getOneHostComment(reserNo);
		
		return hcommentDto;
	}
	
	@PostMapping("/insert")
	public @ResponseBody void insertHostComment(
			@ModelAttribute HostCommentDto hcoDto,
			@RequestParam String reserNo, @RequestParam String guestId, @RequestParam String content,
			HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		hcoDto.setNo(reserNo);
		hcoDto.setGuest_id(guestId);
		hcoDto.setHost_id(myid);
		hcoDto.setContent(content);
		
		hcommentService.insertHostComment(hcoDto);
	}
	
	@PostMapping("/update")
	public @ResponseBody void updateHostComment(@RequestParam String reserNo, @RequestParam String content) {
		HostCommentDto hcommentDto = hcommentService.getOneHostComment(reserNo);
		
		hcommentDto.setContent(content);
		
		hcommentService.updateHostComment(hcommentDto);
	}
	
	@PostMapping("/delete")
	public @ResponseBody void deleteHostComment(@RequestParam String reserNo, HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		hcommentService.deleteHostComment(reserNo, myid);
	}
}