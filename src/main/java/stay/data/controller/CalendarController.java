package stay.data.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("calendar")
public class CalendarController {

	@GetMapping("")
	public String calendarPage(HttpSession session) {
		
		if(session.getAttribute("myid")==null) {
			return "redirect:/member/login";
		}
		
		return "/room/roomCalendar";
		
	}
}
