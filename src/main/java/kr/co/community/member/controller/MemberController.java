package kr.co.community.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import kr.co.community.member.dto.RegisterDTO;
import kr.co.community.member.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
    private final MemberServiceImpl memberService;
	
	@GetMapping("/register/form")
	public String registerForm(Model model) {
		model.addAttribute("registerDTO", new RegisterDTO());
		return "member/register";
	}
	
	@PostMapping("/register")
	public String register(@Valid RegisterDTO registerDTO, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member/register";
		}
		
		int resert = memberService.register(registerDTO);
		
		return "index";
	}
	

}
