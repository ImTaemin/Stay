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
import stay.data.dto.WishListDto;
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
	public ModelAndView profile1(HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		MemberDto memberDto = memberService.getMember(myid);
		/* List<ReportMemberDto> rmList = memberService.getSingoMem(myid); */
		
		memberDto.setId(myid);
		
		mview.addObject("memberDto", memberDto);
		/* mview.addObject("rmList", rmList); */
		
		mview.setViewName("/member/profileForm");
		
		return mview;
	}
	
	@PostMapping("/singo")
	public void singoInsert(
			@ModelAttribute ReportMemberDto rmDto,
			@RequestParam String black_id,
			@RequestParam String reason,
			HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		System.out.println(black_id + " " + reason + " " +myid );
		
		rmDto.setBlack_id(black_id);
		rmDto.setReport_id(myid);
		rmDto.setReason(reason);
		
		memberService.insertSingoMem(rmDto);
	}
	
	
	@GetMapping("/updatelikes")
	@ResponseBody
	public HashMap<String, Integer> updatelikes(
			@RequestParam String id
			){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		mapper.updatelikes(id);
		int likes = mapper.getMember(id).getLikes();//여기가 널이라는거자
		map.put("likes", likes);
		
		return map;
		
	}
	
	@GetMapping("/content")
	public ModelAndView content(
			@RequestParam String no,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		String loginok = (String)session.getAttribute("loginok");
		
		ModelAndView mview = new ModelAndView();
		
		ResultMapDto dto = roomService.getOneRoom(no);
		
		String photoList[] = dto.getRoomDto().getPhotos().split(",");
		
		// 호스트 정보
		String hostId = dto.getRoomDto().getHost_id();
		
		MemberDto memDto = memberService.getMember(hostId);
		
		// 위시리스트
 		if(loginok != null) {
 			List<WishListDto> wishList = wishService.getWishList(myid);
 			
 			mview.addObject("wishList", wishList);
 		}
		
 		// 게스트 댓글
 		List<ResultMapDto> commentList = gcommentService.getRoomComment(no);
 		
 		for(ResultMapDto c : commentList) {
 			// 회원 정보
 			String guestId = c.getGcoDto().getGuest_id();
 			
 			MemberDto gMemDto = memberService.getMember(guestId);
 			
 			c.setMemDto(gMemDto);
 			
 			// 좋아요 개수
 			String reserNo = c.getGcoDto().getNo();
 			
 			int likes = likeService.countLike(reserNo, guestId);
 			
 			GuestCommentDto gCoDto = gcommentService.getOneComment(reserNo, guestId);
 			
 			gCoDto.setCountLike(likes);
 			
 			c.setGcoDto(gCoDto);
 		}
 		
 		// 좋아요한 댓글 리스트
 		List<CommentLikeDto> likeList = likeService.getLike(myid);
 		
 		mview.addObject("currentPage", currentPage);
 		mview.addObject("myid", myid);
 		mview.addObject("loginok", loginok);
		mview.addObject("dto", dto);
		mview.addObject("photoList", photoList);
		mview.addObject("memDto", memDto);
		mview.addObject("commentList", commentList);
		mview.addObject("likeList", likeList);
		
		mview.setViewName("/room/roomDetail");
		
		return mview;
	}
	
}