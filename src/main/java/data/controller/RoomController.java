package data.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.dto.RoomDto;
import data.mapper.RoomMapper;
import data.service.RoomService;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomService roomService;
	
	@GetMapping("/main")
	public ModelAndView roomMain() {
		ModelAndView mview = new ModelAndView();

		int totalCount = roomService.getRoomCount();
		
		mview.addObject("totalCount", totalCount);
		
		mview.setViewName("/room/roomMain");
		
		return mview;
	}
	
	@GetMapping("/insertform")
	public String roomInsertForm() {
		return "/room/roomInsertForm";
	}
	
	@PostMapping("/insert")
	public String roomInsert(@ModelAttribute RoomDto roomDto, HttpSession session) {
		// 업로드할 폴더 지정
		String path = session.getServletContext().getRealPath("/photo");
		
		// 업로드할 파일명
		if(roomDto.getUpload().getOriginalFilename().equals("")) {
			// DB에 no라고 저장
			roomDto.setPhotos(path);
		} else {
			// 업로드 할 경우
			String uploadfile = roomDto.getUpload().getOriginalFilename();
			
			roomDto.setPhotos(uploadfile);
			
			// 실제 업로드
			try {
				roomDto.getUpload().transferTo(new File(path + "\\" + uploadfile));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 추후 session으로 변경
		String myid = "stay";
		
		roomDto.setHost_id(myid);
		
		
		
		roomService.insertRoom(roomDto);
		
		return "redirect:main";
	}
}