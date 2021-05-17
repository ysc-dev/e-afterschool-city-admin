package com.ysc.afterschool.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.afterschool.admin.domain.db.City;
import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.Survey;
import com.ysc.afterschool.admin.domain.param.SurveySearchParam;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.CityService;
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
	private CityService cityService;
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SubjectService subjectService;
	
	public SurveyController(CRUDService<Survey, SurveySearchParam, Long> crudService) {
		super(crudService);
	}

	/**
	 * 만족도 및 설문 조사 조회 화면(학생용)
	 * 
	 * @param model
	 */
	@GetMapping("student")
	public void student(Model model) {
		
		List<City> cities = cityService.getList();
		model.addAttribute("cities", cities);
		
		if (cities.size() > 0) {
			List<Invitation> invitations = invitationService.getList(cities.get(0).getId());
			model.addAttribute("invitations", invitations);
			
			if (invitations.size() > 0) {
				model.addAttribute("subjects", subjectService.getList(invitations.get(0).getId()));
			}
		}
	}
	
	/**
	 * 만족도 및 설문 조사 조회 화면(학부모용)
	 * 
	 * @param model
	 */
	@GetMapping("parents")
	public void parents(Model model) {
		
		List<City> cities = cityService.getList();
		model.addAttribute("cities", cities);
		
		if (cities.size() > 0) {
			List<Invitation> invitations = invitationService.getList(cities.get(0).getId());
			model.addAttribute("invitations", invitations);
			
			if (invitations.size() > 0) {
				model.addAttribute("subjects", subjectService.getList(invitations.get(0).getId()));
			}
		}
	}
	
	/**
	 * 도시를 통해 안내장 불러오기
	 * @param cityId
	 * @return
	 */
	@GetMapping("invitation/list")
	@ResponseBody
	public List<Invitation> getSubjectList(int cityId) {
		return invitationService.getList(cityId);
	}
}
