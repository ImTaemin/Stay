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

import stay.data.dto.ReservationDto;
import stay.data.dto.RoomDto;
import stay.data.service.ReservationService;
import stay.data.service.RoomService;

@Controller
public class HostReservationController {

	@Autowired
	RoomService roomService;
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/reser/hostreservationlist")
	public ModelAndView HostReservationList(HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		List<ReservationDto> reserList = reservationService.selectHostReservation(myid);
		
		List<ReservationDto> nowList = new ArrayList<ReservationDto>();
		List<ReservationDto> preList = new ArrayList<ReservationDto>();
		
		List<RoomDto> nowRoom = new ArrayList<RoomDto>();
		List<RoomDto> preRoom = new ArrayList<RoomDto>();
		
		LocalDate today = LocalDate.now();
		
		for(ReservationDto reser : reserList) {
			String startDate = reser.getStart_date();
			
			LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
			
			if(start.isAfter(today) || start.equals(today)) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				nowList.add(reser);
				nowRoom.add(roomDto);
			} else if (start.isBefore(today)) {
				RoomDto roomDto = roomService.getRoom(reser.getRoom_no());
				
				String photos = roomDto.getPhotos();
				String photo[] = photos.split(",");
				
				roomDto.setPhotos(photo[0]);
				
				preList.add(reser);
				preRoom.add(roomDto);
			}
		}
		
		mview.addObject("nowList", nowList);
		mview.addObject("preList", preList);
		mview.addObject("nowRoom", nowRoom);
		mview.addObject("preRoom", preRoom);
		
		mview.setViewName("/reservation/reservationList");
		
		return mview;
	}
	
	@GetMapping("/reser/hostreservation")
	public ModelAndView hostReservation(@RequestParam String reserNo, HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		ReservationDto reserDto = reservationService.selectHostOneReservation(reserNo, myid);
		
		String roomNo = reserDto.getNo();
		RoomDto roomDto = roomService.getRoom(roomNo);
		
		mview.addObject("reserDto", reserDto);
		mview.addObject("roomDto", roomDto);
		
		mview.setViewName("/reservation/reservationDetail");
		
		return mview;
	}
}
