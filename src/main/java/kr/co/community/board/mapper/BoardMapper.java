package kr.co.community.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import kr.co.community.board.domain.Board;
import kr.co.community.board.domain.BoardFile;
import kr.co.community.board.dto.BoardDTO;
import kr.co.community.board.dto.PageDTO;

@Mapper
public interface BoardMapper {

	void create(Board board);

	List<BoardDTO> getList(@Param("pi")PageDTO pi, @Param("board")Board board);

	int getTotalCount(Board board);

	int viewCount(int boardId);

	BoardDTO detail(int boardId);

	void delete(int boardId);

	void edit(Board board);

	void fileUpload(@Param("board")Board board, @Param("boardFile")BoardFile boardFile, @Param("file")MultipartFile file);

	BoardFile fileInfo(int boardId);

	void fileDelete(int boardId);

	void fileUpdate(@Param("board")Board board, @Param("boardFile")BoardFile boardFile, @Param("file")MultipartFile file);
}
