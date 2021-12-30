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

import stay.data.dto.MemberDto;
import stay.data.dto.RoomDto;
import stay.data.dto.RoomReserGcomDto;
import stay.data.dto.WishListDto;
import stay.data.service.GuestCommentService;
import stay.data.service.MemberService;
import stay.data.service.RoomService;
import stay.data.service.WishListService;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomService roomService;
	
	@Autowired
	GuestCommentService gcommentService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	WishListService wishService;
	
	@GetMapping("/main")
	public ModelAndView roomMain(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		String loginok = (String)session.getAttribute("loginok");
		
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
		List<RoomReserGcomDto> roomList = roomService.getRoomRate(start, perPage);
 		
 		for(RoomReserGcomDto dto : roomList) {
 			// 이미지 분리
 			String photos[] = dto.getRoomDto().getPhotos().split(",");
 			
 			RoomDto roomDto = roomService.getRoom(dto.getRoomDto().getNo());
 			roomDto.setPhotos(photos[0]);
 			
 			dto.setRoomDto(roomDto);
 		}
 		
 		mview.addObject("totalCount", totalCount);
		mview.addObject("roomList", roomList);
		mview.addObject("totalPage", totalPage);
		mview.addObject("startPage", startPage);
		mview.addObject("endPage", endPage);
		mview.addObject("currentPage", currentPage);
 		
 		// 위시리스트
 		if(loginok != null) {
 			List<WishListDto> wishList = wishService.onlyWishList(myid);
 			
 			mview.addObject("wishList", wishList);
 		}
		
		mview.setViewName("/room/roomMain");
		
		return mview;
	}
	
	 @GetMapping("/insertform")
	public String roomInsertForm() {
		return "/room/roomInsertForm";
	}
	
	@PostMapping("/insert")
	public String roomInsert(@ModelAttribute RoomDto roomDto, @RequestParam ArrayList<MultipartFile> upload, HttpSession session) {
		// 업로드할 폴더 지정
		String path = session.getServletContext().getRealPath("/photo/roomPhoto");
		System.out.println(path);
		
		String photo = "";
		
		if(upload.get(0).getOriginalFilename().equals("")) {
			photo  = "no";
		} else {
			for(MultipartFile f : upload) {
				String fName = f.getOriginalFilename();
				photo += fName + ",";
				
				// 업로드
				try {
					f.transferTo(new File(path + "\\" + fName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			photo = photo.substring(0, photo.length() - 1);
		}
		
		String myid = (String)session.getAttribute("myid");
		
		roomDto.setHost_id(myid);
		roomDto.setPhotos(photo);
		
		roomService.insertRoom(roomDto);
		
		int no = roomService.getRoomMaxNo();
		
		return "redirect:content?no=" + no;
	}
	
	@GetMapping("/content")
	public ModelAndView content(
			@RequestParam String no,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		String loginok = (String)session.getAttribute("loginok");
		
		ModelAndView mview = new ModelAndView();
		
		RoomDto roomDto = roomService.getRoom(no);
		
		String photoList[] = roomDto.getPhotos().split(",");
		
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
		
		// 호스트 정보
		String hostId = roomDto.getHost_id();
		
		MemberDto memDto = memberService.getMember(hostId);
		
		// 엔터키 입력
		roomDto.getContent().replaceAll("<br>", "\r\n");
		
		// 위시리스트
 		if(loginok != null) {
 			List<WishListDto> wishList = wishService.onlyWishList(myid);
 			
 			mview.addObject("wishList", wishList);
 		}
		
 		mview.addObject("myid", myid);
 		mview.addObject("loginok", loginok);
		mview.addObject("roomDto", roomDto);
		mview.addObject("memDto", memDto);
		mview.addObject("photoList", photoList);
		mview.addObject("currentPage", currentPage);
		mview.addObject("avgRating", avgRating);
		mview.addObject("totalComment", totalComment);
		
		mview.setViewName("/room/roomDetail");
		
		return mview;
	}
	
	@GetMapping("/updateform")
	public ModelAndView roomUpdateForm(String no) {
		ModelAndView mview = new ModelAndView();
		
		RoomDto roomDto = roomService.getRoom("7");
		
		mview.addObject("roomDto", roomDto);
		
		mview.setViewName("/room/roomUpdateForm");
		
		return mview;
	}
	
	@PostMapping("/update")
	public String roomUpdate(
			@ModelAttribute RoomDto roomDto,
			@RequestParam String no,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session) {
		// 업로드할 폴더 지정
		String path = session.getServletContext().getRealPath("/photo/roomPhoto");
		System.out.println(path);
		
		String photo = "";
		
		if(upload.get(0).getOriginalFilename().equals("")) {
			photo  = null;
		} else {
			String photos = roomService.getRoom(no).getPhotos();
			String photoArray[] = photos.split(",");
			
			for(int i = 0; i < photoArray.length; i++) {
				File file = new File(path + "/" + photoArray[i]);
				file.delete();
			}
			
			for(MultipartFile f : upload) {
				String fName = f.getOriginalFilename();
				photo += fName + ",";
				
				// 업로드
				try {
					f.transferTo(new File(path + "\\" + fName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			photo = photo.substring(0, photo.length() - 1);
		}
		
		roomDto.setPhotos(photo);
		
		roomService.updateRoom(roomDto);
		
		return "redirect:main";
	}
}