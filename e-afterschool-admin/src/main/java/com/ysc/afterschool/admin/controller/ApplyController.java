package com.ysc.afterschool.admin.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.ysc.afterschool.admin.service.common.SmsService;

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
	
	@Autowired
	private SmsService smsService;
	
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
	public List<Subject> getSubjectList(int invitationId) {
		return subjectService.getList(invitationId);
	}
	
	/**
	 * 수강 신청 조회
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public List<Apply> searchApply(@RequestBody ApplySearchParam param) {
		return applyService.getList(param);
	}
	
	/**
	 * 정보 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<?> delete(int id) {
		Apply apply = applyService.get(id);
		Subject subject = subjectService.get(apply.getSubject().getId());
		
		if (applyService.delete(id)) {
			applyCancelService.regist(new ApplyCancel(apply));
			
			List<ApplyWait> applyWaits = applyWaitService.getList(subject.getId());
			if (applyWaits.size() == 0) {  // 대기 인원이 없을 경우
				subject.setApplyNumber(subject.getApplyNumber() - 1);
				
				if (subjectService.update(subject)) {
					return new ResponseEntity<>(HttpStatus.OK);
				}
			} else { // 대기 인원이 있을 경우
				for (ApplyWait applyWait : applyWaits) {
					List<Apply> applies = applyService.getList(applyWait.getInvitation().getId(), applyWait.getStudent().getId());
					
					if (applies.size() < 2) { // 수강대기 첫번째 학생의 수강신청 한 과목이 두개가 아닐 경우
						if (applyService.regist(new Apply(applyWait.getInvitation(), applyWait.getStudent(), subject))) {
							if (applyWaitService.delete(applyWait.getId())) {
								subject.setWaitingNumber(subject.getWaitingNumber() - 1);
								
								if (subjectService.update(subject)) {
									try {
										smsService.send(applyWait.getStudent().getTel(), applyWait.getInvitation().getId());
									} catch (IOException e) {
										e.printStackTrace();
									}
									
									return new ResponseEntity<>(HttpStatus.OK);
								}
							}
						}
					}
				}
				 
				// 대기 인원은 있는데 수강대기 학생들이 최대로(2과목) 수강신청 한 경우
				subject.setApplyNumber(subject.getApplyNumber() - 1);
				
				if (subjectService.update(subject)) {
					return new ResponseEntity<>(HttpStatus.OK);
				}
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 수강 대기 목록 화면
	 * @param model
	 */
	@GetMapping("wait")
	public void applyWait(Model model) {
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
	public void applyCancel(Model model) {
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
