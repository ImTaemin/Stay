package stay.data.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import stay.data.dto.CanReservationDto;
import stay.data.dto.GuestCommentDto;
import stay.data.dto.HostCommentDto;
import stay.data.dto.MemberDto;
import stay.data.dto.ReservationDto;
import stay.data.dto.ResultMapDto;
import stay.data.dto.RoomDto;
import stay.data.service.CanReservationService;
import stay.data.service.CostService;
import stay.data.service.GuestCommentService;
import stay.data.service.HostCommentService;
import stay.data.service.JoinGuestService;
import stay.data.service.MemberService;
import stay.data.service.ReservationService;
import stay.data.service.RoomService;

@Controller
public class HostReservationController {
	@Autowired
	CostService costService;

	@Autowired
	RoomService roomService;

	@Autowired
	ReservationService reservationService;

	@Autowired
	JoinGuestService joinGuestService;

	@Autowired
	MemberService memberService;

	@Autowired
	CanReservationService canReserService;

	@Autowired
	JoinGuestService joinService;
	
	@Autowired
	GuestCommentService guestCommentService;
	
	@Autowired
	HostCommentService hostCommentService;

	@GetMapping("/reser/hostreservationlist")
	public ModelAndView HostReservationList(HttpSession session) {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");

		List<ResultMapDto> nowList = reservationService.selectNowHostReservation(myid);
		List<ResultMapDto> preList = reservationService.selectPreHostReservation(myid);
		List<ResultMapDto> canList = canReserService.getAllHostCanReser(myid);

		// ????????? ??????
		for (ResultMapDto dto : nowList) {
			String photos = dto.getRoomDto().getPhotos();
			String photo[] = photos.split(",");

			String roomNo = dto.getResDto().getRoom_no();
			RoomDto rdto = roomService.getRoom(roomNo);
			rdto.setPhotos(photo[0]);

			dto.setRoomDto(rdto);
		}

		// ?????? ??????
		for (ResultMapDto dto : preList) {
			String photos = dto.getRoomDto().getPhotos();
			String photo[] = photos.split(",");

			String roomNo = dto.getResDto().getRoom_no();
			RoomDto rdto = roomService.getRoom(roomNo);
			rdto.setPhotos(photo[0]);

			dto.setRoomDto(rdto);
			
			String reserNo = dto.getResDto().getNo();
			
			// ????????? ?????? ??????
			GuestCommentDto gcommentDto = guestCommentService.getOneComment(reserNo);
			int countGuestComment = guestCommentService.checkCommentHost(reserNo, myid);

			if (gcommentDto == null) {
				countGuestComment = 0;
				gcommentDto = new GuestCommentDto();
			}

			gcommentDto.setCountLike(countGuestComment);

			dto.setGcoDto(gcommentDto);
			
			// ????????? ?????? ??????
			HostCommentDto hcommentDto = hostCommentService.getOneHostComment(reserNo);
			int countHostComment = hostCommentService.checkRecommentHost(reserNo, myid);
			
			if (hcommentDto == null) {
				countHostComment = 0;
				hcommentDto = new HostCommentDto();
			}
			
			hcommentDto.setCount(countHostComment);
			
			dto.setHcoDto(hcommentDto);
		}

		// ????????? ??????
		for (ResultMapDto dto : canList) {
			String roomNo = dto.getResDto().getRoom_no();
			RoomDto rdto = roomService.getRoom(roomNo);

			String photos = rdto.getPhotos();
			String photo[] = photos.split(",");

			rdto.setPhotos(photo[0]);

			dto.setRoomDto(rdto);
		}

		mview.addObject("nowList", nowList);
		mview.addObject("preList", preList);
		mview.addObject("canList", canList);

		mview.setViewName("/reservation/hostReservationList");

		return mview;
	}

	@PostMapping("/reser/hostreservation")
	public ModelAndView HostReservation(@RequestParam String reserNo, HttpSession session) throws Exception {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");

		ReservationDto reserDto = reservationService.selectHostOneReservation(reserNo, myid);
		
		// ?????? ?????? ????????????
		String roomNo = reserDto.getRoom_no();
		
		RoomDto roomDto = roomService.getRoom(roomNo);

		// ?????? split
		String startDate = reserDto.getStart_date();
		String start[] = startDate.split("-");

		String endDate = reserDto.getEnd_date();
		String end[] = endDate.split("-");

		// ?????? ?????????
		String startDayWeek = getDateDay(startDate);
		String endDayWeek = getDateDay(endDate);

		// ?????? ????????? ??????
		int joinGuestNum = joinGuestService.countJoinGuest(reserNo) + 1;

		// ????????? ?????? ????????????
		String guestId = reserDto.getGuest_id();
		MemberDto guestDto = memberService.getMember(guestId);

		// ?????? ??????
		LocalDate today = LocalDate.now();
		LocalDate startLocal = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		Boolean preCheck = false;

		if (startLocal.isBefore(today)) {
			preCheck = true;
		}

		// ?????? ?????? ??????
		CanReservationDto canReserDto = canReserService.getOneCanReser(reserNo);

		Boolean canCheck = false;

		if (canReserDto != null) {
			String canNo = canReserDto.getNo();

			if (reserNo.equals(canNo)) {
				canCheck = true;
			}
		}

		// ?????? ?????????
		List<ResultMapDto> joinList = joinService.getAllJoinGuest(reserNo);
		
		// ????????? ??????
		GuestCommentDto gcommentDto = guestCommentService.getOneComment(reserNo);
		
		mview.addObject("reserDto", reserDto);
		mview.addObject("roomDto", roomDto);
		mview.addObject("start", start);
		mview.addObject("end", end);
		mview.addObject("startDayWeek", startDayWeek);
		mview.addObject("endDayWeek", endDayWeek);
		mview.addObject("joinGuestNum", joinGuestNum);
		mview.addObject("preCheck", preCheck);
		mview.addObject("canReserDto", canReserDto);
		mview.addObject("canCheck", canCheck);
		mview.addObject("guestDto", guestDto);
		mview.addObject("joinList", joinList);
		mview.addObject("gcommentDto", gcommentDto);

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
			day = "???";
			break;
		case 2:
			day = "???";
			break;
		case 3:
			day = "???";
			break;
		case 4:
			day = "???";
			break;
		case 5:
			day = "???";
			break;
		case 6:
			day = "???";
			break;
		case 7:
			day = "???";
			break;
		}

		return day;
	}
}