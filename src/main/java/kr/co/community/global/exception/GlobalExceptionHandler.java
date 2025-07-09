package kr.co.community.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.community.member.exception.MemberException;

/**
 * 전역 예외 처리 핸들러 클래스입니다.
 * 
 * 이 클래스는 애플리케이션 전반에서 발생할 수 있는 MemberException 예외를 처리합니다.
 */
public class GlobalExceptionHandler {

	/**
	 * MemberException 예외가 발생했을 때 호출되는 핸들러 메서드입니다.
	 * 
	 * 발생한 예외의 메시지를 flash attribute로 전달하여 사용자에게 오류 메시지를 전달합니다.
	 * 
	 * @param e 예외 객체 MemberException
	 * @param redirectAttributes 리다이렉트시 flash 속성을 추가하기 위한 객체
	 * @return 회원가입 폼 페이지로 리다이렉트하는 URL 문자열
	 */
	@ExceptionHandler(MemberException.class)
	public String handleMemberException(MemberException e, RedirectAttributes redirectAttributes) {
		String message = e.getMessage() != null ? e.getMessage() : "알 수 없는 오류가 발생했습니다.";
		redirectAttributes.addFlashAttribute("errorMessage", message);
		return "redirect:/member/register/form";
	}
}
