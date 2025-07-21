package kr.co.community.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.community.comment.dto.CommentDTO;

@Mapper
public interface CommentMapper {

	void create(@Param("boardId")int boardId, @Param("comment")CommentDTO commentDTO);

}
