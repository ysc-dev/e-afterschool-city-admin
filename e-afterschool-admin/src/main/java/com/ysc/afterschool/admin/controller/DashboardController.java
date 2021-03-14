package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ysc.afterschool.admin.service.ApplyService;
import com.ysc.afterschool.admin.service.InvitationService;
import com.ysc.afterschool.admin.service.NoticeService;
import com.ysc.afterschool.admin.service.SchoolService;
import com.ysc.afterschool.admin.service.StudentService;
import com.ysc.afterschool.admin.service.SubjectGroupService;
import com.ysc.afterschool.admin.service.SubjectService;
import com.ysc.afterschool.admin.service.SurveyService;
import com.ysc.afterschool.admin.service.TeacherService;

/**
 * 대시보드 화면 컨트롤러
 * 
 * @author hgko
 *
 */
@Controller
public class DashboardController {
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private SubjectGroupService subjectGroupService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private SurveyService surveyService;

	/**
	 * 대시보드 화면
	 * @param model
	 */
	@GetMapping("home")
	public void home(Model model) {
		model.addAttribute("invitations", invitationService.getList().size());
		model.addAttribute("schools", schoolService.getList().size());
		model.addAttribute("students", studentService.getList().size());
		model.addAttribute("teachers", teacherService.getList().size());
		model.addAttribute("subjectGroups", subjectGroupService.getList().size());
		model.addAttribute("subjects", subjectService.getList().size());
		model.addAttribute("applies", applyService.getList().size());
		model.addAttribute("notices", noticeService.getList().size());
		model.addAttribute("surveyList", surveyService.getList().size());
	}
}
