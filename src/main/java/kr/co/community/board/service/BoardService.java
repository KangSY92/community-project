package kr.co.community.board.service;

import java.util.List;

import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.PageDTO;

public interface BoardService {

	void create(BoardDTO boardDTO, String sessionID);
	
	List<BoardDTO> getList(PageDTO pi);
}
