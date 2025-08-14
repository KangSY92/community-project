package kr.co.community.member.service;

import kr.co.community.member.dto.RequestLoginDTO;
import kr.co.community.member.dto.RequestRegisterDTO;
import kr.co.community.member.dto.ResponseLoginDTO;

public interface MemberService {
	
	void register(RequestRegisterDTO requestRegisterDTO);

	ResponseLoginDTO login(RequestLoginDTO requestLoginDTO);

}
