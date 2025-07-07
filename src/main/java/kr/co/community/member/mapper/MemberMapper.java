package kr.co.community.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;

@Mapper
public interface MemberMapper {
	
	void register(@Param("registerDTO")RegisterDTO registerDTO, @Param("agreeDTO")AgreeDTO agreeDTO, MultipartFile profileImage);
	void register1(AgreeDTO agreeDTO);
	void register2(AgreeDTO agreeDTO);
	void register3(AgreeDTO agreeDTO);
}
