package kr.co.community.member.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.community.member.dto.AgreeDTO;
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
	public int register(RegisterDTO registerDTO, AgreeDTO agreeDTO) {
		 String rawPassword = registerDTO.getPassword();
		    
		    String passEncode = passwordEncoder.encode(rawPassword);
		    
		    registerDTO.setPassword(passEncode);
		    int regist = memberMapper.register(registerDTO, agreeDTO);
		    memberMapper.register1(agreeDTO);
		    memberMapper.register2(agreeDTO);
		    memberMapper.register3(agreeDTO);
		    
		return regist;
	} 
	
}

	
