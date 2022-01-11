package stay.data.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.MemberDto;
import stay.data.mapper.MemberMapper;
import stay.data.service.MemberService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@Autowired
	MemberMapper mapper;

	@Autowired
	MemberService memberService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/mypageform")
	public ModelAndView member1(HttpSession session) {
		ModelAndView mview = new ModelAndView();

		String myid = (String) session.getAttribute("myid");

		MemberDto memberDto = memberService.getMember(myid);

		memberDto.setId(myid);

		mview.addObject("memberDto", memberDto);

		mview.setViewName("/member/mypageForm");

		return mview;
	}

	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDto dto, @RequestParam MultipartFile upload, HttpSession session) {
		
		if(dto.getPass() != null) {
			//패스워드 암호화
			dto.setPass(passwordEncoder.encode(dto.getPass()));
		}
		
		// 업로드할 폴더 지정
		String path = session.getServletContext().getRealPath("/photo/memberPhoto");

		if (upload.getOriginalFilename().equals("")) {
			dto.setPhoto(null);
		} else {
			String fname = upload.getOriginalFilename();
			dto.setPhoto(fname);

			try {
				upload.transferTo(new File(path + "\\" + fname));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		memberService.updateMember(dto);

		// 메인으로
		return "redirect:/mypage/mypageform";
	}
}