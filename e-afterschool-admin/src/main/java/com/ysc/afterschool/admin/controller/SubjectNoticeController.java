package com.ysc.afterschool.admin.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.afterschool.admin.domain.db.SubjectNotice;
import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam.NoticeSearchType;
import com.ysc.afterschool.admin.service.SubjectNoticeService;
import com.ysc.afterschool.admin.service.SubjectService;

/**
 * 과목별 공지사항 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("subject/notice")
public class SubjectNoticeController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SubjectNoticeService subjectNoticeService;
	
	/**
	 * 과목별 공지사항 조회 화면
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("list/{id}")
	public String notice(Model model, @PathVariable int id) {
		model.addAttribute("subject", subjectService.get(id));
		model.addAttribute("searchTypes", NoticeSearchType.values());
		return "subject/notice/list";
	}
	
	/**
	 * 조회
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public ResponseEntity<?> search(@RequestBody NoticeSearchParam param) {
		return new ResponseEntity<>(subjectNoticeService.getList(param), HttpStatus.OK);
	}
	
	/**
	 * 과목별 공지사항 등록 화면
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("regist/{id}")
	public String noticeRegist(Model model, @PathVariable int id) {
		model.addAttribute("subject", subjectService.get(id));
		return "subject/notice/regist";
	}
	
	/**
	 * 과목별 공지사항 등록 기능
	 * @param subjectNotice
	 * @param authentication
	 * @return
	 */
	@PostMapping("regist")
	@ResponseBody
	public ResponseEntity<?> notice(SubjectNotice subjectNotice, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		subjectNotice.setUserId(user.getId());
		subjectNotice.setUserName(user.getName());
		
		if (subjectNoticeService.regist(subjectNotice)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 공지사항 상세보기 화면
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("detail/{id}")
	public String detail(@PathVariable int id, Model model) {
		SubjectNotice notice = subjectNoticeService.get(id);
		model.addAttribute("subjectNotice", notice);
		model.addAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss"));
		model.addAttribute("comments", notice.getComments());
		
		notice.setHit(notice.getHit() + 1);
		subjectNoticeService.update(notice);
		
		return "subject/notice/detail";
	}
	
	/**
	 * 정보 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<?> delete(int id) {
		if (subjectNoticeService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
