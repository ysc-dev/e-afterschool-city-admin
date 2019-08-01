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
import com.ysc.afterschool.admin.domain.db.SubjectGroup;
import com.ysc.afterschool.admin.domain.param.SearchParam;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.SubjectGroupService;

/**
 * 과목 그룹 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("subject/group")
public class SubjectGroupController extends AbstractController<SubjectGroup, SearchParam, Integer> {
	
	@Autowired
	private SubjectGroupService subjectGroupService;

	public SubjectGroupController(CRUDService<SubjectGroup, SearchParam, Integer> crudService) {
		super(crudService);
	}

	@GetMapping("list")
	public void list(Model model) {
		
	}
	
	/**
	 * 과목 그룹 추가 기능
	 * @param subjectGroup
	 * @return
	 */
	@PostMapping("regist")
	public ResponseEntity<?> regist(SubjectGroup subjectGroup) {
		if (subjectGroupService.regist(subjectGroup)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
