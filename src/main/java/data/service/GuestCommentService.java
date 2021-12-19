package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.mapper.GuestCommentMapper;

@Service
public class GuestCommentService {
	@Autowired
	GuestCommentMapper mapper;
	
	public Float getRatingAvg() {
		return mapper.getRatingAvg();
	}
	
	public Integer totalComment() {
		return mapper.totalComment();
	}
}