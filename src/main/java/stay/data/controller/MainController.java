package stay.data.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.MemberDto;
import stay.data.dto.ReservationDto;
import stay.data.dto.RoomDto;
import stay.data.service.GuestCommentService;
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
	
	//첫메인_게스트모드가 기본이므로 게스트메인
   @GetMapping("/")
   public ModelAndView home(HttpSession session) {
		
	   ModelAndView mview = new ModelAndView();
		
	   List<RoomDto> bestList = roomService.getBestRoom();
		
		for(RoomDto dto : bestList) {
			String photos[] = dto.getPhotos().split(",");
			
			dto.setPhotos(photos[0]);
		}
		
		mview.addObject("bestList", bestList);
		
		String myid = (String)session.getAttribute("myid");
		String loginok = (String)session.getAttribute("loginok");
		
		if(loginok != null) {
			MemberDto memberDto = memberService.getMember(myid);
			
			String photo = memberDto.getPhoto();
			
			session.setAttribute("photo", photo);
		}
		
		mview.setViewName("/layout/guestMain");
		
		return mview;
   }
   
   //호스트메인_호스트전환 클릭할 경우
   @GetMapping("/host/main")
	public ModelAndView reservationList(HttpSession session) {
	   
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		//호스트모드 예약상태별 예약 목록
		List<ReservationDto> reserList = reservationService.selectHostReservation(myid);
		
		List<ReservationDto> inList = new ArrayList<ReservationDto>();
		List<ReservationDto> outList = new ArrayList<ReservationDto>();
		List<ReservationDto> hosList = new ArrayList<ReservationDto>(); 
		
		List<RoomDto> inRoom = new ArrayList<RoomDto>();
		List<RoomDto> outRoom = new ArrayList<RoomDto>();
		List<RoomDto> hosRoom = new ArrayList<RoomDto>();
		
		//호스트모드 체크인예정 목록중 최근 3개
		List<ReservationDto> reserThreeList = reservationService.selectHostThreeReservation(myid);
		
		List<ReservationDto> inThreeList = new ArrayList<ReservationDto>();
		List<RoomDto> inThreeRoom = new ArrayList<RoomDto>();
		
		LocalDate today = LocalDate.now();
		
		//호스트모드 예약상태별 예약 목록
		for(ReservationDto reser : reserList) {
			
			String startDate = reser.getStart_date();
			String endDate = reser.getEnd_date();
			
			LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
			LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
			
			if(start.isAfter(today)) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				inList.add(reser);
				inRoom.add(roomDto);
			} else if (end.isBefore(today)) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				outList.add(reser);
				outRoom.add(roomDto);
			} else if ((start.isBefore(today) || start.equals(today)) && (end.isAfter(today) || end.equals(today))) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				hosList.add(reser);
				hosRoom.add(roomDto);
			}
		}
		
		//호스트모드 예약상태별 예약 목록중 최근 3개
		for(ReservationDto reser : reserThreeList) {
			

				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				inThreeList.add(reser);
				inThreeRoom.add(roomDto);
			
		}
		
		//호스트모드 예약상태별 예약 목록
		mview.addObject("inList", inList);
		mview.addObject("outList", outList);
		mview.addObject("hosList", hosList);
		mview.addObject("inRoom", inRoom);
		mview.addObject("outRoom", outRoom);
		mview.addObject("hosRoom", hosRoom);
		
		//호스트모드 예약상태별 예약 목록중 최근 3개
		mview.addObject("inThreeList", inThreeList);
		mview.addObject("inThreeRoom", inThreeRoom);

		
		mview.setViewName("/layout/hostMain");
		
		return mview;
	}
   
   @GetMapping("/changeMode")
   public String modeChange(HttpSession session) {
	   if(((String)session.getAttribute("mode")).equals("guest")) {
		   session.setAttribute("mode", "host");
//		   session.removeAttribute("mode");
//		   session.setAttribute("mode", "host");
		   return "redirect:/host/main";
	   } else {
		   session.setAttribute("mode", "guest");
//		   session.removeAttribute("mode");
//		   session.setAttribute("mode", "host");
		   return "redirect:/";
	   }
	   
   }
   
  
}