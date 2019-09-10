package com.ysc.afterschool.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.afterschool.admin.domain.db.Comment;
import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.service.CommentService;
import com.ysc.afterschool.admin.service.SubjectNoticeService;

/**
 * 댓글 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private SubjectNoticeService subjectNoticeService;

	/**
	 * 한개의 댓글 정보 불러오기
	 * @param id
	 * @return
	 */
	@GetMapping(value = "get")
	@ResponseBody
	public ResponseEntity<Comment> get(int id) {
		return new ResponseEntity<>(commentService.get(id), HttpStatus.OK);
	}
	
	/**
	 * 댓글 등록
	 * @param comment
	 * @param authentication
	 * @return
	 */
	@PostMapping(value = "regist")
	@ResponseBody
	public ResponseEntity<?> regist(Comment comment, int subjectNoticeId, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		comment.setUserId(user.getId());
		comment.setUserName(user.getName());
		comment.setSubjectNotice(subjectNoticeService.get(subjectNoticeId));
		
		if (commentService.regist(comment)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 댓글 수정
	 * @param id
	 * @param content
	 * @return
	 */
	@PutMapping(value = "update")
	@ResponseBody
	public ResponseEntity<?> update(int id, String content) {
		Comment comment = commentService.get(id);
		comment.setContent(content);
		
		if (commentService.update(comment)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 댓글 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<?> delete(int id) {
		if (commentService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
