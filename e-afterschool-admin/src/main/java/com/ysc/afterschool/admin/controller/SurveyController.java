package com.ysc.afterschool.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.Survey;
import com.ysc.afterschool.admin.domain.param.SurveySearchParam;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.InvitationService;
import com.ysc.afterschool.admin.service.SubjectService;

/**
 * 만족도 및 설문 조사 컨트롤러
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("survey")
public class SurveyController extends AbstractController<Survey, SurveySearchParam, Long> {
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SubjectService subjectService;

	public SurveyController(CRUDService<Survey, SurveySearchParam, Long> crudService) {
		super(crudService);
	}

	/**
	 * 만족도 및 설문 조사 조회 화면
	 * 
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		
		List<Invitation> invitations = invitationService.getList();
		model.addAttribute("invitations", invitations);
		
		if (invitations.size() > 0) {
			model.addAttribute("subjects", subjectService.getList(invitations.get(0).getId()));
		}
	}
}
