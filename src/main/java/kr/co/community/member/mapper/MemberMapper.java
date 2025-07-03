package kr.co.community.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;

@Mapper
public interface MemberMapper {
	
	int register(@Param("registerDTO")RegisterDTO registerDTO, @Param("agreeDTO")AgreeDTO agreeDTO);
	void register1(AgreeDTO agreeDTO);
	void register2(AgreeDTO agreeDTO);
	void register3(AgreeDTO agreeDTO);
}
