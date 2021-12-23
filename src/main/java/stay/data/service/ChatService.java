package stay.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.ChatDto;
import stay.data.mapper.ChatMapper;

@Service
public class ChatService {

	@Autowired
	ChatMapper chatMapper;
	
	public List<ChatDto> getChattingRooms(String sender){
		return chatMapper.getChattingRooms(sender);
	}
	
	public List<ChatDto> chatting(String sender, String receiver){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sender", sender);
		map.put("receiver", receiver);
		
		return chatMapper.chatting(sender, receiver);
	}

}
