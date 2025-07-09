package kr.co.community.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

/**
 * Spring Security 설정 클래스입니다.
 * 
 * HTTP 보안 설정과 비밀번호 암호화 설정을 구성합니다.
 * 
 * 현재 모든 요청에 대해 인증 없이 접근을 허용하고 있으며, CSRF 보호는 비활성화되어 있습니다.
 */
@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {
	
	/**
	 * Spring Security의 필터 체인을 설정합니다.
	 * 
	 * @param http Spring Security의 HTTP 보안 객체
	 * @return 구성된 SecurityFilterChain 객체
	 * @throws Exception 보안 설정 중 오류 발생 시
	 */
	@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.authorizeRequests()
					.anyRequest().permitAll();	//모든 요청 허용
			return http.build();
		}
	
	/**
	 * 비밀번호 암화호를 위한 PasswordEncoder 빈을 등록합니다.
	 * 
	 * @return BCryptPasswordEncoder 인스턴스
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}