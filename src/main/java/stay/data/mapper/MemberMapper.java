package stay.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.MemberDto;

@Mapper
public interface MemberMapper {
	
	public void insertMember(MemberDto dto);
	public List<MemberDto> getAllMembers();
	public int getIdCheck(String id);
	public int getCheckPass(HashMap<String, String> map);
	public MemberDto getMember(String id);
	public void updateMember(MemberDto dto);
	public void deleteMember(String num);
	
	public String getName(String id);
	public int login(HashMap<String, String> map);
	
	public void insertPhoto(MemberDto memberDto);

}
