package stay.data.repository;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import stay.data.dto.MemberDto;
import stay.data.mapper.MemberMapper;

public class MemberRepositoryImpl /* implements MemberRepository */{

	 @Autowired
	 private SqlSession sqlSession;
	 
	 @Autowired
	 private MemberMapper mapper;

	 private static final String NAMESPACE = "data.mapper.MemberMapper"; 

	    // 아이디 중복 체크
//	    @Override
//	    public int idCheck(String id) {
//	        int cnt = sqlSession.selectOne(NAMESPACE+".idCheck", id);
//	        return cnt;
//	    }
//	    
//	    //비밀번호 찾기 이메일발송
//	    @Override
//	    public void sendEmail(MemberDto mdto) throws Exception {
//	    	
//	    	// Mail Server 설정
//	    	String charSet = "utf-8";
//	    	String hostSMTP = "smtp.gmail.com";
//	    	String hostSMTPid = "staySwim@gmail.com";
//	    	String hostSMTPpwd = "stay1234!";
//
//	    	// 보내는 사람 EMail, 제목, 내용
//	    	String fromEmail = "staySwim@gmail.com";
//	    	String fromName = "쉼,";
//	    	String subject = "";
//	    	String msg = "";
//
//	    		subject = "쉼, 임시 비밀번호 입니다.";
//	    		msg += "<div align='center' style='border:1px solid black; font-family:WandocleanseaR'>";
//	    		msg += "<h3 style='color: blue;'>";
//	    		msg += mdto.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
//	    		msg += "<p>임시 비밀번호 : ";
//	    		msg += mdto.getPass() + "</p></div>";
//
//	    	// 받는 사람 E-Mail 주소
//	    	String mail = mdto.getE_mail();
//	    	try {
//	    		HtmlEmail email = new HtmlEmail();
//	    		email.setDebug(true);
//	    		email.setCharset(charSet);
//	    		email.setSSL(true);
//	    		email.setHostName(hostSMTP);
//	    		email.setSmtpPort(465);
//
//	    		email.setAuthentication(hostSMTPid, hostSMTPpwd);
//	    		email.setTLS(true);
//	    		email.addTo(mail, charSet);
//	    		email.setFrom(fromEmail, fromName, charSet);
//	    		email.setSubject(subject);
//	    		email.setHtmlMsg(msg);
//	    		email.send();
//	    	} catch (Exception e) {
//	    		System.out.println("메일발송 실패 : " + e);
//	    	}
//	    }

	    //비밀번호찾기
//	    @Override
//	    public void findPw(MemberDto mdto, HttpServletResponse response) throws Exception {
//	    	response.setContentType("text/html;charset=utf-8");
//	    	MemberDto ck = mapper.getMember(mdto.getId());
//	    	PrintWriter out = response.getWriter();
//	    	
//	    	// 가입된 아이디가 없으면
//	    	if(mapper.checkIdEmail(mdto.getId(),mdto.getE_mail())==0) {
//	    		out.print("<script>alert('회원정보가 존재하지 않습니다.');</script>");
//	    		out.flush();
//
//	    	} else {
//	    		//인증 번호 생성기
//                StringBuffer temp =new StringBuffer();
//                Random rnd = new Random();
//                for(int i=0;i<10;i++)
//                {
//                    int rIndex = rnd.nextInt(4);
//                    switch (rIndex) {
//                    case 0:
//                        // a-z
//                        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
//                        break;
//                    case 1:
//                        // A-Z
//                        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
//                        break;
//                    case 2:
//                        // 0-9
//                        temp.append((rnd.nextInt(10)));
//                        break;
//                    case 3:
//                    	//!,@,#,$,%
//                    	char[] sc={'!','@','#','$','%'};
//                    	Random rd=new Random();
//                    	int s=rd.nextInt(4);
//                    	temp.append(sc[s]);
//                    	break;
//                    }
//                    String pw = temp.toString();
//                    //mdto.setPass(pw);
//                    System.out.println(pw);
//                    // 비밀번호 변경
//                    mapper.updatePw(mdto.getId(), pw);
//                    // 비밀번호 변경 메일 발송
//                    sendEmail(mdto);
//                }
//                
//
//	    		out.print("<script>alert('이메일로 임시 비밀번호를 발송하였습니다.');</script>");
//	    		out.flush();
//
//	    	}
//	  }
}

