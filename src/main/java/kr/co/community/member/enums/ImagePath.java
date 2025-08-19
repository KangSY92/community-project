package kr.co.community.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImagePath {

	LOCAL_PATH("C:\\Users\\Foryoucom\\git\\community-project\\src\\main\\resources\\static\\img\\"),

	IMG_PATH("/img/"),
	
	NAME_SET("profil-img.png");
	
	private final String path;

}
