package kr.co.community.comment.service;

import java.util.List;

import kr.co.community.board.dto.PageDTO;
import kr.co.community.comment.dto.CommentDTO;

public interface CommentService {

	void create(int boardId, CommentDTO commentDTO);

	int commentCount(int boardId);

	List<CommentDTO> getList(int boardId, PageDTO pi);

	void delete(int commentId);

	void commentEdit(int commentId, CommentDTO commentDTO);

	


}
