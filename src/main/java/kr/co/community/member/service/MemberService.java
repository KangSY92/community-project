package kr.co.community.member.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.domain.Member;
import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.dto.RequestRegisterDTO;

public interface MemberService {
	
	void register(RequestRegisterDTO requestRegisterDTO);

	RegisterDTO login(RegisterDTO registerDTO);

}
