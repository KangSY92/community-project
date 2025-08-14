package kr.co.community.board.dto;

import kr.co.community.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBoardDTO {
	
	private String text = "";
	private int currentPage = 1;
	
	public Board toBoard() {
		return Board.builder()
				.text(text)
				.build();
	}
}
