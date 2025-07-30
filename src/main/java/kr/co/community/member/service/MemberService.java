package kr.co.community.member.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;

public interface MemberService {
	
	void register(RegisterDTO registerDTO, AgreeDTO agreeDTO, MultipartFile profileImage);

	RegisterDTO login(RegisterDTO registerDTO);

	int idCheck(String id);
}
