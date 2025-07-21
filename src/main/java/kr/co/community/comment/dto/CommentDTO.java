package kr.co.community.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	
	private int commentId;
	private int boardId;
	private String id;
	private String author;
	private String content;
	private String createDate;
	private String updateDate;
	

}
