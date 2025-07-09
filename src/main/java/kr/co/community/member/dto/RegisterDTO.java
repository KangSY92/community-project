package kr.co.community.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원등록 시 사용하는 데이터 전달 객체(DTO)입니다.
 * 
 * 회원정보 입력 폼에서 받은 데이터를 컨트롤러 -> 서비스 -> 매퍼 계층으로 전달할 때 사용됩니다.
 * 
 * 프로필 이미지 경로, 이메일, 아이디, 비밀번호 등의 정보를 보함하고 있습니다. 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
	
	/**
	 * 로컬 저장소 내 이미지 저장 경로
	 */
	public static final String LOCAL_PATH = "C:\\dev\\spring-workspace\\community-project\\src\\main\\resources\\static\\img\\";
	
	/**
	 * 웬에서 접근 가능한 리소스 이미지 경로
	 */
	public static final String RESOURCES_PATH = "/img/";
	
	/** 회원 고유 ID (DB의 PK역할) */
	private int memberId;	
	
	/**	사용자 아이디 */
	private String id;
	
	/** 사용자 닉네임 */
	private String name;
	
	/** 비밀번호 */
	private String password;
	
	/** 회원 상태 (활성/비활성) */
	private String status;
	
	/** 가입일자 (생성일) */
	private String createdAt;
	
	/** 회원정보 마지막 수정일 */
	private String updatedAt;
	
	/** 회원 탈퇴일 */
	private String dropDate;
	
	/** 프로필 이미지 파일명 */
	private String imgName;
	
	/** 프로필 이미지 경로 */
	private String imgPath;
	
	/** 이메일 주소 */
	private String email;

}
