package com.ysc.afterschool.admin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.SmsInfo;
import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.service.InvitationService;
import com.ysc.afterschool.admin.service.SubjectService;
import com.ysc.afterschool.admin.service.common.SmsService;

/**
 * SMS 관리
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("sms")
public class SmsController {

	@Autowired
	private InvitationService invitationService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SmsService smsService;

	/**
	 * 선택된 과목 발송 화면
	 * 
	 * @param model
	 */
	@GetMapping("subject")
	public void subject(Model model) {
		List<Invitation> invitations = invitationService.getList();
		model.addAttribute("invitations", invitations);
		if (invitations.size() > 0) {
			List<Subject> subjects = subjectService.getList(invitations.get(0).getId());
			if (subjects.size() > 0)
				model.addAttribute("subjects", subjectService.getList(invitations.get(0).getId()));
			else
				model.addAttribute("subjects", Arrays.asList(new Subject(0, "등록된 과목 없음")));
		}
	}

	/**
	 * 선택된 안내장 발송 화면
	 * 
	 * @param model
	 */
	@GetMapping("invitation")
	public void invitation(Model model) {
		List<Invitation> invitations = invitationService.getList();
		model.addAttribute("invitations", invitations);
	}

	/**
	 * SMS 전송(과목별)
	 * 
	 * @param smsInfo
	 * @return
	 */
	@PostMapping("send/subject")
	@ResponseBody
	public ResponseEntity<?> sendSubject(SmsInfo smsInfo) {
		return smsService.sendBySubject(smsInfo.getSubjectId(), smsInfo.getContent());
	}

	/**
	 * SMS 전송(과목별)
	 * 
	 * @param smsInfo
	 * @return
	 */
	@PostMapping("send/invitation")
	@ResponseBody
	public ResponseEntity<?> sendInvitation(SmsInfo smsInfo) {
		return smsService.sendByInvitation(smsInfo.getInvitationId(), smsInfo.getContent());
	}
}
