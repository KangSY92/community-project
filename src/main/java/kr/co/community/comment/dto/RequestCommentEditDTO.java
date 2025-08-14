package kr.co.community.comment.dto;

import kr.co.community.comment.domain.BoardComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommentEditDTO {

	private int commentId;
	private int boardId;
	private String author;
	private String content;

	public BoardComment toBoardComment() {
		return BoardComment.builder()
				.commentId(commentId)
				.boardId(boardId)
				.author(author)
				.content(content)
				.build();
	}
}
