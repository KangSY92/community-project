package kr.co.community.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardFileDTO {
	private int fileId;
	private int boardId;
	private String path;
	private String originName;
	private String updateName;
	private String size;
	
	public static final String LOCAL_PATH = "C:\\Users\\Foryoucom\\git\\community-project\\src\\main\\resources\\static\\uploads\\";
	
	public static final String RESOURCES_PATH = "/uploads/";
	

}
