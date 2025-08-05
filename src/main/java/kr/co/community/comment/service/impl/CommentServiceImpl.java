package kr.co.community.comment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.community.board.dto.PageDTO;
import kr.co.community.comment.dto.CommentDTO;
import kr.co.community.comment.mapper.CommentMapper;
import kr.co.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

/**
 * 댓글 관련 비지니스 로직을 처리하는 서비스 구현 클래스 입니다.
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentMapper commentMapper;

	/**
	 * 특정 게시글에 댓글을 등록합니다
	 * 
	 * @param boardId 댓글이 달릴 게시글의 ID
	 * @param commentDTO 등록한 댓글 데이터가 담긴 DTO 객체
	 */
	@Override
	public void create(int boardId, CommentDTO commentDTO) {
		
		commentMapper.create(boardId, commentDTO);
	}

	/**
	 * 특정 게시글에 달린 댓글 목록을 조회합니다.
	 * 
	 * @param boardId 조회할 게시글의 ID
	 * @param pi 
	 * @return 해당 게시글에 달린 댓글 리스트
	 */
	@Override
	public List<CommentDTO> getList(int boardId, PageDTO pi) {
		return commentMapper.getList(boardId, pi);
	}

	/**
	 * 특정 게시글에 달린 댓글 수를 조회합니다.
	 * 
	 * @param boardId 댓글 수를 조회할 게시글의 ID
	 * @return 댓글 수
	 */
	@Override
	public int commentCount(int boardId) {
		return commentMapper.commentCount(boardId);
	}

	/**
	 * 댓글 ID를 기반으로 해당 댓글을 삭제합니다.
	 * 
	 * @param commentId 삭제할 댓글의 ID
	 */
	@Override
	public void delete(int commentId) {
		commentMapper.delete(commentId);
	}

	@Override
	public void commentEdit(int commentId, CommentDTO commentDTO) {
		commentMapper.commentEdit(commentId, commentDTO);
		
	}
	


}
