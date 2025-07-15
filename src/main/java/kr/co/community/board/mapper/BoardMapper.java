package kr.co.community.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.community.board.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	void create(BoardDTO boardDTO);

	List<BoardDTO> getList();

}
