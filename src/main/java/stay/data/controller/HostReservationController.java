package stay.data.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.GuestCommentDto;
import stay.data.dto.MemberDto;
import stay.data.dto.ReservationDto;
import stay.data.dto.RoomDto;
import stay.data.service.CostService;
import stay.data.service.GuestCommentService;
import stay.data.service.JoinGuestService;
import stay.data.service.MemberService;
import stay.data.service.ReservationService;
import stay.data.service.RoomService;

@Controller
public class HostReservationController {

	@Autowired
	CostService costService;
	
	@Autowired
	GuestCommentService gCommentService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	JoinGuestService joinGuestService;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/reser/hostreservationlist")
	public ModelAndView HostReservationList(HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		List<ReservationDto> hostReserList = reservationService.selectHostReservation(myid);
		
		List<ReservationDto> hostNowList = new ArrayList<ReservationDto>();
		List<ReservationDto> hostPreList = new ArrayList<ReservationDto>();
		
		List<RoomDto> hostNowRoom = new ArrayList<RoomDto>();
		List<RoomDto> hostPreRoom = new ArrayList<RoomDto>();
		
		LocalDate today = LocalDate.now();
		
		for(ReservationDto reser : hostReserList) {
			String startDate = reser.getStart_date();
			
			LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
			
			if(start.isAfter(today) || start.equals(today)) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				hostNowList.add(reser);
				hostNowRoom.add(roomDto);
			} else if (start.isBefore(today)) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				hostPreList.add(reser);
				hostPreRoom.add(roomDto);
			}
		}
		
		mview.addObject("hostNowList", hostNowList);
		mview.addObject("hostPreList", hostPreList);
		mview.addObject("hostNowRoom", hostNowRoom);
		mview.addObject("hostPreRoom", hostPreRoom);
		
		mview.setViewName("/reservation/hostReservationList");
		
		return mview;
	}
	
	@PostMapping("/reser/hostreservation")
	public ModelAndView hostReservation(@RequestParam String reserNo, HttpSession session) throws Exception {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		ReservationDto reserDto = reservationService.selectHostOneReservation(reserNo, myid);
		
		// 숙소 정보 가져오기
		String roomNo = reserDto.getRoom_no();
		RoomDto roomDto = roomService.getRoom(roomNo);
		
		// 날짜 split
		String startDate = reserDto.getStart_date();
		String start[] = startDate.split("-");
		
		String endDate = reserDto.getEnd_date();
		String end[] = endDate.split("-");
		
		// 요일 구하기
		String startDayWeek = getDateDay(startDate); 
		String endDayWeek = getDateDay(endDate);
		
		// 공동 게스트 출력
		int joinGuestNum = joinGuestService.countJoinGuest(roomNo) + 1;
		
		// 게스트 정보 가져오기
		String guestId = reserDto.getGuest_id();
		MemberDto guestDto = memberService.getMember(guestId);
		
		// 날짜 비교
		LocalDate today = LocalDate.now();
		LocalDate startLocal = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		Boolean preCheck = false;
		
		if (startLocal.isBefore(today)) {
			preCheck = true;
		}
		
		// 후기 작성
		GuestCommentDto gCommentDto = gCommentService.getOneComment(roomNo, myid);
		
		mview.addObject("reserDto", reserDto);
		mview.addObject("roomDto", roomDto);
		mview.addObject("start", start);
		mview.addObject("end", end);
		mview.addObject("startDayWeek", startDayWeek);
		mview.addObject("endDayWeek", endDayWeek);
		mview.addObject("joinGuestNum", joinGuestNum);
		mview.addObject("guestDto", guestDto);
		mview.addObject("preCheck", preCheck);
		mview.addObject("gCommentDto", gCommentDto);
		
		mview.setViewName("/reservation/hostReservationDetail");
		
		return mview;
	}
	
	public String getDateDay(String date) throws Exception {
		String day = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nDate = dateFormat.parse(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);

		int dayNum = cal.get(Calendar.DAY_OF_WEEK);

		switch (dayNum) {
			case 1:
				day = "일";
				break;
			case 2:
				day = "월";
				break;
			case 3:
				day = "화";
				break;
			case 4:
				day = "수";
				break;
			case 5:
				day = "목";
				break;
			case 6:
				day = "금";
				break;
			case 7:
				day = "토";
				break;
		}

		return day;
	}
}
