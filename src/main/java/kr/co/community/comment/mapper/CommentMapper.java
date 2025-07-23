package kr.co.community.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.community.board.dto.PageDTO;
import kr.co.community.comment.dto.CommentDTO;

@Mapper
public interface CommentMapper {

	void create(@Param("boardId")int boardId, @Param("comment")CommentDTO commentDTO);

	List<CommentDTO> getList(@Param("boardId")int boardId, @Param("pi")PageDTO pi);

	int commentCount(int boardId);

}
