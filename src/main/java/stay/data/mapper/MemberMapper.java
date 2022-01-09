package stay.data.mapper;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.MemberDto;
import stay.data.dto.ReportMemberDto;

@Mapper
public interface MemberMapper {
	
	public void insertMember(MemberDto dto);
	public List<MemberDto> getAllMembers();
	public int getIdCheck(String id);
	public int getEmailCheck(String e_mail);
	public int getCheckPass(HashMap<String, String> map);
	public MemberDto getMember(String id);
	public void updateMember(MemberDto dto);
	public void deleteMember(String num);
	
	public String getName(String id);
	public MemberDto login(String id);
	
	public void insertPhoto(MemberDto memberDto);
	
	public void updateImg(MemberDto dto);
	
	public MemberDto getData(String id);
	public void updatelikes(String id);
	
	public String findId(String inputName, String inputHp);
	
	public void insertSingoMem(ReportMemberDto rmDto);
	public List<ReportMemberDto> getSingoMem(String black_id);
	
	//아이디 이메일 체크
//	public int checkIdEmail(String id, String e_mail);
	public MemberDto checkIdEmail(String id, String e_mail);
	
	// 비밀번호 변경
	public void updatePw(String id, String pass) throws Exception; 
	
	//이메일발송
  	public void sendEmail(MemberDto mdto) throws Exception;

  	//비밀번호찾기
  	public void findPw(MemberDto mdto) throws Exception;
	
  	public List<ReportMemberDto> getReport();
  	
  	public int checkSingo(HashMap<String, String> map);
  	
  	//신고관리
  	public void updateRepMem(ReportMemberDto rmDto);
  	public void updateRepCan(ReportMemberDto rmDto);
  	
}
