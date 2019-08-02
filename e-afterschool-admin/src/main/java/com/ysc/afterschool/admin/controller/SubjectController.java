package com.ysc.afterschool.admin.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.afterschool.admin.domain.db.Student.TargetType;
import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.domain.db.Subject.GradeType;
import com.ysc.afterschool.admin.domain.param.SubjectSearchParam;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.InvitationService;
import com.ysc.afterschool.admin.service.SubjectGroupService;
import com.ysc.afterschool.admin.service.SubjectService;
import com.ysc.afterschool.admin.service.TeacherService;

/**
 * 수강 과목 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("subject")
public class SubjectController extends AbstractController<Subject, SubjectSearchParam, Integer> {

	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SubjectGroupService subjectGroupService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private SubjectService subjectService;
	
	public SubjectController(CRUDService<Subject, SubjectSearchParam, Integer> crudService) {
		super(crudService);
	}

	/**
	 * 과목 조회 화면
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("invitations", invitationService.getList());
		model.addAttribute("subjectGroups", subjectGroupService.getList());
		model.addAttribute("teachers", teacherService.getList());
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("gradeTypes", GradeType.values());
	}
	
	@GetMapping("notice/{id}")
	public String notice(Model model, @PathVariable int id) {
		return "subject/notice";
	}
	
	/**
	 * 과목 조회
	 * @param param
	 * @return
	 */
	@Override
	public ResponseEntity<?> search(@RequestBody SubjectSearchParam param) {
		return new ResponseEntity<>(subjectService.getList(param).stream().map(data -> {
			if (data.getTargetType() == TargetType.전체) {
				data.setTarget("전체 (" + data.getFixedNumber() + ")");
			} else {
				data.setTarget(data.getTargetType().getName() + ",<br>" + data.getGradeType().getName() + "(" + data.getFixedNumber() + ")");
			}
			return data;
		}).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	/**
	 * 과목 등록 화면
	 * @param model
	 */
	@GetMapping("regist")
	public void regist(Model model) {
		model.addAttribute("invitations", invitationService.getList());
		model.addAttribute("subjectGroups", subjectGroupService.getList());
		model.addAttribute("teachers", teacherService.getList());
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("gradeTypes", GradeType.values());
	}
	
	/**
	 * 과목 그룹 수정
	 * @param subject
	 * @return
	 */
	@Override
	public ResponseEntity<?> update(Subject subject) {
		Subject result = subjectService.get(subject.getId());
		result.setInvitation(subject.getInvitation());
		result.setSubjectGroup(subject.getSubjectGroup());
		result.setTeacher(subject.getTeacher());
		result.setTargetType(subject.getTargetType());
		result.setGradeType(subject.getGradeType());
		result.setFixedNumber(subject.getFixedNumber());
		result.setPeriod(subject.getPeriod());
		result.setTime(subject.getTime());
		result.setWeek(subject.getWeek());
		result.setCost(subject.getCost());
		result.setLocation(subject.getLocation());
		result.setDescription(subject.getDescription());
		
		if (subjectService.update(result)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
