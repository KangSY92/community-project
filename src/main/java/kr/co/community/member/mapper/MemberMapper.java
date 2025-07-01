package kr.co.community.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.community.member.dto.RegisterDTO;

@Mapper
public interface MemberMapper {
	
	int register(RegisterDTO registerDTO);
	

}
