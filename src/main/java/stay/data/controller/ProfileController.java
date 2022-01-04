package stay.data.controller;

import java.util.HashMap;
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
import stay.data.dto.ReportMemberDto;
import stay.data.dto.ResultMapDto;
import stay.data.mapper.MemberMapper;
import stay.data.service.CommentLikeService;
import stay.data.service.GuestCommentService;
import stay.data.service.MemberService;
import stay.data.service.RoomService;
import stay.data.service.WishListService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	MemberMapper mapper;

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

	@GetMapping("/profileform")
	public ModelAndView profile1(@RequestParam(required = false) String id, HttpSession session) {
		ModelAndView mview = new ModelAndView();
		String myid = (String) session.getAttribute("myid");

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
		List<CommentLikeDto> likeList = likeService.getLike(myid);

		mview.addObject("commentList", commentList);
		mview.addObject("commentNum", commentNum);
		mview.addObject("likeList", likeList);

		mview.setViewName("/member/profileForm");

		return mview;
	}

	@PostMapping("/singo")
	public void singoInsert(@ModelAttribute ReportMemberDto rmDto, @RequestParam String black_id,
			@RequestParam String reason, HttpSession session) {
		String myid = (String) session.getAttribute("myid");

		System.out.println(black_id + " " + reason + " " + myid);

		rmDto.setBlack_id(black_id);
		rmDto.setReport_id(myid);
		rmDto.setReason(reason);

		memberService.insertSingoMem(rmDto);
	}
//
//	@GetMapping("/updatelikes")
//	@ResponseBody
//	public HashMap<String, Integer> updatelikes(@RequestParam String id) {
//		HashMap<String, Integer> map = new HashMap<String, Integer>();
//		mapper.updatelikes(id);
//		int likes = mapper.getMember(id).getLikes();
//		map.put("likes", likes);
//
//		return map;
//	}
//	
//	@GetMapping("/deletelikes")
//	public @ResponseBody void deleteLikes(@RequestParam String id) {
//		memberService.deletelikes(id);
//	}
}