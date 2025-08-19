package kr.co.community.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommetDeleteDTO {

	private int commentId;
	private int boardId;
	private String author;
}
