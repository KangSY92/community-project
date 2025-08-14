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
