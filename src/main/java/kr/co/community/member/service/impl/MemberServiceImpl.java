package kr.co.community.member.service.impl;

import org.springframework.stereotype.Service;

import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.mapper.MemberMapper;
import kr.co.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
//	private final PasswordEncoder passwordEncoder;

	@Override
	public int register(RegisterDTO registerDTO) {
		
//		String passEncode = passwordEncoder.encode(registerDTO.getPassword());
//		registerDTO.setPassword(passEncode);
		
		return memberMapper.register(registerDTO);
	}
	
	

}
