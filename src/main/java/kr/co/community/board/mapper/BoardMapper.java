package kr.co.community.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.BoardFileDTO;
import kr.co.community.board.dto.PageDTO;

@Mapper
public interface BoardMapper {

	void create(BoardDTO boardDTO);

	List<BoardDTO> getList(@Param("pi")PageDTO pi, @Param("board")BoardDTO boardDTO);

	int getTotalCount(BoardDTO boardDTO);

	int viewCount(int boardId);

	BoardDTO detail(int boardId);

	void delete(int boardId);

	void edit(@Param("board")BoardDTO boardDTO, @Param("boardId")int boardId);

	void fileUpload(@Param("board")BoardDTO boardDTO, @Param("boardFile")BoardFileDTO boardFileDTO, @Param("file")MultipartFile file);

}
