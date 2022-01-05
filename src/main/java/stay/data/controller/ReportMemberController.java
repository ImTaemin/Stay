package stay.data.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.CommentLikeDto;
import stay.data.dto.GuestCommentDto;
import stay.data.dto.MemberDto;
import stay.data.dto.ReportMemberDto;
import stay.data.dto.ResultMapDto;
import stay.data.dto.RoomDto;
import stay.data.dto.WishListDto;
import stay.data.service.CommentLikeService;
import stay.data.service.GuestCommentService;
import stay.data.service.MemberService;
import stay.data.service.RoomService;
import stay.data.service.WishListService;

@Controller
public class ReportMemberController {
	
	
	@Autowired
	MemberService memberService;
	
	
	@GetMapping("/report")
	public ModelAndView report(HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		List<ReportMemberDto> reportList = memberService.getReport();
		
		mview.addObject("reportList", reportList); 
		
		mview.setViewName("/member/memberlist");
		
		return mview;
	}
}