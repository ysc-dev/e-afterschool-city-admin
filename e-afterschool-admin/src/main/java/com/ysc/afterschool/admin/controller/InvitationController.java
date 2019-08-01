package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.Invitation.InvitationType;
import com.ysc.afterschool.admin.domain.param.InvitationSearchParam;
import com.ysc.afterschool.admin.repository.CityRepository;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.InvitationService;

/**
 * 안내장 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("invitation")
public class InvitationController extends AbstractController<Invitation, InvitationSearchParam, Integer> {
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private CityRepository cityRepository;

	public InvitationController(CRUDService<Invitation, InvitationSearchParam, Integer> crudService) {
		super(crudService);
	}

	/**
	 * 안내장 목록 화면
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("cities", cityRepository.findAll());
		model.addAttribute("invitationTypes", InvitationType.values());
	}
	
	/**
	 * 안내장 등록 화면
	 */
	@GetMapping("regist")
	public void regist(Model model) {
		model.addAttribute("cities", cityRepository.findAll());
	}
	
	/**
	 * 안내장 등록 기능
	 * @param invitation
	 * @return
	 */
	@PostMapping("regist")
	public ResponseEntity<?> regist(Invitation invitation) {
		invitation.setType(InvitationType.수강신청);
		
		if (invitationService.regist(invitation)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 안내장 수정
	 * @param subject
	 * @return
	 */
	@Override
	public ResponseEntity<?> update(Invitation invitation) {
		Invitation result = invitationService.get(invitation.getId());
		result.setName(invitation.getName());
		result.setDeadlineDate(invitation.getDeadlineDate());
		result.setDescription(invitation.getDescription());
		
		if (invitationService.update(result)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
