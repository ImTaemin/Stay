package stay.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.RoomDto;
import stay.data.service.GuestCommentService;
import stay.data.service.RoomService;

@Controller
public class MainController {
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	GuestCommentService gcommentService;
	
	
   @GetMapping("/")
   public ModelAndView home(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
		ModelAndView mview = new ModelAndView();
		
		// 총 숙소 개수
		int totalCount = roomService.getRoomCount();
		
		// 총 페이지 수
		int totalPage;
		// 각 블럭의 시작 페이지
		int startPage;
		// 각 블럭의 끝 페이지
		int endPage;
		// 각 페이지의 시작 번호
		int start;

		// 한 페이지에 보여질 글의 개수
		int perPage = 5;
		// 한 페이지에 보여지는 페이지 개수
		int perBlock = 5;

		// 총 페이지 개수 구하기
		totalPage = totalCount / perPage + (totalCount % perPage == 0 ? 0 : 1);

		// 각 블럭의 시작 페이지
		startPage = (currentPage - 1) / perBlock * perBlock + 1;
		endPage = startPage + perBlock - 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 각 페이지에서 불러올 시작 번호
		start = (currentPage - 1) * perPage;

		// 각 페이지에서 필요한 게시글 가져오기
		List<RoomDto> roomList = roomService.getRooms(start, perPage);
		
		for(RoomDto dto : roomList) {
			String photos[] = dto.getPhotos().split(",");
			
			dto.setPhotos(photos[0]);
		}
		
		mview.addObject("totalCount", totalCount);
		mview.addObject("roomList", roomList);
		mview.addObject("totalPage", totalPage);
		mview.addObject("startPage", startPage);
		mview.addObject("endPage", endPage);
		mview.addObject("currentPage", currentPage);
		
		// 방 평균 별점
		Float avgRating = gcommentService.getRatingAvg();
		
		if(avgRating == null) {
			avgRating = (float) 0;
		}
		
		// 방 댓글 개수
		Integer totalComment = gcommentService.totalComment();
		
		if(totalComment == null) {
			totalComment = 0;
		}
		
		mview.addObject("avgRating", avgRating);
		mview.addObject("totalComment", totalComment);
		
		mview.setViewName("/layout/guestMain");
		
		return mview;
   }
   
   @GetMapping("/host/main")
   public String hostmain() {

      return "/layout/hostMain";
   }
}