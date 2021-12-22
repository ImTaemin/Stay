package stay.data.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/mypageform")
	public ModelAndView member1(HttpSession session) {
		ModelAndView mview = new ModelAndView();
		
		String myid = (String)session.getAttribute("myid");
		
		MemberDto memberDto = memberService.getMember(myid);
		
		memberDto.setId(myid);
		
		mview.addObject("memberDto", memberDto);
		
		mview.setViewName("/member/mypageForm");
		
		return mview;
	}
	
	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDto dto) {

		//update 호출
		mapper.updateMember(dto);

		//메인으로
		return "redirect:main";
	}
	
	
	// updatepassform
	/*
	 * @GetMapping("/updatepassform") public String updatePassForm(@RequestParam
	 * String id, Model model) { model.addAttribute("id",id); return
	 * "/member/updatepassform"; }
	 */

	// 비밀번호 체크 후 updateform or passfail
	/*
	 * @PostMapping("/updatepass") public String updatePass(@RequestParam String
	 * id, @RequestParam String pass) { // db로부터 비번맞나 체크 HashMap<String, String> map
	 * = new HashMap<String, String>(); map.put("id", id); map.put("pass", pass);
	 * 
	 * int check = mapper.getCheckPass(map);
	 * 
	 * if (check == 1) { // 1:비번이 맞는경우 return "redirect:updateform?id=" + id; // id에
	 * 해당하는 dto가져와야 하므로 } else { // 비번 틀린경우 return "/member/passfail"; } }
	 */
	
	/*
	 * @GetMapping("/updateform") public ModelAndView updateForm(@RequestParam
	 * String id) { ModelAndView mview=new ModelAndView();
	 * 
	 * //db로부터 id에 해당하는 dto열기 MemberDto dto=mapper.getMember(id);
	 * 
	 * dto.setE_mail(dto.getE_mail());
	 * 
	 * mview.addObject("dto", dto);
	 * 
	 * mview.setViewName("/member/updateform");
	 * 
	 * return mview; }
	 */
	
	//update
	/*
	 * @PostMapping("/update") public String memberUpdate(@ModelAttribute MemberDto
	 * dto) {
	 * 
	 * //email 형식으로 넣어주기 dto.setE_mail(dto.getE_mail());
	 * 
	 * //update 호출 mapper.updateMember(dto);
	 * 
	 * //profile로 return "redirect:profile"; }
	 */
	/*
	 * @GetMapping("/insertform") public String photoInsertForm() { return
	 * "/member/photoInsertForm"; }
	 */
	
	
	
}