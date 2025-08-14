package kr.co.community.board.dto;

import org.springframework.web.multipart.MultipartFile;

import kr.co.community.board.domain.Board;
import kr.co.community.board.domain.BoardFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBoardEditDTO {
	
	private int boardId;
	private String title;
	private String content;
	
	private boolean fileDelete;
	private MultipartFile file;
	
	private int fileId;
	private String path;
	private String originName;
	private String updateName;

	public Board toBoard() {
		return Board.builder()
				.boardId(boardId)
				.title(title)
				.content(content)
				.build();
	}
	
	public BoardFile toBoardFile() {
		return BoardFile.builder()
				.fileId(fileId)
				.path(path)
				.originName(originName)
				.updateName(updateName)
				.build();
	}
}
