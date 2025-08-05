package kr.co.community.member.service.impl;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.community.global.transaction.TransactionHandler;
import kr.co.community.member.domain.Agree;
import kr.co.community.member.domain.Member;
import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.dto.RequestRegisterDTO;
import kr.co.community.member.exception.MemberException;
import kr.co.community.member.mapper.MemberMapper;
import kr.co.community.member.service.MemberService;
import kr.co.community.member.util.FileUpload;
import lombok.RequiredArgsConstructor;

/**
 * MemberService 인터페이스의 구현제로, 회원가입 관련 비지니스 로직을 처리합니다.
 * 
 * 기능 : 
 * - 비밀번호 암호화
 * - 프로필 이미지 업로드 또는 기본 이미지 설정
 * - 회원 정보 및 약관 동의 내역 저장
 * - 예외 발생 시 MemberExcption 처리
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

	/** 트랜잭션을 수동으로 처리하기 위한 헬퍼 클래스 */
	private final TransactionHandler transactionHandler;

	/**
	 * 회원가입 요청을 처리합니다.
	 * 
	 * 비밀번호 암호화, 프로필 이미지 업로드 또는 기본 이미지 지정, 회원 정보 및 약관 동의 정보 저장을 수행합니다.
	 * 트랜잭션 중 오류가 발생하면 롤백 처리 후 MemberException을 발생시킵니다.
	 * 
	 * @param registerDTO  사용자로부터 입력받은 회원가입 정보 DTO
	 * @param agreeDTO     사용자 약관 동의 정보 DTO
	 * @param profileImage 업로드된 프로필 이미지 (null 또는 비어있을시 기본 이미지 사용)
	 * @throws MemberException 파일 업로드 또는 DB 저장 실패 시 발생
	 */
	@Override
	public void register(RequestRegisterDTO requestRegisterDTO) {

		// 1. 트랜잭션 설정 및 시작(현재 트랜잭션의 상태를 가져옴)
		TransactionStatus status = transactionHandler.getStatus();

		// 2. commit, rollback을 사용하기 위한 객체
		PlatformTransactionManager transactionManager = transactionHandler.getTransactionManager();

		Agree agree = requestRegisterDTO.toAgree();

		try {
			// 비밀번호 암호화
			String rawPassword = requestRegisterDTO.getPassword();
			String passEncode = passwordEncoder.encode(rawPassword);

			Member member = requestRegisterDTO.toMember(passEncode);
			// 프로필 이미지 업로드 처리
			if (requestRegisterDTO.getProfileImage() != null && !requestRegisterDTO.getProfileImage().isEmpty()) {
				
				fileUpload.upload(requestRegisterDTO.getProfileImage(), member, RequestRegisterDTO.LOCAL_PATH, RequestRegisterDTO.IMG_PATH);
			} else {
				// 이미지가 없을 경우 기본 이미지 설정
				member.setImgName("profil-img.png");
				member.setImgPath(RequestRegisterDTO.IMG_PATH);
			}

			// 회원정보 및 약관 동의 내역 저장(insert)
			memberMapper.register(member);
			memberMapper.termsAgree(agree);
			memberMapper.privacyAgree(agree);
			memberMapper.marketingAgree(agree);

			// 모든 작업 성공시 커밋
			transactionManager.commit(status);
			
		} catch (IOException e) {
			// 파일 업로드 중 예외 발생 시 롤백 처리
			transactionManager.rollback(status);
			throw new MemberException("프로필 이미지 업로드 중 오류가 발생했습니다.", e);
		} catch (Exception e) {
			// 기타 예외 발생 시 롤백 처리
			transactionManager.rollback(status);
			throw new MemberException("회원가입 중 오류가 발생했습니다.", e);
		}

	}

	/**
	 * 로그인 요청을 처리합니다.
	 * 
	 * 로그인 시도 후 결과를 반환하며, 로그인 실패 또는 시스템 오류 발생 시 MemberException을 발생시킵니다.
	 * 
	 *  @param registerDTO 로그인 요청 시 입력된 ID/PW 정보가 담긴 DTO
	 *  @return 로그인 성공 시 사용자 정보 반환, 실패시 null
	 *  @throws MemberException 로그인 처리 중 예외가 발생하면 해당 예외를 던집니다.
	 */
	public RegisterDTO login(RegisterDTO registerDTO) {

	    try {
	        RegisterDTO result = memberMapper.login(registerDTO);

	        return result;

	    } catch (MemberException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new MemberException("로그인 중 오류가 발생했습니다.", e); 
	    }
	}

}
