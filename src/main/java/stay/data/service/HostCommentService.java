package stay.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stay.data.dto.HostCommentDto;
import stay.data.mapper.HostCommentMapper;

@Service
public class HostCommentService {
	@Autowired
	HostCommentMapper mapper;
	
	public HostCommentDto getOneHostComment(String no) {
		return mapper.getOneHostComment(no);
	}
	
	public void insertHostComment(HostCommentDto dto) {
		mapper.insertHostComment(dto);
	}
}
