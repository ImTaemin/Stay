package stay.data.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import stay.data.dto.MemberDto;
import stay.data.mapper.MemberMapper;
import stay.data.service.KakaoLogin;

@Controller
@RequestMapping("/member")
public class LoginController {

	@Autowired
	MemberMapper mapper;
	
	@Autowired
	private KakaoLogin kakao;
	

	@GetMapping("/login")
	public String loginForm(HttpSession session, 
							Model model
							//, 
							//HttpServletRequest request
							) {
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
			
			//이전페이지...되는지 확인할것 
			//String referer = request.getHeader("Referer"); 
			//return "redirect:"+ referer;
			
			return "redirect:/";
		}

	}

	@PostMapping("/loginprocess")
	public String loginProcss(@RequestParam(required = false) String cbsave, @RequestParam String id,
			@RequestParam String pass, HttpSession session) {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("id", id);
		map.put("pass", pass);

		int check = mapper.login(map);

		if (check == 1) {
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
		
		//System.out.println("code : " + code);
		
		String access_Token = kakao.getAccessToken(code);
        //System.out.println("controller access_token : " + access_Token);
        
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);

        String name=(String)userInfo.get("nickname");
        String birthday=(String)userInfo.get("birthday");
        String photo=(String)userInfo.get("profile_image");
        String e_mail=(String)userInfo.get("email");
        
        Calendar date=Calendar.getInstance();
        int nowyear=date.get(Calendar.YEAR);
        
        String birth=Integer.toString(nowyear)+birthday;
        
        //현재 날짜의 타입 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //Date로 파싱
        Date processDate = dateFormat.parse(birth);
        //변경할 타입으로의 형 변환
        String realbirthday = new SimpleDateFormat("yyyy-MM-dd").format(processDate);
        
        
        if(mapper.getEmailCheck(e_mail)==0) {
        	MemberDto mdto=new MemberDto();
        	mdto.setId(e_mail);
        	mdto.setName(name);
        	mdto.setBirth(realbirthday);
        	mdto.setPhoto(photo);
        	mdto.setE_mail(e_mail);
        	mapper.insertMember(mdto);
        }
        
        session.setAttribute("myid", name);
        session.setAttribute("loginok", "yes");
        session.setAttribute("mode", "guest");
        session.setAttribute("kakaologin", "yes");
        session.setAttribute("profile", photo);
        
        //클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
        }
        
        return "/member/loginForm";
		
	}
	
	@RequestMapping("/kakaologout")
	public String kakaologout(HttpSession session) {
	    kakao.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "/member/loginForm";
	}

	@GetMapping("/logoutprocess")
	public String logout(HttpSession session) {

		session.removeAttribute("loginok");

		return "redirect:/";
	}

}
