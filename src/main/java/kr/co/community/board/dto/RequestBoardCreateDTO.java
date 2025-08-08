package kr.co.community.board.dto;

import org.springframework.web.multipart.MultipartFile;

import kr.co.community.comment.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBoardCreateDTO {
	
	private String title;
	private String author;
	private String content;
	
	private MultipartFile file;
	
	public Board toBoard() {
		return Board.builder()
				.title(title)
				.author(author)
				.content(content)
				.build();
	}

}
