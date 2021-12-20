package data.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.dto.MemberDto;
import data.mapper.MemberMapper;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	MemberMapper mapper;
	
	@GetMapping("/mypageform")
	public String mypageForm() {
		return "/member/mypageForm";
	}

	// updatepassform
	@GetMapping("/updatepassform")
	public String updatePassForm(@RequestParam String num, Model model) {
		model.addAttribute("num", num);
		return "/member/updatepassform";
	}

	// 비밀번호 체크 후 updateform or passfail
	@PostMapping("/updatepass")
	public String updatePass(@RequestParam String num, @RequestParam String pass) {
		// db로부터 비번맞나 체크
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("num", num);
		map.put("pass", pass);

		int check = mapper.getCheckPass(map);

		if (check == 1) { // 1:비번이 맞는경우
			return "redirect:updateform?num=" + num; // num에 해당하는 dto가져와야 하므로
		} else { // 비번 틀린경우
			return "/member/passfail";
		}
	}
	
	@GetMapping("/updateform")
	public ModelAndView updateForm(@RequestParam String num) {
		ModelAndView mview=new ModelAndView();
		
		//db로부터 num에 해당하는 dto열기
		MemberDto dto=mapper.getMember(num);
		
		dto.setE_mail(dto.getE_mail());
		
		mview.addObject("dto", dto);
		
		mview.setViewName("/member/updateform");
		
		return mview;
	}
	
	//update
	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDto dto) {
		
		//email 형식으로 넣어주기
		dto.setE_mail(dto.getE_mail());
		
		//update 호출
		mapper.updateMember(dto);
			
		//list로 
		return "redirect:list";
	}
	
}