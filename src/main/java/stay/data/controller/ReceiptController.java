package stay.data.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.ResultMapDto;
import stay.data.service.CanReceiptService;
import stay.data.service.CostService;
import stay.data.service.JoinGuestService;
import stay.data.service.ReceiptService;
import stay.data.service.RoomService;

@Controller
public class ReceiptController {
	@Autowired
	ReceiptService receiptService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	JoinGuestService joinService;
	
	@Autowired
	CanReceiptService canreceiptService;
	
	@Autowired
	CostService costService;

	@PostMapping("/receipt/detail")
	public ModelAndView receiptDetail(@RequestParam String reserNo) throws Exception {
		ModelAndView mview = new ModelAndView();
		
		ResultMapDto receiptDto = receiptService.getDetailReceipt(reserNo);
		
		// 예약 번호
		receiptDto.getResDto().setNo(reserNo);
		
		// 날짜 split
		String startDate = receiptDto.getResDto().getStart_date();
		String start[] = startDate.split("-");

		String endDate = receiptDto.getResDto().getEnd_date();
		String end[] = endDate.split("-");

		// 요일 구하기
		String startDayWeek = getDateDay(startDate);
		String endDayWeek = getDateDay(endDate);
		
		// 공동 게스트
		int joinNum = joinService.countJoinGuest(receiptDto.getResDto().getNo()) + 1;
		
		// 취소 예약 확인
		int canCheck = canreceiptService.selectCancleCheck(reserNo);
		
		// 카드 이름
		if(receiptDto.getResDto().getPay_method().equals("card")) {
			String cardName = costService.getCardName(receiptDto.getResDto().getCard_num());
			
			mview.addObject("cardName", cardName);
			
			System.out.println(cardName);
		}
		
		mview.addObject("receiptDto", receiptDto);
		mview.addObject("start", start);
		mview.addObject("end", end);
		mview.addObject("startDayWeek", startDayWeek);
		mview.addObject("endDayWeek", endDayWeek);
		mview.addObject("joinNum", joinNum);
		mview.addObject("canCheck", canCheck);

		mview.setViewName("/receipt/receiptDetail");

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