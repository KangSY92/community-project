package kr.co.community.member.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;

public interface MemberService {
	
	int register(RegisterDTO registerDTO, AgreeDTO agreeDTO, MultipartFile profileImage);

}
