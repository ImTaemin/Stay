package stay.data.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import stay.data.dto.MemberDto;
import stay.data.mapper.MemberMapper;

@Controller
@RequestMapping("/member")
public class GaipController {
	
	@Autowired
	MemberMapper mapper;
	
	@GetMapping("/gaip")
	public String gaipForm() {
		
		return "/member/gaipForm";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<MemberDto> list=mapper.getAllMembers();
		
		model.addAttribute("count", list.size());
		model.addAttribute("list", list);
		
		return "/member/memberlist";
	}
	
	// 아이디 체크
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("id") String id){
        int cnt = mapper.getIdCheck(id);
        return cnt;
    }

	//insert
	@PostMapping("/insert")
	public String memberInsert(@ModelAttribute MemberDto dto) {

		//insert 호출
		mapper.insertMember(dto);

		//메인으로
		return "redirect:/member/login";
	}

	@GetMapping("/updateform")
	public ModelAndView updateForm(@RequestParam String id) {

		ModelAndView mv=new ModelAndView();

		//db로부터 dto얻기
		MemberDto dto=mapper.getMember(id);
		
		mv.addObject("dto", dto);

		mv.setViewName("/member/updateform");

		return mv;
	}

	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDto dto) {

		//update 호출
		mapper.updateMember(dto);

		//메인으로
		return "redirect:/";
	}

	@GetMapping("/delete")
	public @ResponseBody HashMap<String, Integer> delete(@RequestParam String myid,
														 @RequestParam String pass){
		//db로부터 비번맞는지 체크
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("myid",myid);
		map.put("pass",pass);

		int check=mapper.getCheckPass(map);

		if(check==1) {
			//비번이 맞는 경우
			mapper.deleteMember(myid);
		}

		HashMap<String, Integer> rmap=new HashMap<String, Integer>();
		rmap.put("check", check);

		return rmap;
	}

}
