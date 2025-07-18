package kr.co.community.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.PageDTO;
import kr.co.community.board.mapper.BoardMapper;
import kr.co.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;

/**
 * BoardService 인터페이스의 구현체로, 게시판 관련 비지니스 로직을 담당합니다. 게시글 작성, 조회, 삭제 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	/** 게시판 데이트베이스 처리를 위한 매퍼 객체 */
	private final BoardMapper boardMapper;

	/**
	 * 게시글을 등록합니다.
	 * 
	 * @param boardDTO  새로 작성할 게시글 데이터
	 * @param sessionID 현재 로그인한 사용자 세션 ID
	 */
	public void create(BoardDTO boardDTO, String sessionID) {
		boardMapper.create(boardDTO);
	}

	/**
	 * 페이징 정보에 맞는 게시글 목록을 조회합니다.
	 * 
	 * @param pi 페이지 및 페이징 관련 정보가 담긴 DTO
	 * @return 게시글 리스트
	 */
	public List<BoardDTO> getList(PageDTO pi) {
		return boardMapper.getList(pi);
	}

	/**
	 * 게시글의 전체 개수를 조회합니다.
	 * 
	 * @param boardDTO 검색 조건 등이 포함될 수 있는 게시글 DTO
	 * @return 전체 게시글 수
	 */
	public int getTotalCount(BoardDTO boardDTO) {
		return boardMapper.getTotalCount(boardDTO);
	}

	/**
	 * 게시글 상세정보를 조회합니다.
	 * 
	 * @param boardId 상세 조회할 게시글 ID
	 * @return 게시글 상세 데이터
	 */
	public BoardDTO detail(int boardId) {
		return boardMapper.detail(boardId);
	}

	/**
	 * 게시글의 조회수를 1 증가시킵니다.
	 * 
	 * @param boardId 조회수 증각 대상 게시글 ID
	 * @return 증가 처리된 결과값
	 */
	public int viewCountplus(int boardId) {
		return boardMapper.viewCount(boardId);
	}

	/**
	 * 게시글을 삭제합니다.
	 * 
	 * 작성자와 로그인 사용자가 일차할 경우에만 삭제합니다.
	 */
	public void delete(int boardId, String author, String sessionid) {
		if (author.equals(sessionid)) {
			boardMapper.delete(boardId);
		}

	}

	/**
	 * 게시글을 수정합니다.
	 * 
	 * @param boardDTO 수정할 게시글 데이터
	 * @param boardId 수정 대상 게시글 ID
	 */
	public void edit(BoardDTO boardDTO, int boardId) {
		boardMapper.edit(boardDTO, boardId);
	}

}
