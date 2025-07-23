package kr.co.community.comment.service;

import java.util.List;

import kr.co.community.board.dto.PageDTO;
import kr.co.community.comment.dto.CommentDTO;

public interface CommentService {

	void create(int boardId, CommentDTO commentDTO);

	int commentCount(int boardId);

	/**
	 * 특정 게시글에 달린 댓글 목록을 조회합니다.
	 * 
	 * @param boardId 조회할 게시글의 ID
	 * @param pi 
	 * @return 해당 게시글에 달린 댓글 리스트
	 */
	List<CommentDTO> getList(int boardId, PageDTO pi);

	/**
	 * 특정 게시글에 댓글을 등록합니다
	 * 
	 * @param boardId 댓글이 달릴 게시글의 ID
	 * @param commentDTO 등록한 댓글 데이터가 담긴 DTO 객체
	 */


}
