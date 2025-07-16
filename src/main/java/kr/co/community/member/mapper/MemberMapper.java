package kr.co.community.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;

/**
 * 회원 관련 DB 연산을 담당하는 매퍼 인터페이스입니다.
 * 
 * 회원등록, 약관 동의 내역 저장 등 회원가입 프로세스에서 필요한 데이터베이스 작업을 정의합니다.
 * 
 * 이 인터페이스의 메서드는 매핑된 XML 쿼리나 어노테이션 기반 SQL에 의해 실행됩니다.
 */
@Mapper
public interface MemberMapper {
	
	/**
	 * 회원정보를 DB에 등록합니다.
	 * 
	 * @param registerDTO 등록할 회원 정보
	 * @param agreeDTO 약관 동의 정보
	 * @param profileImage 프로필 이미지(실제 DB에 저장하지 않더라도 서비스 계층에서 필요함
	 */
	void register(@Param("registerDTO")RegisterDTO registerDTO,
				  @Param("agreeDTO")AgreeDTO agreeDTO,
				  MultipartFile profileImage);
	
	/**
	 * 이용약관 동의 여부를 DB에 저장합니다.
	 * 
	 * @param agreeDTO 약관 동의 정보
	 */
	void termsAgree(AgreeDTO agreeDTO);
	
	/**
	 * 개인정보 처리방침 동의 여부를 DB에 저장합니다.
	 * 
	 * @param agreeDTO 약관 동의 정보
	 */
	void privacyAgree(AgreeDTO agreeDTO);
	
	/**
	 * 마케팅 정보 수신 동의 여부를 DB에 저장합니다.
	 * 
	 * @param agreeDTO 약관 동의 정보
	 */
	void marketingAgree(AgreeDTO agreeDTO);

	/**
	 * 사용자 로그인 시, 입력된 ID에 해당하는 회원 정보를 조회합니다.
	 * 
	 * @param registerDTO 로그인 요청 정보(아이디, 비밀번호)
	 * @return 회원정보가 존재하면 해당 정보를 반환, 없으면 null
	 */
	RegisterDTO login(RegisterDTO registerDTO);
}