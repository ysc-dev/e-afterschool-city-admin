package com.ysc.afterschool.admin.provider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.ysc.afterschool.admin.domain.db.User;

public class SaveIdLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final String REQUEST_PARAM_NAME = "remember-me";
	private final String COOKIE_NAME = "saved_username";
	private final int DEFAULT_MAX_AGE = 60 * 60 * 24 * 7;

	private int maxAge = DEFAULT_MAX_AGE;

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String remember = request.getParameter(REQUEST_PARAM_NAME);
		if (remember != null) {
			User user = (User) authentication.getPrincipal();
			Cookie cookie = new Cookie(COOKIE_NAME, user.getUserId());
			cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie(COOKIE_NAME, "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
