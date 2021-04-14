package com.ysc.afterschool.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ysc.afterschool.admin.provider.SaveIdLoginSuccessHandler;
import com.ysc.afterschool.admin.provider.UserAuthenticationProvider;

/**
 * Spring Security 설정
 * 
 * @author hgko
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		// 로그인 설정
		http.authorizeRequests() // 요청을 어떻게 보안을 할 것인지 설정
			.antMatchers("/home").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
			.antMatchers("/invitation/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/school/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/teacher/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/student/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/subject/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/apply/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
			.antMatchers("/classContent/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
			.antMatchers("/notice/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/sms/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/survey/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/user/profile").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
			.antMatchers("/user/pending").access("hasAnyRole('ROLE_ADMIN')")
		.and()
			// 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error")
			.usernameParameter("username")
            .passwordParameter("password")
            .successHandler(saveIdLoginSuccessHandler())
		.and()
			// 로그아웃 관련 설정
			.logout()
			.logoutSuccessUrl("/")
			// 로그아웃 성공 시 현재 보고 있는 페이지 리다이렉트
			.invalidateHttpSession(true);
		
		http.httpBasic();
		
		// session 관리
		http.sessionManagement().sessionFixation().changeSessionId()
			.maximumSessions(5).expiredUrl("/home");
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) {
		// 로그인 프로세스가 진행될 provider
        auth.authenticationProvider(authenticationProvider);
    }
	
	@Bean
	public SaveIdLoginSuccessHandler saveIdLoginSuccessHandler() {
		
		SaveIdLoginSuccessHandler handler = new SaveIdLoginSuccessHandler();
		handler.setDefaultTargetUrl("/home");
		handler.setAlwaysUseDefaultTargetUrl(true);
		
		return handler;
	}
}
