package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.afterschool.admin.domain.db.School;
import com.ysc.afterschool.admin.domain.db.School.SchoolType;
import com.ysc.afterschool.admin.domain.param.SchoolSearchParam;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.SchoolService;

/**
 * 학교 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("school")
public class SchoolController extends AbstractController<School, SchoolSearchParam, Integer> {
	
	@Autowired
	private SchoolService schoolService;

	public SchoolController(CRUDService<School, SchoolSearchParam, Integer> crudService) {
		super(crudService);
	}
	
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("schoolTypes", SchoolType.values());
	}
	
	@PostMapping("regist")
	public ResponseEntity<?> regist(School school) {
		if (schoolService.regist(school)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
