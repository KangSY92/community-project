package kr.co.community.member.service.impl;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.mapper.MemberMapper;
import kr.co.community.member.service.MemberService;
import kr.co.community.member.util.FileUpload;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	private final FileUpload fileUpload;

	@Override
	public void register(RegisterDTO registerDTO, AgreeDTO agreeDTO, MultipartFile profileImage) {
		
			String rawPassword = registerDTO.getPassword();
		    String passEncode = passwordEncoder.encode(rawPassword);
		    registerDTO.setPassword(passEncode);
		    
		    
		    if(profileImage != null && !profileImage.isEmpty()) {
		    	try {
		    		fileUpload.upload(profileImage, registerDTO);
		    		
		    		
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    	
		    } else {
		    	registerDTO.setImgName("profil-img.png");
		    	registerDTO.setImgPath(registerDTO.RESOURCES_PATH);
		    }
		    
		    memberMapper.register(registerDTO, agreeDTO, profileImage);
		    memberMapper.register1(agreeDTO);
		    memberMapper.register2(agreeDTO);
		    memberMapper.register3(agreeDTO);
		    

	} 
	
}

	
