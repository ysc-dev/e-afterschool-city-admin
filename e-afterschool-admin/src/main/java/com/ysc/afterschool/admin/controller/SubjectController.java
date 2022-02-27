package com.ysc.afterschool.admin.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

import reactor.core.publisher.Mono;

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
	 * 
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("invitations", invitationService.getOrderbyList());
		model.addAttribute("subjectGroups", subjectGroupService.getList());
		model.addAttribute("teachers", teacherService.getList());
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("gradeTypes", GradeType.values());
	}

	/**
	 * 수강 과목 조회
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public Mono<ResponseEntity<?>> search(@RequestBody SubjectSearchParam param) {
		return Mono.just(new ResponseEntity<>(subjectService.getList(param).stream().map(data -> {
			if (data.getTargetType() == TargetType.전체) {
				if (data.getGradeType() == GradeType.초_3_6_중등
						|| data.getGradeType() == GradeType.초_5_6_중등
						|| data.getGradeType() == GradeType.초_1_6_중등) {
					data.setTarget(data.getGradeType().getName() + " (" + data.getFixedNumber() + ")");
				} else {
					data.setTarget("전체 (" + data.getFixedNumber() + ")");
				}
			} else {
				data.setTarget(data.getTargetType().getName() + ",<br>" + data.getGradeType().getName() + " ("
						+ data.getFixedNumber() + ")");
			}
			return data;
		}).collect(Collectors.toList()), HttpStatus.OK));
	}

	/**
	 * 과목 등록 화면
	 * 
	 * @param model
	 */
	@GetMapping("regist")
	public void regist(Model model) {
		model.addAttribute("invitations", invitationService.getOrderbyList());
		model.addAttribute("subjectGroups", subjectGroupService.getList());
		model.addAttribute("teachers", teacherService.getList());
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("gradeTypes", GradeType.values());
	}

	/**
	 * 과목 등록
	 * 
	 * @param subject
	 * @return
	 */
	@Override
	public Mono<ResponseEntity<?>> regist(Subject subject) {
//		subject.setWaitFixedNumber(subject.getFixedNumber() * 20 / 100);
		subject.setWaitFixedNumber(subject.getFixedNumber() * 2);
		if (subjectService.regist(subject)) {
			return Mono.just(new ResponseEntity<>(HttpStatus.OK));
		}

		return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	/**
	 * 과목 정보 수정
	 * 
	 * @param subject
	 * @return
	 */
	@Override
	public Mono<ResponseEntity<?>> update(Subject subject) {
		Subject result = subjectService.get(subject.getId());
		result.setName(subject.getName());
		result.setInvitation(subject.getInvitation());
		result.setSubjectGroup(subject.getSubjectGroup());
		result.setTeacher(subject.getTeacher());
		result.setTargetType(subject.getTargetType());
		result.setGradeType(subject.getGradeType());
		result.setFixedNumber(subject.getFixedNumber());
//		result.setWaitFixedNumber(subject.getFixedNumber() * 20 / 100);
		result.setWaitFixedNumber(subject.getFixedNumber());
		result.setPeriod(subject.getPeriod());
		result.setTime(subject.getTime());
		result.setWeek(subject.getWeek());
		result.setCost(subject.getCost());
		result.setLocation(subject.getLocation());
		result.setDescription(subject.getDescription());

		if (subjectService.update(result)) {
			return Mono.just(new ResponseEntity<>(HttpStatus.OK));
		}

		return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
