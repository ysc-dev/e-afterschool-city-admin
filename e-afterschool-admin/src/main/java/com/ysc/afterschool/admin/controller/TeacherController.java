package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.afterschool.admin.domain.db.Teacher;
import com.ysc.afterschool.admin.domain.db.Teacher.Sex;
import com.ysc.afterschool.admin.domain.param.TeacherSearchParam;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.TeacherService;

import reactor.core.publisher.Mono;

/**
 * 강사 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("teacher")
public class TeacherController extends AbstractController<Teacher, TeacherSearchParam, Integer> {

	@Autowired
	private TeacherService teacherService;

	public TeacherController(CRUDService<Teacher, TeacherSearchParam, Integer> crudService) {
		super(crudService);
	}

	/**
	 * 강사 목록 화면
	 * 
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("sexList", Sex.values());
	}

	/**
	 * 강사 정보 수정
	 * 
	 * @param teacher
	 * @return
	 */
	@Override
	public Mono<ResponseEntity<?>> update(Teacher teacher) {

		Teacher result = teacherService.get(teacher.getId());
		result.setTel(teacher.getTel());
		result.setEmail(teacher.getEmail());
		result.setContent(teacher.getContent());

		if (teacherService.update(result)) {
			return Mono.just(new ResponseEntity<>(HttpStatus.OK));
		}

		return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
