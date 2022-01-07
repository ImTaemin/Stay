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

import stay.data.dto.RoomDto;
import stay.data.dto.RoomHostingDto;
import stay.data.dto.RoomPirceDto;
import stay.data.service.CalendarService;
import stay.data.service.RoomService;

@Controller
@RequestMapping("calendar")
public class CalendarController {
	@Autowired
	RoomService roomService;
	
	@Autowired
	CalendarService calendarService;

	@GetMapping("")
	public ModelAndView calendarPage(HttpSession session) {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");

		if (myid == null) {
			mview.setViewName("redirect:/member/login");

			return mview;
		}

		List<RoomDto> roomList = roomService.getRoomList(myid);

		mview.addObject("roomList", roomList);

		mview.setViewName("/room/roomCalendar");

		return mview;
	}

	@PostMapping("/searchroom")
	public @ResponseBody RoomDto searchRoom(@RequestParam String roomNo) {
		return roomService.getRoom(roomNo);
	}

	@PostMapping("/updatehosting")
	public @ResponseBody void updateHosting(
			@ModelAttribute RoomHostingDto hostingDto,
			@RequestParam String roomNo, @RequestParam String chageDate, @RequestParam boolean hosting) {
		boolean roomHosting = roomService.getRoomHosting(roomNo);
		
		if(hosting != roomHosting) {
			hostingDto.setNo(roomNo);
			hostingDto.setChange_date(chageDate);
			hostingDto.setHosting(hosting);
			
			calendarService.insertHosting(hostingDto);
		} else {
			calendarService.deleteHosting(roomNo, chageDate);
		}
	}
	
	@PostMapping("/updateprice")
	public @ResponseBody void updatePrice(
			@ModelAttribute RoomPirceDto priceDto,
			@RequestParam String roomNo, @RequestParam String chageDate, @RequestParam int roomPrice) {
		if(roomPrice != roomService.getRoom(roomNo).getPrice()) {
			priceDto.setNo(roomNo);
			priceDto.setChange_date(chageDate);
			priceDto.setPrice(roomPrice);
			
			calendarService.insertPrice(priceDto);
		} else {
			calendarService.deletePrice(roomNo, chageDate);
		}
	}
}
