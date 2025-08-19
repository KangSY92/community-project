package kr.co.community.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

	private int boardId;
	private String title;
	private String author;
	private String content;
	private int viewCount;
	private String createDate;
	private String updateDate;
	private String dropDate;
	
	private String text = "";
}
