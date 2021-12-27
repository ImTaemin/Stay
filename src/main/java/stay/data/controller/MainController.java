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
		
		List<ReservationDto> reserList = reservationService.selectHostReservation(myid);
		
		List<ReservationDto> inList = new ArrayList<ReservationDto>();
		List<ReservationDto> outList = new ArrayList<ReservationDto>();
		List<ReservationDto> hosList = new ArrayList<ReservationDto>(); 
		
		List<RoomDto> inRoom = new ArrayList<RoomDto>();
		List<RoomDto> outRoom = new ArrayList<RoomDto>();
		List<RoomDto> hosRoom = new ArrayList<RoomDto>();
		
		List<ReservationDto> reserThreeList = reservationService.selectHostThreeReservation(myid);
		
		List<ReservationDto> inThreeList = new ArrayList<ReservationDto>();
		List<ReservationDto> outThreeList = new ArrayList<ReservationDto>();
		List<ReservationDto> hosThreeList = new ArrayList<ReservationDto>(); 
		
		List<RoomDto> inThreeRoom = new ArrayList<RoomDto>();
		List<RoomDto> outThreeRoom = new ArrayList<RoomDto>();
		List<RoomDto> hosThreeRoom = new ArrayList<RoomDto>();
		
		LocalDate today = LocalDate.now();
		
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
		
for(ReservationDto reser : reserThreeList) {
			
			String startDate = reser.getStart_date();
			String endDate = reser.getEnd_date();
			
			LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
			LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
			
			if(start.isAfter(today)) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				inThreeList.add(reser);
				inThreeRoom.add(roomDto);
			} else if (end.isBefore(today)) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				outThreeList.add(reser);
				outThreeRoom.add(roomDto);
			} else if ((start.isBefore(today) || start.equals(today)) && (end.isAfter(today) || end.equals(today))) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				hosThreeList.add(reser);
				hosThreeRoom.add(roomDto);
			}
		}
		
		mview.addObject("inList", inList);
		mview.addObject("outList", outList);
		mview.addObject("hosList", hosList);
		mview.addObject("inRoom", inRoom);
		mview.addObject("outRoom", outRoom);
		mview.addObject("hosRoom", hosRoom);
		
		mview.addObject("inThreeList", inThreeList);
		mview.addObject("outThreeList", outThreeList);
		mview.addObject("hosThreeList", hosThreeList);
		mview.addObject("inThreeRoom", inThreeRoom);
		mview.addObject("outThreeRoom", outThreeRoom);
		mview.addObject("hosThreeRoom", hosThreeRoom);
		
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