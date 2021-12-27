package stay.data.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import stay.data.dto.ChatDto;
import stay.data.mapper.ChatMapper;

@Service
public class ChatService {

	@Autowired
	ChatMapper chatMapper;

	private final Map<ResponseBodyEmitter, List<ChatDto>> emitterChatMap = new HashMap<>();
	ArrayList<ChatDto> historyChat = new ArrayList<ChatDto>();
	
	@Scheduled(fixedRate = 1000L)
	public void emit() {
		
		for(Map.Entry<ResponseBodyEmitter, List<ChatDto>> entry : emitterChatMap.entrySet()) {
//			ChatDto dto = new RestTemplate().getForObject("http://localhost:8080/chat/{sender}", ChatDto.class, sender);

			ResponseBodyEmitter emitter = entry.getKey(); // emmiter|List<ChatDto>
			
			try {
				for(ChatDto chatdto : entry.getValue()) {
					if(historyChat.contains(chatdto)) {
						//채팅이 있으면 채팅내역 전송X
					}else {
						//채팅이 없으면 채팅내역 전송O 
						historyChat.add(chatdto);
						emitter.send(chatdto);
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	public void add(ResponseBodyEmitter emitter, String sender) {
		emitterChatMap.put(emitter, chatMapper.getChattingRooms(sender));
	}
	
	//채팅방
	public List<ChatDto> getChattingRooms(String sender){
		return chatMapper.getChattingRooms(sender); //List<ChatDto> 반환
	}
	
	//채팅
	public List<ChatDto> chatting(String sender, String receiver){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sender", sender);
		map.put("receiver", receiver);
		
		return chatMapper.chatting(sender, receiver);
	}

}
