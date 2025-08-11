package kr.co.community.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBoardDetailDTO {
	
	private int boardId;
	private int currentPage = 1;
	private int pageLimit = 5;
	private int boardLimit = 5; 

}
