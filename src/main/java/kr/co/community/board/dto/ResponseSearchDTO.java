package kr.co.community.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseSearchDTO {
	
	private String text;

	public static ResponseSearchDTO  from(String text) {
		return ResponseSearchDTO.builder()
				.text(text)
				.build();
	}
	
	
}
