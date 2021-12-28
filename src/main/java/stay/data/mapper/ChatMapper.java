package stay.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stay.data.dto.ChatDto;

@Mapper
public interface ChatMapper {

	public List<ChatDto> getChattingRooms(String sender);
	public List<ChatDto> chatting(String sender, String receiver);
}
