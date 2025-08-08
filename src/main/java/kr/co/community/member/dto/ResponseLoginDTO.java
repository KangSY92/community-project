package kr.co.community.member.dto;

import kr.co.community.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseLoginDTO {
	
	private int memberId;
	private String id;
	private String name;
	private String password;
	private String imgName;
	private String imgPath;
	private String email;

	public static ResponseLoginDTO from(Member result) {
		return ResponseLoginDTO.builder()
				.memberId(result.getMemberId())
				.id(result.getId())
				.name(result.getName())
				.password(result.getPassword())
				.imgName(result.getImgName())
				.imgPath(result.getImgPath())
				.email(result.getEmail())
				.build();
		
	}
}
