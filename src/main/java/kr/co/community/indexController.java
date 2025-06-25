package kr.co.community;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class indexController {
	
	public String doIndex() {
		return "index";
	}

}
