package stay.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import stay.data.dto.ChatDto;
import stay.data.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	ChatService chatService;
	
	@GetMapping("")
	public String chat() {
		return "/chat/chatting";
	}
	
	//채팅방 목록
	@GetMapping(path="/chatting/{sender}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public @ResponseBody ResponseBodyEmitter getChattingRooms(@PathVariable String sender){
		SseEmitter emitter = new SseEmitter();
		chatService.roomAdd(emitter, sender);
		
		return emitter;
	}
	
	//채팅방별 채팅
	@GetMapping(path="/chatting/{sender}/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public @ResponseBody ResponseBodyEmitter chatting(@PathVariable("sender") String sender, @PathVariable("receiver") String receiver) {
		SseEmitter emitter = new SseEmitter();
		chatService.chatAdd(emitter, sender, receiver);
		
		return emitter;
	}

	//채팅방 내역
	@PostMapping("/recorded")
	public @ResponseBody List<ChatDto> getInitChattingRooms(@RequestParam String sender){
		return chatService.getInitChattingRooms(sender);
	}
	
	//원래 채팅기록(채팅별 전체 데이터)
	@PostMapping("/chatting")
	public @ResponseBody List<ChatDto> getInitChatting(@RequestParam String sender, @RequestParam String receiver) {
		return chatService.getInitChatting(sender, receiver);
	}
	
	@PostMapping("/chatting/insert")
	public void insertChat(@RequestBody ChatDto chatDto) {
		chatService.insertChat(chatDto);
	}
}
