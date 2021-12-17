package data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}