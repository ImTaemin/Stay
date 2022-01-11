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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import stay.data.dto.CanReservationDto;
import stay.data.dto.ChatDto;
import stay.data.dto.GuestCommentDto;
import stay.data.dto.MemberDto;
import stay.data.dto.PayCardDto;
import stay.data.dto.ReceiptDto;
import stay.data.dto.ReservationDto;
import stay.data.dto.ResultMapDto;
import stay.data.dto.RoomDto;
import stay.data.service.CanReservationService;
import stay.data.service.ChatService;
import stay.data.service.CostService;
import stay.data.service.GuestCommentService;
import stay.data.service.JoinGuestService;
import stay.data.service.MemberService;
import stay.data.service.ReceiptService;
import stay.data.service.ReservationService;
import stay.data.service.RoomService;

@Controller
public class ReservationController {
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

	@Autowired
	CanReservationService canReserService;

	@Autowired
	JoinGuestService joinService;

	@Autowired
	ReceiptService receiptService;
	
	@Autowired
	ChatService chatService;

	@PostMapping("/pay/paymentform")
	public ModelAndView paymentForm(@RequestParam String roomNo, @RequestParam String startDate,
			@RequestParam String endDate, @RequestParam String betweenDay, @RequestParam String roomPrice,
			@RequestParam String allPrice, HttpSession session) throws Exception {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");

		ResultMapDto dto = roomService.getOneRoom(roomNo);

		// 이미지 분리
		RoomDto roomDto = roomService.getRoom(roomNo);

		String photos[] = roomDto.getPhotos().split(",");
		roomDto.setPhotos(photos[0]);

		dto.setRoomDto(roomDto);

		// 날짜 분리
		String start[] = startDate.split("-");
		String end[] = endDate.split("-");

		// 카드 리스트
		List<PayCardDto> cardList = costService.getAllCard(myid);

		for (PayCardDto p : cardList) {
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

		mview.addObject("dto", dto);
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
	public ModelAndView payInsert(@ModelAttribute ReservationDto reserDto, @ModelAttribute ReceiptDto receiptDto,
			@RequestParam String roomNo, @RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String betweenDay, @RequestParam String calPrice, @RequestParam String taxPrice,
			@RequestParam String allPrice, @RequestParam String payMethod, @RequestParam String cardNum,
			HttpSession session) {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");

		// 예약 추가
		RoomDto roomDto = roomService.getRoom(roomNo);

		reserDto.setHost_id(roomDto.getHost_id());
		reserDto.setGuest_id(myid);
		reserDto.setRoom_no(roomNo);
		reserDto.setStart_date(startDate);
		reserDto.setEnd_date(endDate);
		reserDto.setPrice(allPrice);
		reserDto.setPay_method(payMethod);

		if (payMethod == "card") {
			reserDto.setCard_num(cardNum);
		}

		reservationService.insertReservation(reserDto);
		
		// 대화 추가
		RoomDto room = roomService.getRoom(roomNo);
		
		ChatDto chatDto = new ChatDto();
		chatDto.setSender(myid);
		chatDto.setReceiver(reserDto.getHost_id());
		chatDto.setMsg(reserDto.getStart_date() + "-" + reserDto.getEnd_date() + "에 " + room.getName().substring(0,10) + "... 예약이 완료되었습니다.");
		chatService.insertChat(chatDto);
		
		// 영수증 추가
		String reserNo = reservationService.getReserMaxNo();
		receiptDto.setNo(reserNo);

		receiptService.receInsert(receiptDto);

		mview.setViewName("redirect:/reser/reservationlist");

		return mview;
	}

	@GetMapping("/pay/kakaopay")
	public @ResponseBody String kakaoPay(@ModelAttribute ReservationDto reserDto, @ModelAttribute ReceiptDto receiptDto,
			@RequestParam Map<String, Object> param, HttpSession session) {
		String myid = (String) session.getAttribute("myid");

		String roomNo = (String) param.get("roomNo");
		String allPrice = (String) param.get("allPrice");
		String taxPrice = (String) param.get("taxPrice");
		String startDate = (String) param.get("startDate");
		String endDate = (String) param.get("endDate");

		RoomDto roomDto = roomService.getRoom(roomNo);

		reserDto.setGuest_id(myid);
		reserDto.setHost_id(roomDto.getHost_id());
		reserDto.setStart_date(startDate);
		reserDto.setEnd_date(endDate);
		reserDto.setPrice(allPrice);
		reserDto.setPay_method("kakao");
		reserDto.setRoom_no(roomNo);
		
		// 대화 추가
		ChatDto chatDto = new ChatDto();
		chatDto.setSender(myid);
		chatDto.setReceiver(reserDto.getHost_id());
		chatDto.setMsg(reserDto.getStart_date() + "-" + reserDto.getEnd_date() + "에 " + roomDto.getName().substring(0,10) + "... 예약이 완료되었습니다.");
		chatService.insertChat(chatDto);

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
					+ "&approval_url=https://localhost:8080/reser/reservationlist" // 결제 성공 시
					+ "&fail_url=https://localhost:8080/" // 결제 실패 시
					+ "&cancel_url=https://localhost:8080/"; // 결제 취소 시

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

			// 영수증 추가
			String reserNo = reservationService.getReserMaxNo();
			receiptDto.setNo(reserNo);

			receiptService.receInsert(receiptDto);

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

		String myid = (String) session.getAttribute("myid");

		List<ResultMapDto> nowList = reservationService.selectNowGuestReservation(myid);
		List<ResultMapDto> preList = reservationService.selectPreGuestReservation(myid);
		List<ResultMapDto> canList = canReserService.getAllCanReser(myid);

		// 예정된 예약
		for (ResultMapDto dto : nowList) {
			String photos = dto.getRoomDto().getPhotos();
			String photo[] = photos.split(",");

			String roomNo = dto.getResDto().getRoom_no();
			RoomDto rdto = roomService.getRoom(roomNo);
			rdto.setPhotos(photo[0]);

			dto.setRoomDto(rdto);
		}

		// 이전 예약
		for (ResultMapDto dto : preList) {
			String photos = dto.getRoomDto().getPhotos();
			String photo[] = photos.split(",");

			String roomNo = dto.getResDto().getRoom_no();
			RoomDto rdto = roomService.getRoom(roomNo);
			rdto.setPhotos(photo[0]);

			dto.setRoomDto(rdto);

			// 후기 여부
			String reserNo = dto.getResDto().getNo();

			GuestCommentDto gcommentDto = gCommentService.getOneComment(reserNo);
			int countComment = gCommentService.checkCommentGuest(reserNo, myid);
			
			if (gcommentDto == null) {
				countComment = 0;
				gcommentDto = new GuestCommentDto();
			}

			gcommentDto.setCountLike(countComment);

			dto.setGcoDto(gcommentDto);
			
			// 메인 게스트
			String mainId = reservationService.getMainGuest(reserNo);
			
			ReservationDto reserDto = reservationService.selectGuestOneReservation(reserNo);
			reserDto.setGuest_id(mainId);
			
			dto.setResDto(reserDto);
		}

		// 취소된 예약
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

		mview.setViewName("/reservation/reservationList");

		return mview;
	}

	@PostMapping("/reser/reservation")
	public ModelAndView reservation(@RequestParam String reserNo, HttpSession session) throws Exception {
		ModelAndView mview = new ModelAndView();

		ReservationDto reserDto = reservationService.selectGuestOneReservation(reserNo);
		
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
		int joinGuestNum = joinGuestService.countJoinGuest(reserNo) + 1;

		// 호스트 정보 가져오기
		String hostId = reserDto.getHost_id();
		MemberDto hostDto = memberService.getMember(hostId);

		// 날짜 비교
		LocalDate today = LocalDate.now();
		LocalDate startLocal = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		Boolean preCheck = false;

		if (startLocal.isBefore(today)) {
			preCheck = true;
		}

		// 후기 작성
		GuestCommentDto gCommentDto = gCommentService.getOneComment(reserNo);

		// 예약 취소 여부
		CanReservationDto canReserDto = canReserService.getOneCanReser(reserNo);

		Boolean canCheck = false;

		if (canReserDto != null) {
			String canNo = canReserDto.getNo();

			if (reserNo.equals(canNo)) {
				canCheck = true;
			}
		}
		
		// 메인 게스트
		String mainId = reservationService.getMainGuest(reserNo);
		MemberDto mainDto = memberService.getMember(mainId);

		// 조인 게스트
		List<ResultMapDto> joinList = joinService.getAllJoinGuest(reserNo);

		mview.addObject("reserDto", reserDto);
		mview.addObject("roomDto", roomDto);
		mview.addObject("start", start);
		mview.addObject("end", end);
		mview.addObject("startDayWeek", startDayWeek);
		mview.addObject("endDayWeek", endDayWeek);
		mview.addObject("joinGuestNum", joinGuestNum);
		mview.addObject("hostDto", hostDto);
		mview.addObject("preCheck", preCheck);
		mview.addObject("gCommentDto", gCommentDto);
		mview.addObject("canReserDto", canReserDto);
		mview.addObject("canCheck", canCheck);
		mview.addObject("mainDto", mainDto);
		mview.addObject("joinList", joinList);

		mview.setViewName("/reservation/reservationDetail");

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