package kr.co.community.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	@GetMapping("/register/form")
	public String registerForm() {
		return "member/register";
	}
	
	@PostMapping()
	public String register(Model model) {
		
		return null;
	}
	

}
