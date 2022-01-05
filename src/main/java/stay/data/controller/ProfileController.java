package stay.data.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.CommentLikeDto;
import stay.data.dto.GuestCommentDto;
import stay.data.dto.MemberDto;
import stay.data.dto.MemberLikeDto;
import stay.data.dto.ReportMemberDto;
import stay.data.dto.ResultMapDto;
import stay.data.service.CommentLikeService;
import stay.data.service.GuestCommentService;
import stay.data.service.MemberLikeService;
import stay.data.service.MemberService;
import stay.data.service.RoomService;
import stay.data.service.WishListService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	MemberService memberService;

	@Autowired
	RoomService roomService;

	@Autowired
	GuestCommentService gcommentService;

	@Autowired
	WishListService wishService;

	@Autowired
	CommentLikeService likeService;
	
	@Autowired
	MemberLikeService memLikeService;

	@GetMapping("/profileform")
	public ModelAndView profile1(@RequestParam(required = false) String id, HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		// 회원 정보
		MemberDto memberDto = memberService.getMember(id);

		mview.addObject("memberDto", memberDto);

		// 후기 정보
		List<ResultMapDto> commentList = gcommentService.selectOneGuest(id);

		for (ResultMapDto c : commentList) {
			// 회원 정보
			c.setMemDto(memberDto);

			// 좋아요 개수
			String reserNo = c.getGcoDto().getNo();

			int likes = likeService.countLike(reserNo, id);

			GuestCommentDto gcoDto = gcommentService.getOneComment(reserNo, id);
			gcoDto.setCountLike(likes);

			c.setGcoDto(gcoDto);
		}

		// 후기 개수
		int commentNum = gcommentService.countGuestComment(id);

		// 좋아요한 댓글 리스트
		List<CommentLikeDto> likeList = likeService.getLike(id);
		
		// 멤버 좋아요
		int likeFlag = memLikeService.getSameLike(id, myid);
		int memberLikeNum = memLikeService.getGuestLikeNum(id);

		mview.addObject("commentList", commentList);
		mview.addObject("commentNum", commentNum);
		mview.addObject("likeList", likeList);
		mview.addObject("likeFlag", likeFlag);
		mview.addObject("memberLikeNum", memberLikeNum);

		mview.setViewName("/member/profileForm");

		return mview;
	}
	
	@PostMapping("/report")
	public @ResponseBody boolean insertReport(@RequestParam String blackId, @RequestParam String reason, HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		// 신고 여부
		int checkSingo = memberService.checkSingo(blackId, myid);
		
		System.out.println(checkSingo);
		
		// 신고 insert
		ReportMemberDto rmDto = new ReportMemberDto();
		rmDto.setBlack_id(blackId);
		rmDto.setReport_id(myid);
		rmDto.setReason(reason);
		
		if(checkSingo == 1) {
			return false;
		} else {
			memberService.insertSingoMem(rmDto);
			
			return true;
		}
	}
	
	@PostMapping("/insertlike")
	public void likeInsert(
			@ModelAttribute MemberLikeDto memLikeDto, @RequestParam String guestId, HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		memLikeDto.setGuest_id(guestId);
		memLikeDto.setId(myid);
		
		memLikeService.insertLike(memLikeDto);
	}
	
	@PostMapping("/deletelike")
	public void deleteLike(@RequestParam String guestId, HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		memLikeService.deleteLike(guestId, myid);
	}
}