package com.ysc.afterschool.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ysc.afterschool.admin.domain.CommonFile;
import com.ysc.afterschool.admin.domain.db.ClassContents;
import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.domain.db.SubjectUploadedFile;
import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.param.ClassContentsSearchParam;
import com.ysc.afterschool.admin.repository.SubjectUploadedFileRepository;
import com.ysc.afterschool.admin.service.ClassContentsService;
import com.ysc.afterschool.admin.service.InvitationService;
import com.ysc.afterschool.admin.service.SubjectService;
import com.ysc.afterschool.admin.service.impl.FileUploadService;

/**
 * 횟수별 수업 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("classContent")
public class ClassContentsController {
	
	@Autowired
	private ClassContentsService classContentsService;
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private SubjectUploadedFileRepository subjectUploadedFileRepository;

	/**
	 * 수업 목록 화면
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
	 * 조회
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public ResponseEntity<?> search(@RequestBody ClassContentsSearchParam param) {
		return new ResponseEntity<>(classContentsService.getList(param), HttpStatus.OK);
	}
	
	/**
	 * 수업 내용 등록 화면
	 */
	@GetMapping("regist")
	public void regist(Model model, int subjectId) {
		model.addAttribute("subject", subjectService.get(subjectId));
	}
	
	/**
	 * 수업 내용 등록 기능
	 * @param classContents
	 * @return
	 */
	@PostMapping("regist")
	@ResponseBody 
	public ResponseEntity<?> regist(ClassContents classContents, Authentication authentication, HttpServletRequest request) {
		List<SubjectUploadedFile> uploadedFiles = new ArrayList<>();
		
		for (MultipartFile file : classContents.getFiles()) {
			String fileName = file.getOriginalFilename();
			if (!fileName.isEmpty()) {
				CommonFile commonFile = fileUploadService.restore(file, CommonFile.CLASS_PATH);
				SubjectUploadedFile uploadedFile = new SubjectUploadedFile(commonFile);
				uploadedFile.setClassContents(classContents);
				
				uploadedFiles.add(uploadedFile);
			}
		}
		
		User user = (User) authentication.getPrincipal();
		classContents.setUserId(user.getUserId());
		classContents.setUserName(user.getName());
		classContents.setUploadedFiles(uploadedFiles);
		
		if (classContentsService.regist(classContents)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("file/get")
	public ResponseEntity<?> getFile(int id) {
		return new ResponseEntity<>(classContentsService.get(id), HttpStatus.OK);
	}

	/**
	 * 정보 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<?> delete(Integer id) {
		List<SubjectUploadedFile> files = subjectUploadedFileRepository.findByClassContentsId(id);
		
		if (classContentsService.delete(id)) {
			for (SubjectUploadedFile file : files) {
				fileUploadService.fileDelete(CommonFile.CLASS_PATH, file.getFileName());
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
