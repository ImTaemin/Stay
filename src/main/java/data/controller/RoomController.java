package data.controller;

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

import data.dto.RoomDto;
import data.service.GuestCommentService;
import data.service.RoomService;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomService roomService;
	
	@Autowired
	GuestCommentService gcommentService;
	
	@GetMapping("/main")
	public ModelAndView roomMain(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
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
		
		return "redirect:main";
	}
	
	@GetMapping("/content")
	public ModelAndView content(@RequestParam String no, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
		ModelAndView mview = new ModelAndView();
		
		RoomDto roomDto = roomService.getRoom(no);
		
		String photoList[] = roomDto.getPhotos().split(",");
		
		mview.addObject("roomDto", roomDto);
		mview.addObject("photoList", photoList);
		mview.addObject("currentPage", currentPage);
		
		mview.setViewName("/room/roomDetail");
		
		return mview;
	}
}