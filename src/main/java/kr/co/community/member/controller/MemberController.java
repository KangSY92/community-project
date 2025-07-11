package kr.co.community.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.community.member.dto.AgreeDTO;
import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 요청을 처리하는 컨트롤러 클래스입니다.
 * 
 * 회원가입 폼 페이지 요청 및 회원가입 처리기능을 제공합니다.
 * 
 * 회원가입 처리시 MemberServiceImpl을 통해 실제 비지니스 로직을 위임합니다.
 */
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	/**
	 * 회원 관련 비지니스 로직을 처리하는 서비스 객체
	 */
    private final MemberServiceImpl memberService;
	
    /**
     * 회원가입 폼 페이지를 보여주는 메서드 입니다.
     * 
     * @param model 뷰에 전달할 데이터를 담는 모델 객체
     * @return 회원가입 페이지 뷰 이름 (member/register)
     */
	@GetMapping("/register/form")
	public String registerForm(Model model) {
		model.addAttribute("registerDTO", new RegisterDTO());
		return "member/register";
	}
	
	/**
	 * 회원가입 폼 데이터를 처리하는 메서드 입니다.
	 * 
	 * @param registerDTO 사용자로부터 입력받은 회원 정보 DTO
	 * @param agreeDTO 이용약관 동의 정보 DTO
	 * @param bindingResult registerDTO에 대한 유효성 검사 결과를 담는 객체
	 * @param profileImage 사용자가 업로드한 프로필 이미지 파일
	 * @param model 뷰에 전달할 모델 객체
	 * @return 회원가입 성공 시 메인 페이지로, 유효성 검사 실패 시 회원가입 페이지로 리다이렉트
	 */
	@PostMapping("/register")
	public String register(@Valid RegisterDTO registerDTO, 
						   AgreeDTO agreeDTO, 
						   BindingResult bindingResult,
						   @RequestParam("profileImage") MultipartFile profileImage, 
						   Model model) {
    	
		//유효성 검사 오류가 있는 경우 회원가입 폼으로 되돌아감
		if(bindingResult.hasErrors()) {
			return "redirect:/member/register/form";
		}

		//회원가입 처리 서비스 호출
		memberService.register(registerDTO, agreeDTO, profileImage);
		
		//메인페이지로 이동
		return "redirect:/";
	}
	
	/**
	 * 로그인 요청을 처리합니다.
	 * 
	 * 로그인 성공시 사용자 정보를 세션에 저장하고 메인 페이지로 리다이렉트합니다.
	 * 
	 * @param registerDTO 로그인정보(아이디, 비밀번호)
	 * @param session 현재 세션
	 * @return 메인페이지로 리다이렉트
	 */
	@PostMapping("/login")
	public String login(RegisterDTO registerDTO, HttpSession session, RedirectAttributes redirectAttributes) {

			RegisterDTO result = memberService.login(registerDTO);
			
			session.setAttribute("id", result.getId());
			session.setAttribute("name", result.getName());
			session.setAttribute("email", result.getEmail());
			session.setAttribute("imgName", result.getImgName());
			session.setAttribute("imgPath", result.getImgPath());

			return "redirect:/";


	}
	
	/**
	 * 로그아웃 요청을 처리하는 메서드입니다.
	 * 
	 * 현재 로그인 된 사용자 세션을 무효화하고 메인 페이지로 리다이렉트 합니다.
	 * 
	 * @param session 현재  HPPT 세션 객체
	 * @return 로그아웃 처리 후 메인 페이지로 리다이렉트
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
			session.invalidate(); // 세션 무효화
		return "redirect:/";
	}

}
