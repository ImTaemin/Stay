package stay.data.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.PayCardDto;
import stay.data.dto.ReservationDto;
import stay.data.dto.RoomDto;
import stay.data.service.CostService;
import stay.data.service.GuestCommentService;
import stay.data.service.ReservationService;
import stay.data.service.RoomService;

@Controller
@RequestMapping("/pay")
public class ReservationController {
	@Autowired
	CostService costService;
	
	@Autowired
	GuestCommentService gcommentService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	ReservationService reservationService;
	
	// 결제
	@PostMapping("/paymentform")
	public ModelAndView paymentForm(
			@RequestParam String roomNo, @RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String betweenDay, @RequestParam String roomPrice, @RequestParam String allPrice,
			HttpSession session
			) throws Exception {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		RoomDto roomDto = roomService.getRoom(roomNo);
		
		String photos[] = roomDto.getPhotos().split(",");
		roomDto.setPhotos(photos[0]);
		
		// 날짜 분리
		String start[] = startDate.split("-");
		String end[] = endDate.split("-");
		
		// 카드 리스트
		List<PayCardDto> cardList = costService.getAllCard(myid);
		
		for(PayCardDto p : cardList) {
			String num = p.getNum();
			String numArray[] = num.split("-");
			
			p.setNum(numArray[3]);
		}
		
		// 7일 전 날짜
		String beforeWeek = AddDate(startDate, 0, 0, -7);
		String weekArray[] = beforeWeek.split("-");
		
		// 한달 전 날짜
		String beforeMonth = AddDate(startDate, 0, -1, 0);
		String monthArray[] = beforeMonth.split("-");
		
		// 방 평균 별점
 		Float avgRating = gcommentService.getRatingAvg();
 		
 		if(avgRating == null) {
 			avgRating = (float) 0;
 		}
 		
 		// 방 댓글 개수
 		Integer totalComment = gcommentService.totalComment();
 		
 		if(totalComment == null) {
 			totalComment = 0;
 		}
		
		mview.addObject("roomDto", roomDto);
		mview.addObject("startDate", startDate);
		mview.addObject("endDate", endDate);
		mview.addObject("start", start);
		mview.addObject("end", end);
		mview.addObject("betweenDay", betweenDay);
		mview.addObject("roomPrice", roomPrice);
		mview.addObject("allPrice", allPrice);
		mview.addObject("cardList", cardList);
		mview.addObject("weekArray", weekArray);
		mview.addObject("monthArray", monthArray);
		mview.addObject("avgRating", avgRating);
		mview.addObject("totalComment", totalComment);
		
		mview.setViewName("/cost/paymentForm");
		
		return mview;
	}

	private static String AddDate(String strDate, int year, int month, int day) throws Exception {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		Date dt = dtFormat.parse(strDate);
		
		cal.setTime(dt);
		
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DATE, day);
		
		return dtFormat.format(cal.getTime());
	}

	@PostMapping("/insert")
	public ModelAndView payInsert(
			@ModelAttribute ReservationDto reserDto, @RequestParam String roomNo,
			@RequestParam String startDate, @RequestParam String endDate, @RequestParam String betweenDay,
			@RequestParam String calPrice, @RequestParam String taxPrice, @RequestParam String allPrice,
			@RequestParam String payMethod, @RequestParam String cardNum, HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		RoomDto roomDto = roomService.getRoom(roomNo);
		
		reserDto.setHost_id(roomDto.getHost_id());
		reserDto.setGuest_id(myid);
		reserDto.setRoom_no(roomNo);
		reserDto.setStart_date(startDate);
		reserDto.setEnd_date(endDate);
		reserDto.setPrice(allPrice);
		reserDto.setPay_method(payMethod);
		
		if(payMethod == "card") {
			reserDto.setCard_num(cardNum);
		}
		
		reservationService.insertReservation(reserDto);
		
		mview.setViewName("redirect:/room/main");
		
		return mview;
	}
}