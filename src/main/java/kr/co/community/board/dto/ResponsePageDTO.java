package kr.co.community.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponsePageDTO {
	
	private int currentPage;
	private int maxPage;
	private int startPage;
	private int endPage;	
	private int row;

	public static  ResponsePageDTO from(PageDTO pi) {
		return ResponsePageDTO.builder()
				.currentPage(pi.getCurrentPage())
				.maxPage(pi.getMaxPage())
				.startPage(pi.getStartPage())
				.endPage(pi.getEndPage())
				.row(pi.getRow())
				.build();
	}
}
