package com.ysc.afterschool.admin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.afterschool.admin.domain.db.Apply;
import com.ysc.afterschool.admin.domain.db.ApplyCancel;
import com.ysc.afterschool.admin.domain.db.ApplyWait;
import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.domain.param.ApplySearchParam;
import com.ysc.afterschool.admin.service.ApplyCancelService;
import com.ysc.afterschool.admin.service.ApplyService;
import com.ysc.afterschool.admin.service.ApplyWaitService;
import com.ysc.afterschool.admin.service.InvitationService;
import com.ysc.afterschool.admin.service.SchoolService;
import com.ysc.afterschool.admin.service.SubjectService;

/**
 * 수강 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("apply")
public class ApplyController {
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SchoolService schoolService;

	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private ApplyWaitService applyWaitService;
	
	@Autowired
	private ApplyCancelService applyCancelService;
	
	/**
	 * 수강 신청 목록 화면
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		List<Invitation> invitations = invitationService.getList();
		model.addAttribute("invitations", invitations);
		if (invitations.size() > 0) {
			List<Subject> subjects = subjectService.getList(invitations.get(0).getId());
			if (subjects.size() > 0)
				model.addAttribute("subjects", subjectService.getList(invitations.get(0).getId()));
			else 
				model.addAttribute("subjects", Arrays.asList(new Subject(0, "수강 신청 없음")));
		}
		model.addAttribute("schools", schoolService.getList());
	}
	
	/**
	 * 안내장을 통해 과목 리스트 불러오기
	 * @param invitationId
	 * @return
	 */
	@GetMapping("subject/list")
	@ResponseBody
	public List<Subject> subjectList(int invitationId) {
		return subjectService.getList(invitationId);
	}
	
	/**
	 * 과목 조회
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public List<Apply> search(@RequestBody ApplySearchParam param) {
		return applyService.getList(param);
	}
	
	/**
	 * 수강 대기 목록 화면
	 * @param model
	 */
	@GetMapping("wait")
	public void wait(Model model) {
		List<Invitation> invitations = invitationService.getList();
		model.addAttribute("invitations", invitations);
		if (invitations.size() > 0) {
			List<Subject> subjects = subjectService.getList(invitations.get(0).getId());
			if (subjects.size() > 0)
				model.addAttribute("subjects", subjectService.getList(invitations.get(0).getId()));
			else 
				model.addAttribute("subjects", Arrays.asList(new Subject(0, "수강 대기 없음")));
		}
		model.addAttribute("schools", schoolService.getList());
	}
	
	/**
	 * 수강대기 조회
	 * @param param
	 * @return
	 */
	@PostMapping("wait/search")
	@ResponseBody 
	public List<ApplyWait> searchWait(@RequestBody ApplySearchParam param) {
		return applyWaitService.getList(param);
	}
	
	/**
	 * 수강 취소 목록 화면
	 * @param model
	 */
	@GetMapping("cancel")
	public void cancel(Model model) {
		List<Invitation> invitations = invitationService.getList();
		model.addAttribute("invitations", invitations);
		if (invitations.size() > 0) {
			List<Subject> subjects = subjectService.getList(invitations.get(0).getId());
			if (subjects.size() > 0)
				model.addAttribute("subjects", subjectService.getList(invitations.get(0).getId()));
			else 
				model.addAttribute("subjects", Arrays.asList(new Subject(0, "수강 취소 없음")));
		}
		model.addAttribute("schools", schoolService.getList());
	}
	
	/**
	 * 수강취소 조회
	 * @param param
	 * @return
	 */
	@PostMapping("cancel/search")
	@ResponseBody 
	public List<ApplyCancel> searchCancel(@RequestBody ApplySearchParam param) {
		return applyCancelService.getList(param);
	}
}
