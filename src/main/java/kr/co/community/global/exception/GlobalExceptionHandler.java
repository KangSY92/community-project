package kr.co.community.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.community.member.exception.MemberException;

public class GlobalExceptionHandler {

	@ExceptionHandler(MemberException.class)
	public String handleMemberException(MemberException e, RedirectAttributes redirectAttributes) {
		String message = e.getMessage() != null ? e.getMessage() : "알 수 없는 오류가 발생했습니다.";
		redirectAttributes.addFlashAttribute("errorMessage", message);
		return "redirect:/member/register/form";
	}
}
