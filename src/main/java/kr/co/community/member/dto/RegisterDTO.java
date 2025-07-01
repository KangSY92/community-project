package kr.co.community.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
	
	private int memberId;	
	private String id;
	private String name;
	private String password;
	private String status;
	private String createdAt;
	private String updatedAt;
	private String dropDate;
	private String imgName;
	private String imgPath;
	private String email;
	
	
	
	

}
