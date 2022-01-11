package stay.data.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import stay.data.dto.GuestCommentDto;
import stay.data.dto.HostCommentDto;
import stay.data.service.GuestCommentService;
import stay.data.service.HostCommentService;

@Controller
@RequestMapping("/hcomment")
public class HostCommentController {
	@Autowired
	HostCommentService hCommentService;
	
	@Autowired
	GuestCommentService gCommentService;
	
	@PostMapping("/insert")
	@ResponseBody
	public HostCommentDto commentInsert(@RequestParam String no, @RequestParam String content, HttpSession session) {
		
		String myid = (String)session.getAttribute("myid");
		
		GuestCommentDto gCommentDto=gCommentService.getOneComment(no);
		
		HostCommentDto hCommentDto = new HostCommentDto();
		hCommentDto.setNo(gCommentDto.getNo());
		hCommentDto.setGuest_id(gCommentDto.getGuest_id());
		hCommentDto.setHost_id(myid);
		hCommentDto.setContent(content);
		
		hCommentService.insertHostComment(hCommentDto);
		return hCommentService.getHostComment(gCommentDto.getNo(), myid);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public HostCommentDto commentUpdate(@RequestParam String no, @RequestParam String content, HttpSession session) {
		
		GuestCommentDto gCommentDto=gCommentService.getOneComment(no);

		HostCommentDto hCommentDto = new HostCommentDto();
		hCommentDto.setNo(gCommentDto.getNo());
		hCommentDto.setContent(content);
		
		hCommentService.updateHostComment(hCommentDto);
		
		hCommentDto = hCommentService.getHostComment(gCommentDto.getNo(), content);

		return hCommentDto;
	}
	
	@PostMapping("/delete")
	public void commentDelete(@RequestParam String no) {
		hCommentService.deleteHostComment(no);
	}
}