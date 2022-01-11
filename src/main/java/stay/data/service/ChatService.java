package stay.data.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import stay.data.dto.ChatDto;
import stay.data.mapper.ChatMapper;

@Service
public class ChatService {

	@Autowired
	ChatMapper chatMapper;

//	private final Map<ResponseBodyEmitter, List<ChatDto>> emitterRoomMap = new HashMap<>();
	private final Map<ResponseBodyEmitter, String> emitterRoomMap = new HashMap<>();
	private final Map<ResponseBodyEmitter, HashMap<String, String>> emitterChatMap = new HashMap<>(); //(emmiter, (sender,receiver))
	
	ArrayList<ChatDto> historyRoom = new ArrayList<ChatDto>();
	ArrayList<ChatDto> historyChat = new ArrayList<ChatDto>();

	ResponseBodyEmitter emitterRoom, emitterChat;
	
	//room폴링
	@Scheduled(fixedRate = 1000L)
	public void emitRoom() {
		
		for(Map.Entry<ResponseBodyEmitter, String> entry : emitterRoomMap.entrySet()) {

			emitterRoom = entry.getKey();
			
			try {
				for(ChatDto chatDto : chatMapper.getChattingRooms(entry.getValue())) {
					if(historyRoom.contains(chatDto)) {
						//채팅방이 있으면 채팅방 전송X
					}else {
//						채팅방이 없으면 채팅방 전송O 
						historyRoom.add(chatDto);
						emitterRoom.send(chatDto);
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	//chat폴링
	@Scheduled(fixedRate = 1000L)
	public void emitChat() {
		for(Map.Entry<ResponseBodyEmitter, HashMap<String, String>> entry : emitterChatMap.entrySet()) { //Map<emitter, HashMap<String,String>>
//			ChatDto dto = new RestTemplate().getForObject("http://localhost:8080/chat/{sender}", ChatDto.class, sender);
			
			emitterChat = entry.getKey(); // emmiter,HashMap(sender,receiver);
			
			try {
				for(Map.Entry<String, String> chatMap : entry.getValue().entrySet()) { // HashMap<String,String>
					for(ChatDto chatDto : chatMapper.chatting(chatMap.getKey(), chatMap.getValue())) {
						
						if(historyChat.contains(chatDto)) {
							//채팅이 있으면 채팅내역 전송X
						}else {
//							채팅이 없으면 채팅내역 전송O 
							historyChat.add(chatDto);
							emitterChat.send(chatDto);
						}
					}

				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	//채팅방 MapEmitter
	public void roomAdd(ResponseBodyEmitter emitter, String sender) {
		emitterRoomMap.put(emitter, sender);
	}
	
	//채팅 MapEmitter
	public void chatAdd(ResponseBodyEmitter emitter, String sender, String receiver) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(sender, receiver);
		
		emitterChatMap.put(emitter, map);
	}
	
	public synchronized List<ChatDto> getInitChattingRooms(String sender){
		List<ChatDto> dtos = chatMapper.getChattingRooms(sender);
		for(ChatDto dto : dtos) {
			if(!historyRoom.contains(dto)) {
				historyRoom.add(dto);
			}
		}
		return dtos;
	}
	
	public synchronized List<ChatDto> getInitChatting(String sender, String receiver) {
		List<ChatDto> dtos = chatMapper.chatting(sender, receiver);
		for(ChatDto dto : dtos) {
			if(!historyChat.contains(dto)) {
				historyChat.add(dto);
			}
		}
		return dtos;
	}
	
	public void insertChat(ChatDto dto) {
		chatMapper.insertChat(dto);
	}

}
