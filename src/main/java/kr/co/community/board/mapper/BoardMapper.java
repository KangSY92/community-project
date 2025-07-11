package kr.co.community.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.community.board.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	void create(BoardDTO boardDTO);

}
