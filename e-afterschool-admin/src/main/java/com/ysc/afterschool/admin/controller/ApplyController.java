package com.ysc.afterschool.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 수강 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("apply")
public class ApplyController {

	/**
	 * 수강 목록 화면
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
	}
}
