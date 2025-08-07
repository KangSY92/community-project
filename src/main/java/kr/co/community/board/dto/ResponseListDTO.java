package kr.co.community.board.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseListDTO {

	private List<ResponseBoardListDTO> list;
	
	private ResponseSearchDTO search;
	
	private ResponsePageDTO page;
	
	public static ResponseListDTO from(List<BoardDTO> list, PageDTO pi, String text) {
		List<ResponseBoardListDTO> responseList = list.stream()
				.map(ResponseBoardListDTO::from)
				.collect(Collectors.toList());
		ResponseSearchDTO search = ResponseSearchDTO.from(text);
		ResponsePageDTO page = ResponsePageDTO.from(pi);
		return ResponseListDTO.builder()
				.list(responseList)
				.search(search)
				.page(page)
				.build();
	}
}
