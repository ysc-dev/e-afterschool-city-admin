package com.ysc.afterschool.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 메인 화면 컨트롤러
 * 
 * @author hgko
 *
 */
@Controller
public class MainController {

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
}
