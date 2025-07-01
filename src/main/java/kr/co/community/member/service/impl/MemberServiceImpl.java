package kr.co.community.member.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.mapper.MemberMapper;
import kr.co.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public int register(RegisterDTO registerDTO) {
		 String rawPassword = registerDTO.getPassword();
		    System.out.println("원본 비밀번호 : " + rawPassword);
		    
		    String passEncode = passwordEncoder.encode(rawPassword);
		    System.out.println("암호화된 비밀번호 : " + passEncode);
		    
		    registerDTO.setPassword(passEncode);
		    System.out.println("DTO에 설정된 비밀번호 : " + registerDTO.getPassword());
		return memberMapper.register(registerDTO);
	}
	
	

}
