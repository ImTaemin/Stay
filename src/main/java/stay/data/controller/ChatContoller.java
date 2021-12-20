package stay.data.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import stay.data.chat.Chat;
import stay.data.chat.ChatRepository;

@RequiredArgsConstructor
@RestController
public class ChatContoller {

	@Autowired
	private final ChatRepository chatRepository;
	
	@CrossOrigin//자바스크립트 요청 받음
	@GetMapping(value="/sender/{sender}/receiver/{receiver}", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Chat> getMsg(@PathVariable String sender, @PathVariable String receiver){
		
		return chatRepository.findBySender(sender, receiver).subscribeOn(Schedulers.boundedElastic());
	}
	
	@CrossOrigin//자바스크립트 요청 받음
	@PostMapping("/chat")
	public Mono<Chat> setMsg(@RequestBody Chat chat){ //반환값 void 가능 mono는 값 한개를 넣을때, 확인 할때
		chat.setDay(LocalDateTime.now());
		return chatRepository.save(chat);
	}
}
