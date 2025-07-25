package kr.co.community.board.service;

import java.util.List;

import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.PageDTO;

public interface BoardService {

	void create(BoardDTO boardDTO);
	
	List<BoardDTO> getList(PageDTO pi);
	
	BoardDTO detail(int boardId);
	
	void delete(int boardId, String author, String sessionID);

	int getTotalCount(BoardDTO boardDTO);

	int viewCountplus(int boardId);

	void edit(BoardDTO boardDTO, int boardId);
}
