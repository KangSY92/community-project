package kr.co.community.member.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.dto.RegisterDTO;

/**
 * 회원 프로필 이미지를 서버에 업로드하는 기능을 제공하는 유틸리티 클래스입니다.
 * 
 * 업로드된 이미지 파일을 지정된로컬 디렉터리에 자장하고,
 * RegisterDTO에 해당 파일 정보(파일명, 경로)를 설정합니다.
 * 
 * 파일이름은 중복 방지를 위해 UUID로 변경되며, 확장자는 유지됩니다.
 */
@Component
public class FileUpload {

	/**
	 * 업로드된 파일을 서버의 로컬 디렉토리에 저장하고,
	 * 해당 파일명들을 RegisterDTO에 세팅합니다.
	 *  
	 * @param file 업로드 된 멀티파트 파일
	 * @param registerDTO 파일 정보를 설정할 회원 등록 DTO
	 * @throws IOException 파일 저장 중 오류 발생 시
	 */
	public void upload(MultipartFile file, RegisterDTO registerDTO) throws IOException {
		// 1. 원본 파일 이름
		String originalName = file.getOriginalFilename();
		
		// 2. 확장자 분리
		String extension = originalName.substring(originalName.lastIndexOf("."));
		
		// 3. 중복방지를 위한 UUID기반 새 파일 이름 생성
		String changeName = UUID.randomUUID().toString() + extension; 
		
		// 4. 저장 경로 지정
		Path path = Paths.get(RegisterDTO.LOCAL_PATH + changeName);
		
		// 5. 파일 저장
		Files.write(path, file.getBytes());
		
		// 6. fileDTO에 저장 정보 세팅
		registerDTO.setImgName(changeName);
		registerDTO.setImgPath(RegisterDTO.RESOURCES_PATH);
		
	}
	
}
