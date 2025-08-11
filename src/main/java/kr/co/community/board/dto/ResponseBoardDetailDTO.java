package kr.co.community.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseBoardDetailDTO {
	
	private int boardId;
	private String title;
	private String author;
	private String content;
	private int viewCount;
	private String createDate;
	private String updateDate;

	public static ResponseBoardDetailDTO from(BoardDTO baordDTO) {
		return ResponseBoardDetailDTO.builder()
				.boardId(baordDTO.getBoardId())
				.title(baordDTO.getTitle())
				.author(baordDTO.getAuthor())
				.content(baordDTO.getContent())
				.viewCount(baordDTO.getViewCount())
				.createDate(baordDTO.getCreateDate())
				.updateDate(baordDTO.getUpdateDate())
				.build();
	}
}
