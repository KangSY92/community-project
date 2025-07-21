package kr.co.community.comment.service;

import kr.co.community.comment.dto.CommentDTO;

public interface CommentService {

	void create(int boardId, CommentDTO commentDTO);


}
