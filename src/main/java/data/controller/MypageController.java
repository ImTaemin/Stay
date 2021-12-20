package data.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.dto.MemberDto;
import data.dto.RoomDto;
import data.mapper.MemberMapper;
import data.service.MemberService;

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
			
		//profile로 
		return "redirect:profile";
	}
	
	/*
	 * @GetMapping("/insertform") public String photoInsertForm() { return
	 * "/member/photoInsertForm"; }
	 */

	@PostMapping("/photoinsert")
	public String roomInsert(@ModelAttribute MemberDto memberDto, @RequestParam ArrayList<MultipartFile> upload,
			HttpSession session) {
		// 업로드할 폴더 지정
		String path = session.getServletContext().getRealPath("/photo");
		System.out.println(path);

		String photo = "";

		if (upload.get(0).getOriginalFilename().equals("")) {
			photo = "no";
		} else {
			for (MultipartFile f : upload) {
				String fName = f.getOriginalFilename();
				photo += fName + ",";

				// 업로드
				try {
					f.transferTo(new File(path + "\\" + fName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			photo = photo.substring(0, photo.length() - 1);
		}

		// 추후 로그인 아이디 값으로 변경
		String myid = "stay";

		memberDto.setId(myid);
		memberDto.setPhoto(photo);

		MemberService.insertPhoto(memberDto);

		return "redirect:main";
	}
	
}