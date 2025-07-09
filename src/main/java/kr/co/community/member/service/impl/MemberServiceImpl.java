package kr.co.community.member.service.impl;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.exception.MemberException;
import kr.co.community.member.mapper.MemberMapper;
import kr.co.community.member.service.MemberService;
import kr.co.community.member.util.FileUpload;
import lombok.RequiredArgsConstructor;

/**
 * MemberService 인터페이스의 구현제로, 회원가입 관련 비지니스 로직을 처리합니다.
 * 
 * 주요 기능
 * - 비밀번호 암호화
 * - 프로필 이미지 업로드 및 기본 이미지 처리
 * - 회원 정보 및 약관 동의 내역 DB 저장
 * - 예외 발생 시 MemberException으로 래핑하여 처리
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	/** 회원관련 데이터 베이스 처리를 위한 매퍼 */
	private final MemberMapper memberMapper;

	/** 비밀번호 암호화를 위한 Spring Security의 PasswordEncoder */
	private final PasswordEncoder passwordEncoder;

	/** 파일 업로드 유틸리티 클래스 */
	private final FileUpload fileUpload;

	/**
	 * 회원정보를 등록하고, 약관동의 등의 정보를 저장합니다.
	 * 
	 * @param registerDTO  사용자로부터 입력받은 회원가입 정보 DTO
	 * @param agreeDTO     사용자 약관 동의 정보 DTO
	 * @param profileImage 업로드된 프로필 이미지 (null 또는 비어있을시 기본 이미지 사용)
	 * @throws MemberException 파일 업로드 또는 DB 저장 실패 시 발생
	 */
	@Override
	public void register(RegisterDTO registerDTO, AgreeDTO agreeDTO, MultipartFile profileImage) {

		try {
			// 비밀번호 암호화
			String rawPassword = registerDTO.getPassword();
			String passEncode = passwordEncoder.encode(rawPassword);
			registerDTO.setPassword(passEncode);
			
			//프로필 이미지 업로드 처리
			if (profileImage != null && !profileImage.isEmpty()) {		
					fileUpload.upload(profileImage, registerDTO);			
			} else {
				// 이미지가 없을 경우 기본 이미지 설정
				registerDTO.setImgName("profil-img.png");
				registerDTO.setImgPath(RegisterDTO.RESOURCES_PATH);
			}
			
			//회원정보 및 약관 동의 내역 저장(insert)
			memberMapper.register(registerDTO, agreeDTO, profileImage);
			memberMapper.termsAgree(agreeDTO);
			memberMapper.privacyAgree(agreeDTO);
			memberMapper.marketingAgree(agreeDTO);
			
		} catch (IOException e) {
			throw new MemberException("프로필 이미지 업로드 중 오류가 발생했습니다.", e);
		} catch (Exception e) {
			throw new MemberException("회원가입 중 오류가 발생했습니다.", e);
		}

	}

	public RegisterDTO login(RegisterDTO registerDTO) {
	
		RegisterDTO result = memberMapper.login(registerDTO);
		
		if(passwordEncoder.matches(registerDTO.getPassword(), result.getPassword())){
			return result;
		}
		
		return null;
	}

}
