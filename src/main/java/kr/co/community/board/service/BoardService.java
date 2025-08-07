package kr.co.community.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.BoardFileDTO;
import kr.co.community.board.dto.PageDTO;
import kr.co.community.board.dto.RequestBoardDTO;
import kr.co.community.board.dto.ResponseListDTO;

public interface BoardService {

	void create(BoardDTO boardDTO, BoardFileDTO boardFileDTO, MultipartFile file);
	
	ResponseListDTO getList(PageDTO pi, RequestBoardDTO requestBoardDTO);
	
	BoardDTO detail(int boardId);
	
	void delete(int boardId, String author, String sessionID);

	int getTotalCount(RequestBoardDTO requestBoardDTO);

	int viewCountplus(int boardId);

	void edit(BoardDTO boardDTO, int boardId, BoardFileDTO boardFileDTO, MultipartFile file);

	BoardFileDTO fileInfo(int boardId);

	void fileDelete(int boardId);
}
