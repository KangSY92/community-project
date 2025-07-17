package kr.co.community.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.PageDTO;
import kr.co.community.board.mapper.BoardMapper;
import kr.co.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;

/**
 * BoardService 인터페이스의 구현체로,
 * 게시판 관련 비지니스 로직을 담당합니다.
 * 게시글 작성, 조회, 삭제 기능을 제공합니다. 
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	/** 게시판 데이트베이스 처리를 위한 매퍼 객체 */
	private final BoardMapper boardMapper;

	/**
	 * 게시글을 등록합니다.
	 * 
	 * @param boardDTO 새로 작성할 게시글 데이터
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
	 * 조회수 증가 처리 후, 게시글 데이터를 반환합니다.
	 * 
	 * @param boardId 상세 조회할 게시글 ID
	 * @return 게시글 상세 데이터 (조회수 증가 실패 시 null)
	 */
	public BoardDTO detail(int boardId) {
		int viewCountResult = boardMapper.viewCount(boardId);
		
		if(viewCountResult == 1) {
			return boardMapper.detail(boardId);
		} else {
			return null;
		}
	}

	/**
	 * 게시글을 삭제합니다.
	 * 
	 * 작성자와 로그인 사용자가 일차할 경우에만 삭제합니다.
	 */
	public void delete(int boardId, String author, String sessionID) {
		if(author.equals(sessionID)) {
			boardMapper.delete(boardId);
		}
		
	}

}
