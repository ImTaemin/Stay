package stay.data.controller;

import java.io.File;
import java.io.IOException;

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
	
	/*
	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDto dto,
			@RequestParam String id,
			HttpSession session,
			@RequestParam ArrayList<MultipartFile> upload) {

		String loginok = (String) session.getAttribute("loginok");
		if (loginok == null) {
			return "/member/login";
		}

		// 업로드할 폴더 지정
		String path = session.getServletContext().getRealPath("/photo/memberPhoto");
		System.out.println(path);
		String photo = "";

		if (upload.get(0).getOriginalFilename().equals("")) {
			photo = null;
		} else {
			String photos = memberService.getMember(id).getPhoto();
			String photoArray[] = photos.split(",");

			for (int i = 0; i < photoArray.length; i++) {
				File file = new File(path + "/" + photoArray[i]);
				file.delete();
			}

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

		// 엔터키 입력
		dto.setPhoto(photo);

		memberService.updateMember(dto);
		// update 호출
		mapper.updateMember(dto);

		// 메인으로
		return "redirect:/mypage/mypageform";
		
	
		
		
		 * String path = session.getServletContext().getRealPath("/photo/memberPhoto");
		 * System.out.println(path); if(upload.get(0).getOriginalFilename().equals(""))
		 * { photo = null; } else { String photo =
		 * memberService.getMember(id).getPhoto(); dto.setPhoto(photo);
		 * 
		 * try { f.transferTo(new File(path + "\\" + fName)); } catch
		 * (IllegalStateException | IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } service.updateBoard(dto); return
		 * "redirect:content?num="+dto.getNum()+"&currentPage="+currentPage;
		 
	}*/
	
	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDto dto,
			HttpSession session) {

		String loginok = (String) session.getAttribute("loginok");
		if (loginok == null) {
			return "/member/login";
		}
		
		String id = (String) session.getAttribute("id");
		dto.setId(id);
		
		String path = session.getServletContext().getRealPath("/photo/memberPhoto");
		if(dto.getUpload().getOriginalFilename().equals("")) {
			dto.setPhoto(null);
		} else {
			String photo = dto.getUpload().getOriginalFilename();
			dto.setPhoto(photo);
			
			try {
				dto.getUpload().transferTo(new File(path+"\\"+photo));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		memberService.updateMember(dto);
		// update 호출
	//	mapper.updateMember(dto);

		// 메인으로
		return "redirect:/mypage/mypageform";
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