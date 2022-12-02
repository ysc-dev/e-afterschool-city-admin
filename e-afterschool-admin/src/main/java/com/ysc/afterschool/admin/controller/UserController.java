package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.afterschool.admin.domain.db.Teacher;
import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.db.User.UserRole;
import com.ysc.afterschool.admin.service.TeacherService;
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

	@Autowired
	private TeacherService teacherService;

	/**
	 * 사용자ID 중복 확인
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("checkDuplicateId")
	@ResponseBody
	public boolean checkDuplicateId(String userId) {
		return userService.get(userId) == null ? true : false;
	}

	/**
	 * 강사 회원가입
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("signup")
	public ResponseEntity<?> regist(User user) {
		user.setRole(UserRole.TEACHER);
		user.setPending(false);

		if (userService.regist(user)) {
			if (teacherService.regist(new Teacher(user))) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 사용자 승인 대기 중 화면
	 * 
	 * @param model
	 */
	@GetMapping("pending")
	public void pending(Model model) {
		model.addAttribute("users", userService.getList(false));
	}

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
		user.setRole(UserRole.TEACHER);

		if (userService.update(user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 사용자 승인
	 * 
	 * @param user
	 * @return
	 */
	@PutMapping("pending")
	public ResponseEntity<?> pending(int id) {
		User user = userService.get(id);
		user.setPending(true);

		if (userService.update(user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 사용자 승인 거부
	 * 
	 * @param user
	 * @return
	 */
	@DeleteMapping("pending/cancel")
	public ResponseEntity<?> pendingCancel(int id) {

		if (userService.delete(id)) {
			if (teacherService.deleteByUserId(id)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
