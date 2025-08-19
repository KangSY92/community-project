package kr.co.community.member.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.dto.RequestLoginDTO;
import kr.co.community.member.dto.RequestRegisterDTO;
import kr.co.community.member.dto.ResponseLoginDTO;
import kr.co.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 요청을 처리하는 컨트롤러 클래스입니다.
 * 
 * 기능 : 
 * - 회원가입 폼 페이지 반환
 * - 회원가입 처리
 * - 로그인 및 세션 저장
 * - 로그아웃 처리
 */
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	/** 회원 서비스 로직을 처리하는 객체 */
    private final MemberService memberService;
    
    /** 비밀번호 암호화를 위한 객체 */
    private final PasswordEncoder passwordEncoder;
	
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
	 * 회원가입 요청을 처리합니다.
	 * 
	 * 유효성 검사 오류가 있을 경우 회원가입 폼으로 리다이렉트 합니다.
	 * 회원가입 성공 시 메인 페이지로 이동하고, 실패시 실패 메시지를 전달합니다.
	 * 
	 * @param requestRegisterDTO 회원정보 DTO
	 * @param bindingResult 유효성 검사 결과
	 * @param redirectAttributes FlashAttribute 전달 객체
	 * @param model 뷰에 전달할 모델 객체
	 * @return 리다이렉트 대상 경로
	 */
	@PostMapping("/register")
	public String register(@Valid RequestRegisterDTO requestRegisterDTO, 
						   BindingResult bindingResult,
						   RedirectAttributes redirectAttributes,
						   Model model) {
    	
		try {
			if(bindingResult.hasErrors()) {
				//유효성 검사 오류가 있는 경우 회원가입 폼으로 되돌아감
				return "redirect:/member/register/form";
			}
			//회원가입 처리 서비스 호출
			memberService.register(requestRegisterDTO);
			redirectAttributes.addFlashAttribute("registMsg", "회원가입에 성공했습니다.");
			//메인페이지로 이동
			return "redirect:/";
			
		} catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("registMsg", "회원가입에 실패했습니다.");
			return "redirect:/member/register/form"; 
		}
	}
	
	/**
	 * 로그인 요청을 처리합니다.
	 * 
	 * 로그인 성공시 사용자 정보를 세션에 저장하고 메인 페이지로 리다이렉트합니다.
	 * 실패시 실패 메시지를 전달합니다.
	 * 
	 * @param requestRegisterDTO 로그인 요청 정보(아이디, 비밀번호)
	 * @param session 현재 HTTP 세션
	 * @param redirectAttributes FlashAttribute 전달 객체
	 * @return 메인페이지로 리다이렉트
	 */
	@PostMapping("/login")
	public String login(RequestLoginDTO requestLoginDTO,
						HttpSession session,
						RedirectAttributes redirectAttributes) {

			ResponseLoginDTO result = memberService.login(requestLoginDTO);
			
	        if (result == null) {
	        	redirectAttributes.addFlashAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
	        	return "redirect:/";
	        }
	        
	        if (!passwordEncoder.matches(requestLoginDTO.getPassword(), result.getPassword())) {
	        	redirectAttributes.addFlashAttribute("loginFailMsg", "비밀번호가 일치하지 않습니다.");
	        	return "redirect:/";
	        }

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
	 * @param session 현재 HPPT 세션 객체
	 * @return 로그아웃 처리 후 메인 페이지로 리다이렉트
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
			session.invalidate(); // 세션 무효화
		return "redirect:/";
	}
	
}
