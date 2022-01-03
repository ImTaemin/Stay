package stay.data.repository;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import stay.data.dto.MemberDto;
import stay.data.mapper.MemberMapper;

public class MemberRepositoryImpl implements MemberRepository {

	 @Autowired
	    private SqlSession sqlSession;
	 
	 @Autowired
	 private MemberMapper mapper;

	    private static final String NAMESPACE = "data.mapper.MemberMapper"; 

	    // 아이디 중복 체크
	    @Override
	    public int idCheck(String id) {
	        int cnt = sqlSession.selectOne(NAMESPACE+".idCheck", id);
	        return cnt;
	    }
	    
	    //비밀번호 찾기 이메일발송
	    @Override
	    public void sendEmail(MemberDto mdto, String div) throws Exception {
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

	    	if(div.equals("findpw")) {
	    		subject = "쉼, 임시 비밀번호 입니다.";
	    		msg += "<div align='center' style='border:1px solid black; font-family:WandocleanseaR'>";
	    		msg += "<h3 style='color: blue;'>";
	    		msg += mdto.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
	    		msg += "<p>임시 비밀번호 : ";
	    		msg += mdto.getPass() + "</p></div>";
	    	}

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

	    //비밀번호찾기
	    @Override
	    public void findPw(MemberDto mdto, HttpServletResponse response) throws Exception {
	    	response.setContentType("text/html;charset=utf-8");
	    	MemberDto ck = mapper.getMember(mdto.getId());
	    	PrintWriter out = response.getWriter();
	    	// 가입된 아이디가 없으면
	    	if(mapper.getIdCheck(mdto.getId()) == 0) {
	    		out.print("등록되지 않은 아이디입니다.");
	    		out.close();
	    	}
	    	// 가입된 이메일이 아니면
	    	else if(!mdto.getE_mail().equals(ck.getE_mail())) {
	    		out.print("등록되지 않은 이메일입니다.");
	    		out.close();
	    	}else {
	    		// 임시 비밀번호 생성
	    		String pw = "";
	    		for (int i = 0; i < 12; i++) {
	    			pw += (char) ((Math.random() * 26) + 97);
	    		}
	    		mdto.setPass(pw);
	    		// 비밀번호 변경
	    		mapper.updatePw(mdto);
	    		// 비밀번호 변경 메일 발송
	    		sendEmail(mdto, "findpw");

	    		out.print("이메일로 임시 비밀번호를 발송하였습니다.");
	    		out.close();
	    	}
	    }
}
