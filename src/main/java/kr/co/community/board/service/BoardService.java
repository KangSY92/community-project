package kr.co.community.board.service;

import java.util.List;

import kr.co.community.board.dto.BoardDTO;

public interface BoardService {

	void create(BoardDTO boardDTO, String sessionID);
	
	List<BoardDTO> getList();
}
