package kr.co.community.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseBoardListDTO {
	
	private int boardId;
	private String title;
	private String content;
	private int viewCount;
	private String createDate;
	private String author;
	
	public static ResponseBoardListDTO from(BoardDTO dto) {
		return ResponseBoardListDTO.builder()
				.boardId(dto.getBoardId())
				.title(dto.getTitle())
				.content(dto.getContent())
				.viewCount(dto.getViewCount())
				.createDate(dto.getCreateDate())
				.author(dto.getAuthor())
				.build();
	}

}
