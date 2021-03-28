package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.db.User.UserRole;
import com.ysc.afterschool.admin.service.UserService;

/**
 * 메인 화면 컨트롤러
 * 
 * @author hgko
 *
 */
@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
    public String index() throws Exception {
        return "redirect:login";
    }
	
	/**
	 * 로그인 화면
	 * 
	 * @param model
	 * @param error
	 * @param username
	 */
	@GetMapping("login")
    public void login(Model model, @RequestParam(value = "error", required = false) String error,
    		@CookieValue(value = "saved_username", defaultValue = "") String username) {
		if (error != null) {
			model.addAttribute("error", "falied");
		}
		
		if (username != null) {
			model.addAttribute("username", username);
		}
	}
	
	/**
	 * 회원가입 화면
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
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
		
		if (userService.regist(user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
