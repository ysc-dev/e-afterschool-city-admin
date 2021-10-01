package com.ysc.afterschool.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.afterschool.admin.domain.db.Apply;
import com.ysc.afterschool.admin.domain.db.Student;
import com.ysc.afterschool.admin.domain.db.Student.TargetType;
import com.ysc.afterschool.admin.domain.param.StudentSearchParam;
import com.ysc.afterschool.admin.service.ApplyService;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.CityService;
import com.ysc.afterschool.admin.service.SchoolService;
import com.ysc.afterschool.admin.service.StudentService;

import reactor.core.publisher.Mono;

/**
 * 학생 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("student")
public class StudentController extends AbstractController<Student, StudentSearchParam, Integer> {

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ApplyService applyService;

	@Autowired
	private CityService cityService;

	public StudentController(CRUDService<Student, StudentSearchParam, Integer> crudService) {
		super(crudService);
	}

	/**
	 * 학생 조회 화면
	 * 
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("cities", cityService.getList());
		model.addAttribute("schools", schoolService.getList());
	}

	/**
	 * 학생 정보 수정
	 * 
	 * @param student
	 * @return
	 */
	@Override
	public Mono<ResponseEntity<?>> update(Student student) {
		Student temp = studentService.get(student.getId());
		temp.setSchool(student.getSchool());
		temp.setGrade(student.getGrade());
		temp.setClassType(student.getClassType());
		temp.setNumber(student.getNumber());
		temp.setTel(student.getTel());

		String school = student.getSchool();
		temp.setTargetType(school.contains("초등학교") ? TargetType.초등 : TargetType.중등);
		school = school.endsWith("초등학교") ? school.substring(0, school.length() - 4)
				: school.substring(0, school.length() - 3);
		temp.setSchoolInfo(school);

		if (student.getSchool().contains("초등")) {
			temp.setTargetType(TargetType.초등);
		} else {
			temp.setTargetType(TargetType.중등);
		}

		if (studentService.update(temp)) {
			return Mono.just(new ResponseEntity<>(HttpStatus.OK));
		}

		return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	/**
	 * 학생 정보 삭제
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ResponseEntity<?> delete(Integer id) {
		List<Apply> applies = applyService.getList(id);
		if (applies.size() > 0) {
			return new ResponseEntity<>("수강신청 되어있는 학생입니다.<br>삭제하려면 먼저 수강신청 취소를 하세요.", HttpStatus.BAD_REQUEST);
		} else {
			if (studentService.delete(id)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 수강 이력 화면
	 * 
	 * @param model
	 */
	@GetMapping("apply")
	public void apply(Model model) {
	}

	/**
	 * 수강 이력 조회
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("apply/search")
	@ResponseBody
	public List<Apply> searchApply(@RequestBody StudentSearchParam param) {
		return applyService.getListFromStudent(param);
	}
}
