package stay.data.controller;

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
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.ResultMapDto;
import stay.data.dto.RoomDto;
import stay.data.dto.WishListDto;
import stay.data.service.GuestCommentService;
import stay.data.service.RoomService;
import stay.data.service.WishListService;

@Controller
@RequestMapping("/wish")
public class WishListController {
	@Autowired
	WishListService wishService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	GuestCommentService gcommentService;
	
	@GetMapping("/list")
	public ModelAndView wishList(HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		List<WishListDto> wishList = wishService.getWishList(myid);
		List<ResultMapDto> roomList = new ArrayList<ResultMapDto>();
		
		for(WishListDto wdto : wishList) {
			// 숙소 정보 입력
			String roomNo = wdto.getRoom_no();
			
			ResultMapDto allDto = roomService.getOneRoom(roomNo);
			
			// 이미지 분리
			String photos = allDto.getRoomDto().getPhotos();
			String photo[] = photos.split(",");
			
			RoomDto roomDto = roomService.getRoom(roomNo);
			
			roomDto.setPhotos(photo[0]);
			allDto.setRoomDto(roomDto);
			
			roomList.add(allDto);
		}
		
		mview.addObject("wishList", wishList);
		mview.addObject("roomList", roomList);
		
		mview.setViewName("/wishlist/wishList");
		
		return mview;
	}
	
	@PostMapping("/insert")
	public void wishInsert(
			@ModelAttribute WishListDto wishDto,
			@RequestParam String roomId,
			HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		wishDto.setGuest_id(myid);
		wishDto.setRoom_no(roomId);
		
		wishService.insertWishList(wishDto);
	}
	
	@PostMapping("/delete")
	public void wishDelete(@RequestParam String roomId, HttpSession session) {
		String guest_id = (String)session.getAttribute("myid");
		
		wishService.deleteWishList(roomId, guest_id);
	}
}