package com.ysc.afterschool.admin.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.service.UserService;

/**
 * 사용자 인증 관리
 * 
 * @author hgko
 *
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        
        User user = userService.login(username, password);
        if (user != null) {
        	if (user.isPending()) {
        		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
            	roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
      
                return new UsernamePasswordAuthenticationToken(user, password, roles);  
        	} else {
        		throw new BadCredentialsException("승인 대기 중입니다.");
        	}
        } else {
            throw new BadCredentialsException("사용자 정보가 일치하지 않습니다.");
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
