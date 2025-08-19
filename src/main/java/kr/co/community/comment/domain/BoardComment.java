package kr.co.community.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardComment {
	
	private int commentId;
	private int boardId;
	private String id;
	private String author;
	private String content;
	private String createDate;
	private String updateDate;

}
