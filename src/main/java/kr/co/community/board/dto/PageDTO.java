package kr.co.community.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageDTO {
	private int totalCount;		// 전체 게시글 수
	private int currentPage;	// 현재 페이지
	private int pageLimit;		// 버튼에 보여질 페이지 수
	private int boardLimit;		// 한 페이지에 보여질 게시글 수
	
	private int maxPage;		// 전체 페이지
	private int startPage;		// 시작 페이지
	private int endPage;		// 끝 페이지
	
	private int row;			// view에서 꺼낼 게시글 번호
	private int offset;			// DB 몇번째 데이터부터 가져올지
	private int limit;			// DB 몇번째 데이터 까지 가져올지

}
