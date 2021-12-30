package stay.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.MemberDto;
import stay.data.dto.ResultMapDto;

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
	public int login(HashMap<String, String> map);
	
	public void insertPhoto(MemberDto memberDto);
	
	public void updateImg(MemberDto dto);
	
	public MemberDto getData(String id);
	public void updatelikes(String id);
	
	public String findId(String e_mail);
	public void findPw(String e_mail);
	
	public List<ResultMapDto> getCommentMember(String roomNo);
}
