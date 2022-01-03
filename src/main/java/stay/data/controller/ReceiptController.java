package stay.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.ResultMapDto;
import stay.data.service.ReceiptService;

@Controller
public class ReceiptController {
	@Autowired
	ReceiptService receiptService;
	
	// 영수증
	@GetMapping("/receipt/detail")
	public ModelAndView receiptDetail() {
		ModelAndView mview = new ModelAndView();
		
		ResultMapDto receiptDto = receiptService.getDetailReceipt("21122700008");
		
		mview.addObject("receiptDto", receiptDto);
		
		mview.setViewName("/receipt/receiptDetail");
		
		return mview;
	}
	
	// 취소 영수증
}