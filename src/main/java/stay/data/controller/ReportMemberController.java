package stay.data.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.ReportMemberDto;
import stay.data.service.MemberService;

@Controller
public class ReportMemberController {
	@Autowired
	MemberService memberService;

	@GetMapping("/report")
	public ModelAndView report(HttpSession session) {
		ModelAndView mview = new ModelAndView();

		List<ReportMemberDto> reportList = memberService.getReport();

		mview.addObject("reportList", reportList);

		mview.setViewName("/member/memberlist");

		return mview;
	}
}