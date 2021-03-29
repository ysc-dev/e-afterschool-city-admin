package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.db.User.UserRole;
import com.ysc.afterschool.admin.service.UserService;

/**
 * 사용자 정보 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * 사용자 정보 화면
	 * 
	 * @param model
	 * @param authentication
	 */
	@GetMapping("profile")
	public void profile(Model model, Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		model.addAttribute("userRoles", UserRole.values());
	}
	
	/**
	 * 사용자 정보 수정
	 * 
	 * @param user
	 * @return
	 */
	@PutMapping("update")
	public ResponseEntity<?> update(User user) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 사용자ID 중복 확인
	 * @param userId
	 * @return
	 */
	@PostMapping("checkDuplicateId")
	@ResponseBody
	public boolean chkDuplicateEmail(String userId) {
		
		if (userService.get(userId) == null) {
			return true;
		};
		
		return false;
	}
	
	/**
	 * 회원가입
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("signup")
	public ResponseEntity<?> regist(User user) {
		user.setRole(UserRole.GUEST);
		
		System.err.println(user);
		
		if (userService.regist(user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
