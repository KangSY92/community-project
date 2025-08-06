package kr.co.community.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

	private int memberId;
	private String id;
	private String name;
	private String password;
	private String status;
	private String createAt;
	private String updateAt;
	private String dropDate;
	@Setter
	private String imgName;
	@Setter
	private String imgPath;
	private String email;
	
}
