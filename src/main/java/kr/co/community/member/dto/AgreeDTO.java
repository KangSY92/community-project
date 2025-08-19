package kr.co.community.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 약관 동의 정보를 담는 DTO 클래스 입니다.
 * 
 * 회원가입시 사용자의 약관, 개인정보, 마케팅 수신 동의 여부를 전달하기 위해 사용됩니다.
 * 
 * 이 객체는 컨트롤러에서 전달받아 DB에 저장하거나 검증하는 곳에 사용됩니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgreeDTO {
	
	/** 약관동의 고유 ID (PK 역할) */
	private int agreeId;
	
	/** 동의를 제출한 회원의 고유 ID */
	private int memberId;
	
	/** 약관 고유 ID (이용약관, 개인정보처리방침, 마케팅수신 구분용) */
	private int termsId;

	/** 마케팅 수신 동의 여부 ("Y" 또는 "N") */
	private String marketingAgree;


}
