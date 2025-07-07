package kr.co.community.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgreeDTO {
	
	private int agreeId;
	private int memberId;
	private int termsId;
	private String marketingAgree;


}
