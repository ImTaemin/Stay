package data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import data.mapper.MemberMapper;
import data.service.MemberService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	MemberMapper mapper;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/profileform")
	public String profileForm() {
		return "/member/profileForm";
	}
	
}