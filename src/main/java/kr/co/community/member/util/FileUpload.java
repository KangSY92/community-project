package kr.co.community.member.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kr.co.community.member.dto.RegisterDTO;

@Component
public class FileUpload {

	public void upload(MultipartFile file, RegisterDTO registerDTO) throws IOException {
		// 1. 원본 파일 이름
		String originalName = file.getOriginalFilename();
		
		String extension = originalName.substring(originalName.lastIndexOf("."));
		
		// 2. 새로운 파일 이름
		String changeName = UUID.randomUUID().toString() + extension; 
		
		// 3. 서버에 저장될 경로 설정
		Path path = Paths.get(RegisterDTO.LOCAL_PATH + changeName);
		
		// 4.파일 저장
		Files.write(path, file.getBytes());
		
		// 5. fileDTO에 정보 저장
		registerDTO.setImgName(changeName);
		registerDTO.setImgPath(registerDTO.RESOURCES_PATH);
		
	}
	
//	public void deleteFile(String changeName) {
//		Path path = Paths.get(RegisterDTO.LOCAL_PATH + changeName);
//		try {
//			Files.delete(path);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private String getFileExtension(String originalName) {
//		// originalName : 제목없음.png
//		// dotIndex : 4
//		
//		int dotIndex = originalName.lastIndexOf('.');
//		return dotIndex == -1 ? "" : originalName.substring(dotIndex);
//	}
}
