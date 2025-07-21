package kr.co.community.board.util;

import org.springframework.stereotype.Component;

import kr.co.community.board.dto.PageDTO;

@Component
public class Pagenation {

	public PageDTO getpageDTO(int totalCount, int currentPage, int pageLimit, int boardLimit) {

		int maxPage = (int) (Math.ceil((double) totalCount / boardLimit));

		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

		int endPage = startPage + pageLimit - 1;

		int row = totalCount - (currentPage - 1) * boardLimit;

		int offset = (currentPage - 1) * boardLimit + 1;

		int limit = offset + boardLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		return new PageDTO(totalCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage, row, offset,
				limit);

	}

}
