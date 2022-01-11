package stay.data.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import stay.data.service.KakaoLogin;
import stay.data.service.MemberService;

@Controller
@RequestMapping("/member")
public class LoginController {
	@Autowired
	MemberMapper mapper;

	@Autowired
	MemberService service;

	@Autowired
	private KakaoLogin kakao;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String loginForm(HttpSession session, Model model) {
		// 아이디 얻어오기
		String myid = (String) session.getAttribute("myid");

		// 로그인 상태인지 확인
		String loginok = (String) session.getAttribute("loginok");

		if (loginok == null) {
			return "/member/loginForm";

		} else {
			// 로그인중일때는 로그인한 이름 저장
			String name = mapper.getName(myid);

			model.addAttribute("name", name);

			return "redirect:/";
		}
	}

	@PostMapping("/loginprocess")
	public String loginProcss(@RequestParam(required = false) String cbsave, @RequestParam String id,
			@RequestParam String pass, HttpSession session) {
		
		MemberDto check = mapper.login(id);
		
		if(!id.equals(check.getId())) {
			return "/member/passfail";
		}
		
		if(passwordEncoder.matches(pass, check.getPass()) && id.equals("admin")) {
			session.setAttribute("loginok", "yes");
			session.setAttribute("myid", id);
			session.setAttribute("mode", "admin");
			return "redirect:/report";
		}

		if (passwordEncoder.matches(pass, check.getPass())) {
			session.setAttribute("myid", id);
			session.setAttribute("loginok", "yes");
			session.setAttribute("saveok", cbsave);
			session.setAttribute("mode", "guest");

			// 체크했을때 on, 안하면 null
			return "redirect:/";
		} else {
			return "/member/passfail";
		}
	}

	@RequestMapping("/kakaologin")
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) throws ParseException {
		String access_Token = kakao.getAccessToken(code);

		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		//System.out.println("login Controller : " + userInfo);

		String name = (String) userInfo.get("nickname");
		String birthday = (String) userInfo.get("birthday");
		String photo = (String) userInfo.get("profile_image");
		String e_mail = (String) userInfo.get("email");

		Calendar date = Calendar.getInstance();
		int nowyear = date.get(Calendar.YEAR);

		String birth = Integer.toString(nowyear) + birthday;

		// 현재 날짜의 타입
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		// Date로 파싱
		Date processDate = dateFormat.parse(birth);

		// 변경할 타입으로의 형 변환
		String realbirthday = new SimpleDateFormat("yyyy-MM-dd").format(processDate);

		if (mapper.getEmailCheck(e_mail) == 0) {
			MemberDto mdto = new MemberDto();
			mdto.setId(e_mail);
			mdto.setName(name);
			mdto.setBirth(realbirthday);
			mdto.setPhoto(photo);
			mdto.setE_mail(e_mail);
			mapper.insertMember(mdto);
		}

		session.setAttribute("myid", e_mail);
		session.setAttribute("kakaoName", name);
		session.setAttribute("loginok", "yes");
		session.setAttribute("mode", "guest");
		session.setAttribute("kakaologin", "yes");
		session.setAttribute("profile", photo);
		session.setAttribute("access_Token", access_Token);

		return "redirect:/";
	}

	@RequestMapping("/kakaologout")
	public String kakaologout(HttpSession session) {
		if (session.getAttribute("kakaologin") == null) {
			session.removeAttribute("myid");
			session.removeAttribute("loginok");
			session.removeAttribute("mode");
			return "/member/loginForm";
		}

		kakao.kakaoLogout((String) session.getAttribute("access_Token"));
		session.removeAttribute("myid");
		session.removeAttribute("access_Token");
		session.removeAttribute("userId");
		session.removeAttribute("kakaologin");
		session.removeAttribute("kakaoName");
		session.removeAttribute("profile");
		session.removeAttribute("mode");
		session.removeAttribute("loginok");

		return "redirect:/";
	}

	// 아이디 찾기
	@GetMapping("/findId")
	public String findIdForm() throws Exception {
		return "/member/findIdForm";
	}

	@PostMapping("/findIdprocess")
	public @ResponseBody HashMap<String, String> findId(@RequestParam(value = "name", required = false) String inputName,
			@RequestParam(value = "hp", required = false) String inputHp) {
		String result = service.findId(inputName, inputHp);

		HashMap<String, String> m = new HashMap<String, String>();
		m.put("id", result);

		return m;
	}

	// 비밀번호 찾기
	@GetMapping("/findPw")
	public ModelAndView findPwGET(@RequestParam(required =  false) String userId) throws Exception {
		ModelAndView mview = new ModelAndView();
		
		mview.addObject("userId", userId);
		
		mview.setViewName("/member/findPwForm");
		
		return mview;
	}

	@PostMapping("/findPw")
	@ResponseBody
	public String findPwPOST(@ModelAttribute MemberDto mdto) throws Exception {
		MemberDto dto = service.checkIdEmail(mdto);

		if (dto == null) {
			return "일치하는 회원정보가 없습니다.";
		} else {
			return "임시 비밀번호를 메일로 발송했습니다.";
		}
	}

}
