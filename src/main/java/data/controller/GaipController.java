package data.controller;

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

import data.dto.MemberDto;
import data.mapper.MemberMapper;

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
	
	/*
	 * //id체크
	 * 
	 * @GetMapping("/idCheck")
	 * 
	 * @ResponseBody //json으로 반환되려면 반드시 Map타입이어야하고 @ResponseBody 써줘야 함 public
	 * Map<String, Integer> idCheckProc(@RequestParam String id){
	 * 
	 * int check=mapper.getIdCheck(id);
	 * 
	 * System.out.println(check);
	 * 
	 * Map<String, Integer> map=new HashMap<String, Integer>(); map.put("check",
	 * check); //결과값 0(사용가능) 또는 1(사용중)
	 * 
	 * return map; }
	 */
	
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

		//주소 형식으로 넣어주기
		dto.setAddr(dto.getAddr1()+" "+dto.getAddr2());

		//insert 호출
		mapper.insertMember(dto);

		//리스트로
		return "redirect:list";
	}

	/*
	 * //updatepassform으로 포워드
	 * 
	 * @GetMapping("/updatepassform") public String updatePassForm(@RequestParam
	 * String num, Model model) {
	 * 
	 * model.addAttribute("num", num);
	 * 
	 * return "/member/updatepassform"; }
	 */

	/*
	 * //비밀번호 체크 후 updateform 또는 passfail로
	 * 
	 * @PostMapping("/updatepass") public String updatePass(@RequestParam
	 * String num,
	 * 
	 * @RequestParam String pass) {
	 * 
	 * //db로부터 비번맞는지 체크 HashMap<String, String> map=new HashMap<String, String>();
	 * map.put("num", num); map.put("pass", pass);
	 * 
	 * int check=mapper.getCheckPass(map);
	 * 
	 * if(check==1) { //비번이 맞는 경우 return "redirect:updateform?num="+num; //num에 해당하는
	 * dto가져와야하므로 } else { return "/member/passfail"; } }
	 */

	@GetMapping("/updateform")
	public ModelAndView updateForm(@RequestParam String id) {

		ModelAndView mv=new ModelAndView();

		//db로부터 dto얻기
		MemberDto dto=mapper.getMember(id);
		
		//이메일 분리한 후 다시 dto에 담기
		String [] ad=dto.getAddr().split(" ");
		dto.setAddr1(ad[0]);
		dto.setAddr2(ad[1]);

		mv.addObject("dto", dto);

		mv.setViewName("/member/updateform");

		return mv;
	}

	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDto dto) {

		//update 호출
		mapper.updateMember(dto);

		//리스트로
		return "redirect:list";
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
