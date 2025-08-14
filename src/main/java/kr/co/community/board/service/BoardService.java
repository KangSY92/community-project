package kr.co.community.board.service;

import kr.co.community.board.dto.BoardFileDTO;
import kr.co.community.board.dto.PageDTO;
import kr.co.community.board.dto.RequestBoardCreateDTO;
import kr.co.community.board.dto.RequestBoardDTO;
import kr.co.community.board.dto.RequestBoardDeleteDTO;
import kr.co.community.board.dto.RequestBoardEditDTO;
import kr.co.community.board.dto.ResponseBoardDetailDTO;
import kr.co.community.board.dto.ResponseListDTO;

public interface BoardService {

	void create(RequestBoardCreateDTO requestBoardCreateDTO);
	
	ResponseListDTO getList(PageDTO pi, RequestBoardDTO requestBoardDTO);
	
	ResponseBoardDetailDTO detail(int boardId);
	
	void delete(RequestBoardDeleteDTO requestBoardDeleteDTO, String sessionID);

	int getTotalCount(RequestBoardDTO requestBoardDTO);

	int viewCountplus(int boardId);

	BoardFileDTO fileInfo(int boardId);

	void fileDelete(int boardId);

	void edit(RequestBoardEditDTO requestBoardEditDTO);
}
