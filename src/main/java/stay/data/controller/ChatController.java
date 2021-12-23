package stay.data.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	@GetMapping("/{sender}")
	public @ResponseBody List<ChatDto> getChattingRooms(@PathVariable("sender") String sender) {
		
		List<ChatDto> roomList = chatService.getChattingRooms(sender);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("roomList", roomList);
		mav.setViewName("/chat/chatting");
		
		return roomList;
	}
	
	//채팅
	@GetMapping("/{sender}/{receiver}")
	public @ResponseBody ChatDto chatting(@PathVariable("sender") String sender, @PathVariable("receiver") String receiver, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		
		
		PrintWriter writer = response.getWriter();
//		while(true) {
//			writer.write("data:{\"sender\""+ i +"\"}\n\n");
//		}
		writer.close();
		return new ChatDto();
	}

}
