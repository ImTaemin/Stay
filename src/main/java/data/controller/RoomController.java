package data.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	public String roomInsert(@ModelAttribute RoomDto roomDto, @RequestParam ArrayList<MultipartFile> upload, HttpSession session) {
		// 업로드할 폴더 지정
		String path = session.getServletContext().getRealPath("/photo");
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
		
		// 추후 로그인 아이디 값으로 변경
		String myid = "stay";
		
		roomDto.setHost_id(myid);
		roomDto.setPhotos(photo);
		
		roomService.insertRoom(roomDto);
		
		return "redirect:main";
	}
}