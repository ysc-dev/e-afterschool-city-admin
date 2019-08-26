package com.ysc.afterschool.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.db.User.UserRole;

/**
 * 사용자 정보 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	/**
	 * 사용자 정보 화면
	 * @param model
	 */
	@GetMapping("profile")
	public void profile(Model model, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		model.addAttribute("userRoles", UserRole.values());
	}
	
	/**
	 * 사용자 정보 수정
	 * @param user
	 * @return
	 */
	@PutMapping("update")
	public ResponseEntity<?> update(User user) {
		System.err.println(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
