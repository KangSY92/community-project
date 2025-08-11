package kr.co.community.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFile {
	
	private int fileId;
	private int boardId;
	private String path;
	private String originName;
	private String updateName;
	
	public static final String LOCAL_PATH = "C:\\Users\\Foryoucom\\git\\community-project\\src\\main\\resources\\static\\uploads\\";
	
	public static final String RESOURCES_PATH = "/uploads/";
}
