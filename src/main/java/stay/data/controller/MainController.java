package stay.data.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.JoinGuestDto;
import stay.data.dto.MemberDto;
import stay.data.dto.ResultMapDto;
import stay.data.dto.RoomDto;
import stay.data.service.CanReservationService;
import stay.data.service.GuestCommentService;
import stay.data.service.JoinGuestService;
import stay.data.service.MemberService;
import stay.data.service.ReservationService;
import stay.data.service.RoomService;

@Controller
public class MainController {
	@Autowired
	RoomService roomService;

	@Autowired
	MemberService memberService;

	@Autowired
	GuestCommentService gcommentService;

	@Autowired
	ReservationService reservationService;

	@Autowired
	CanReservationService canReserService;

	@Autowired
	JoinGuestService joinService;

	// 첫메인_게스트모드가 기본이므로 게스트메인
	@GetMapping("/")
	public ModelAndView home(HttpSession session) {

		ModelAndView mview = new ModelAndView();

		List<RoomDto> bestList = roomService.getBestRoom();

		for (RoomDto dto : bestList) {
			String photos[] = dto.getPhotos().split(",");

			dto.setPhotos(photos[0]);
		}

		mview.addObject("bestList", bestList);

		String myid = (String) session.getAttribute("myid");
		String loginok = (String) session.getAttribute("loginok");

		if (loginok != null) {
			MemberDto memberDto = memberService.getMember(myid);
			
			String photo = memberDto.getPhoto();

			session.setAttribute("photo", photo);
		}

		mview.setViewName("/layout/guestMain");

		return mview;
	}

	// 호스트메인_호스트전환 클릭할 경우
	@GetMapping("/host/main")
	public ModelAndView reservationList(HttpSession session) {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");
		
		int checkReser = reservationService.checkHostReservation(myid);

		List<ResultMapDto> checkInList = reservationService.selectCheckInHostReservation(myid);
		List<ResultMapDto> checkOutList = reservationService.selectCheckOutHostReservation(myid);
		List<ResultMapDto> hostingList = reservationService.selectHostingHostReservation(myid);

		// 호스트모드 체크인예정 목록중 최근 3개
		List<ResultMapDto> reserThreeList = reservationService.selectHostThreeReservation(myid);

		// 체크인 예정
		for (ResultMapDto dto : checkInList) {
			String photos = dto.getRoomDto().getPhotos();
			String photo[] = photos.split(",");

			String roomNo = dto.getResDto().getRoom_no();
			RoomDto rdto = roomService.getRoom(roomNo);
			rdto.setPhotos(photo[0]);

			dto.setRoomDto(rdto);

			// 조인 게스트
			String reserNo = dto.getResDto().getNo();
			List<JoinGuestDto> joinDtoList = joinService.selectListJoin(reserNo);

			int joinNum = joinService.countJoinGuest(reserNo) + 1;

			for (JoinGuestDto joinDto : joinDtoList) {
				joinDto.setCount(joinNum);

				dto.setJoinDto(joinDto);
			}
		}

		// 체크아웃 예정
		for (ResultMapDto dto : checkOutList) {
			String photos = dto.getRoomDto().getPhotos();
			String photo[] = photos.split(",");

			String roomNo = dto.getResDto().getRoom_no();
			RoomDto rdto = roomService.getRoom(roomNo);
			rdto.setPhotos(photo[0]);

			dto.setRoomDto(rdto);

			// 조인 게스트
			String reserNo = dto.getResDto().getNo();
			List<JoinGuestDto> joinDtoList = joinService.selectListJoin(reserNo);

			int joinNum = joinService.countJoinGuest(reserNo) + 1;

			for (JoinGuestDto joinDto : joinDtoList) {
				joinDto.setCount(joinNum);

				dto.setJoinDto(joinDto);
			}
		}

		// 호스팅 중
		for (ResultMapDto dto : hostingList) {
			String photos = dto.getRoomDto().getPhotos();
			String photo[] = photos.split(",");

			String roomNo = dto.getResDto().getRoom_no();
			RoomDto rdto = roomService.getRoom(roomNo);
			rdto.setPhotos(photo[0]);

			dto.setRoomDto(rdto);

			// 조인 게스트
			String reserNo = dto.getResNoDto().getReser_no();
			List<JoinGuestDto> joinDtoList = joinService.selectListJoin(reserNo);

			int joinNum = joinService.countJoinGuest(reserNo) + 1;

			for (JoinGuestDto joinDto : joinDtoList) {
				joinDto.setCount(joinNum);

				dto.setJoinDto(joinDto);
			}
		}

		// 호스트모드 예약상태별 예약 목록중 최근 3개
		for (ResultMapDto reser : reserThreeList) {
			String roomNo = reser.getResDto().getRoom_no();

			RoomDto roomDto = roomService.getRoom(roomNo);

			String photos = roomDto.getPhotos();
			String photo[] = photos.split(",");

			roomDto.setPhotos(photo[0]);

			reser.setRoomDto(roomDto);
		}

		mview.addObject("checkReser", checkReser);
		
		mview.addObject("checkInList", checkInList);
		mview.addObject("checkOutList", checkOutList);
		mview.addObject("hostingList", hostingList);

		// 호스트모드 예약상태별 예약 목록중 최근 3개
		mview.addObject("reserThreeList", reserThreeList);

		mview.setViewName("/layout/hostMain");

		return mview;
	}

	// header의 메뉴 아이콘_게스트모드 전환, 호스트모드 전환
	@GetMapping("/changeMode")
	public String modeChange(HttpSession session) {
		if (((String) session.getAttribute("mode")).equals("guest")) {
			session.setAttribute("mode", "host");
			return "redirect:/host/main";
		} else {
			session.setAttribute("mode", "guest");
			return "redirect:/";
		}

	}

	@GetMapping("/searchRoomSite")
	public @ResponseBody List<RoomDto> searchRoomSite(@RequestParam(value = "search") String search) {
		List<RoomDto> rdtoList = roomService.getRoomSite(search);

		return rdtoList;
	}

}