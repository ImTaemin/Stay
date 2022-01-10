package stay.data.service;


import java.util.HashMap;
import java.util.List;
import java.util.Random;


import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import stay.data.dto.MemberDto;
import stay.data.dto.ReportMemberDto;
import stay.data.mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	MemberMapper mapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
    // 아이디 중복 체크
    public int idCheck(String id) {
        return mapper.getIdCheck(id);
    }
    
    public MemberDto getMember(String id) {
    	return mapper.getMember(id);
    }
	
	public void insertPhoto(MemberDto memberDto) {
		mapper.insertPhoto(memberDto);
    }
	
	public void updateMember(MemberDto dto) {
		mapper.updateMember(dto);
	}
	
	public void updateImg(MemberDto memberDto) {
		mapper.updateImg(memberDto);
	}
	
	//아이디 찾기
	public String findId(String inputName, String inputHp) {
		
		return mapper.findId(inputName, inputHp);
	}
	
	public void insertSingoMem(ReportMemberDto rmDto) {
		mapper.insertSingoMem(rmDto);
	}
	
	public List<ReportMemberDto> getSingoMem(String black_id) {
		return mapper.getSingoMem(black_id);
	}
	
	//아이디 이메일 체크
//	public int checkIdEmail(String id, String e_mail) {
//		return mapper.checkIdEmail(id, e_mail);
//	}

	// 비밀번호
	public MemberDto checkIdEmail(MemberDto mdto) throws Exception{
		MemberDto dto = mapper.checkIdEmail(mdto.getId(), mdto.getE_mail());
		
		if(dto == null) {
			return dto;
		}else {
			//임시번호 생성 디비 업데이트 후 메일발송하는거
			//인증 번호 생성기
            StringBuffer temp =new StringBuffer();
            Random rnd = new Random();
            for(int i=0;i<10;i++)
            {
                int rIndex = rnd.nextInt(4);
                switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
                case 3:
                	//!,@,#,$,%
                	char[] sc={'!','@','#','$','%'};
                	Random rd=new Random();
                	int s=rd.nextInt(4);
                	temp.append(sc[s]);
                	break;
                }
            }
            
            String pass = temp.toString();
            mdto.setPass(passwordEncoder.encode(pass));
            
            // 암호화 한 비밀번호 삽입
            mapper.updatePw(mdto.getId(), mdto.getPass());
            
            pass = temp.toString();
            mdto.setPass(pass);
            
            //비밀번호 변경 메일 발송
            sendEmail(mdto);
		}
		
		return dto;
	}
	
	//임시비밀번호로 변경 DB업데이트
	public void updatePw(String id, String pass) throws Exception{
		mapper.updatePw(id, pass);
	}
	
	//메일 발송
	public void sendEmail(MemberDto mdto) throws Exception {
		
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "staySwim@gmail.com";
		String hostSMTPpwd = "stay1234!";
		
		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "staySwim@gmail.com";
		String fromName = "쉼,";
		String subject = "";
		String msg = "";
		
		subject = "쉼, 임시 비밀번호 입니다.";
		msg += "<style>@charset \"UTF-8\";\r\n"
				+ "@font-face {\r\n"
				+ "    font-family: 'WandocleanseaR';\r\n"
				+ "    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/WandocleanseaR.woff') format('woff');\r\n"
				+ "    font-weight: normal;\r\n"
				+ "    font-style: normal;\r\n"
				+ "}</style>";
		msg += "<div align='center' style='border:1px solid black; font-family: 'WandocleanseaR';'>";
		msg += "<h3 style='color: blue; font-family: 'WandocleanseaR';'>";
		msg += mdto.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
		msg += "<p style='font-family: 'WandocleanseaR';'>임시 비밀번호 : ";
		msg += mdto.getPass() + "</p></div>";
		
		// 받는 사람 E-Mail 주소
		String mail = mdto.getE_mail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);
			
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
	
	public List<ReportMemberDto> getReport(){
		return mapper.getReport();
	}
	
	public int checkSingo(String blackId, String reportId) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("black_id", blackId);
		map.put("report_id", reportId);
		
		return mapper.checkSingo(map);
	}
	
	public void updateRepMem(ReportMemberDto rmDto) {
		mapper.updateRepMem(rmDto);
	}
	
	public void updateRepCan(ReportMemberDto rmDto) {
		mapper.updateRepCan(rmDto);
	}
}