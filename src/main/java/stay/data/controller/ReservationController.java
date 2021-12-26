package stay.data.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.PayCardDto;
import stay.data.dto.ReservationDto;
import stay.data.dto.RoomDto;
import stay.data.service.CostService;
import stay.data.service.GuestCommentService;
import stay.data.service.ReservationService;
import stay.data.service.RoomService;

@Controller
public class ReservationController {
	@Autowired
	CostService costService;
	
	@Autowired
	GuestCommentService gcommentService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	ReservationService reservationService;
	
	@PostMapping("/pay/paymentform")
	public ModelAndView paymentForm(
			@RequestParam String roomNo, @RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String betweenDay, @RequestParam String roomPrice, @RequestParam String allPrice,
			HttpSession session) throws Exception {
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
			
			p.setLast_num(numArray[3]);
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

	@PostMapping("/pay/insert")
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
	
	@GetMapping("/pay/kakaopay")
	public @ResponseBody String kakaoPay(
			@ModelAttribute ReservationDto reserDto, @RequestParam Map<String, Object> param, HttpSession session) {
		String myid = (String)session.getAttribute("myid");
		
		String roomNo = (String)param.get("roomNo");
		String allPrice = (String)param.get("allPrice");
		String taxPrice = (String)param.get("taxPrice");
		String startDate = (String)param.get("startDate");
		String endDate = (String)param.get("endDate");
		
		RoomDto roomDto = roomService.getRoom(roomNo);
		
		reserDto.setGuest_id(myid);
		reserDto.setHost_id(roomDto.getHost_id());
		reserDto.setStart_date(startDate);
		reserDto.setEnd_date(endDate);
		reserDto.setPrice(allPrice);
		reserDto.setPay_method("kakao");
		reserDto.setRoom_no(roomNo);
		
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection connection = (HttpURLConnection) address.openConnection(); // 서버연결
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "KakaoAK 5e26b9346af700eb3b3a3c9286aa7c87"); // 어드민 키
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.setDoOutput(true); // 서버한테 전달할게 있는지 없는지
			
			String parameter = "cid=TC0ONETIME" // 가맹점 코드
								+ "&partner_order_id=partner_order_id" // 가맹점 주문번호
								+ "&partner_user_id=" + myid // 가맹점 회원 id
								+ "&item_name=Swim,"// 상품명
								+ "&quantity=1"// 상품 수량
								+ "&total_amount=" + allPrice // 총 금액
								+ "&vat_amount=" + taxPrice // 부가세
								+ "&tax_free_amount=0" // 상품 비과세 금액
								+ "&approval_url=http://localhost:8080/" // 결제 성공 시
								+ "&fail_url=http://localhost:8080/" // 결제 실패 시
								+ "&cancel_url=http://localhost:8080/"; // 결제 취소 시
			
			OutputStream send = connection.getOutputStream(); // 이제 뭔가를 를 줄 수 있다.
			DataOutputStream dataSend = new DataOutputStream(send); // 이제 데이터를 줄 수 있다.
			dataSend.writeBytes(parameter); // OutputStream은 데이터를 바이트 형식으로 주고 받기로 약속되어 있다. (형변환)
			dataSend.close(); // flush가 자동으로 호출이 되고 닫는다. (보내고 비우고 닫다)
			
			int result = connection.getResponseCode(); // 전송 잘 됐나 안됐나 번호를 받는다.
			InputStream receive; // 받다
			
			if (result == 200) {
				receive = connection.getInputStream();
				
				reservationService.insertReservation(reserDto);
			} else {
				receive = connection.getErrorStream(); 
			}
			
			// 읽는 부분
			InputStreamReader read = new InputStreamReader(receive); // 받은걸 읽는다.
			BufferedReader change = new BufferedReader(read); // 바이트를 읽기 위해 형변환 버퍼리더는 실제로 형변환을 위해 존제하는 클레스는 아니다.
			// 받는 부분
			return change.readLine(); // 문자열로 형변환을 알아서 해주고 찍어낸다 그리고 본인은 비워진다.
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}
	
	@GetMapping("/reser/reservationlist")
	public ModelAndView reservationList(HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		List<ReservationDto> reserList = reservationService.selectReservation(myid);
		
		mview.addObject("reserList", reserList);
		
		mview.setViewName("/reservation/reservationList");
		
		return mview;
	}
}