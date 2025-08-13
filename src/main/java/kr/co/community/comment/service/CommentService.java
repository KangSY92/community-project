package kr.co.community.comment.service;

import java.util.List;

import kr.co.community.board.dto.PageDTO;
import kr.co.community.comment.dto.CommentDTO;
import kr.co.community.comment.dto.RequestCommentCreateDTO;
import kr.co.community.comment.dto.RequestCommetDeleteDTO;

public interface CommentService {

	void create(RequestCommentCreateDTO requestCommentCreateDTO);

	int commentCount(int boardId);

	List<CommentDTO> getList(int boardId, PageDTO pi);

	void delete(RequestCommetDeleteDTO requestCommetDeleteDTO);

	void commentEdit(int commentId, CommentDTO commentDTO);

	


}
