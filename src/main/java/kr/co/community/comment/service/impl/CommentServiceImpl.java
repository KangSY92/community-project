package kr.co.community.comment.service.impl;

import org.springframework.stereotype.Service;

import kr.co.community.comment.dto.CommentDTO;
import kr.co.community.comment.mapper.CommentMapper;
import kr.co.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentMapper commentMapper;

	@Override
	public void create(int boardId, CommentDTO commentDTO) {
		commentMapper.create(boardId, commentDTO);
	}
	


}
