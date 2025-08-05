package kr.co.community.member.dto;

import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.domain.Agree;
import kr.co.community.member.domain.Member;
import kr.co.community.member.domain.Terms;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegisterDTO {
	
	/**
	 * 로컬 저장소 내 이미지 저장 경로
	 */
	public static final String LOCAL_PATH = "C:\\Users\\Foryoucom\\git\\community-project\\src\\main\\resources\\static\\img\\";
	
	/**
	 * 웬에서 접근 가능한 리소스 이미지 경로
	 */
	public static final String IMG_PATH = "/img/";

	private String id;
	private String name;
	private String password;
	private String email;
	private MultipartFile profileImage;
	private String imgName;
	private String imgPath;
	
	private int termsId;
	private String marketingAgree;
	
	public Member toMember(String passEncode) {
		return Member.builder()
				.id(id)
				.name(name)
				.password(passEncode)
				.email(email)
				.build();
	}
	
	
	public Agree toAgree() {
		return Agree.builder()
				.agreement(marketingAgree)
				.build();
	}
}
