package kr.co.community.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	
	private int boardId;
	private String title;
	private String content;
	private int viewCount;
	private String createDate;
	private String updateDate;
	private String dropDate;
	private String author;

	private String text;
}
