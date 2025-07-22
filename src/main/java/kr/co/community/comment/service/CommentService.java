package kr.co.community.comment.service;

import java.util.List;

import kr.co.community.comment.dto.CommentDTO;

public interface CommentService {

	void create(int boardId, CommentDTO commentDTO);

	List<CommentDTO> getList(int boardId);

	int commentCount(int boardId);


}
