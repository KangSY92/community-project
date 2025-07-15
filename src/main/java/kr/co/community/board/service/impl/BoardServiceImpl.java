package kr.co.community.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.mapper.BoardMapper;
import kr.co.community.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;

	public void create(BoardDTO boardDTO, String sessionID) {
		boardMapper.create(boardDTO);
	}

	public List<BoardDTO> getList() {
		return boardMapper.getList();
	}

}
