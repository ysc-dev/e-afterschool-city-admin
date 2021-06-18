package com.ysc.afterschool.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ysc.afterschool.admin.domain.CommonFile;
import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.Invitation.InvitationType;
import com.ysc.afterschool.admin.domain.db.InvitationFile;
import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.domain.param.InvitationSearchParam;
import com.ysc.afterschool.admin.repository.CityRepository;
import com.ysc.afterschool.admin.repository.InvitationFileRepository;
import com.ysc.afterschool.admin.service.InvitationService;
import com.ysc.afterschool.admin.service.SubjectService;
import com.ysc.afterschool.admin.service.impl.FileUploadService;

/**
 * 안내장 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("invitation")
public class InvitationController {
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private InvitationFileRepository invitationFileRepository;
	
	/**
	 * 정보 불러오기
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("get")
	@ResponseBody
	public Invitation get(int id) {
		return invitationService.get(id);
	}

	/**
	 * 안내장 목록 화면
	 * 
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("cities", cityRepository.findAll());
		model.addAttribute("invitationTypes", InvitationType.values());
	}
	
	/**
	 * 안내장 조회
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public ResponseEntity<?> search(@RequestBody InvitationSearchParam param) {
		List<Invitation> list = invitationService.getList(param).stream().map(data -> {
			data.setTypeContent(data.getType().getContent());
			return data;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 안내장 등록 화면
	 * 
	 * @param model
	 */
	@GetMapping("regist")
	public void regist(Model model) {
		model.addAttribute("cities", cityRepository.findAll());
	}
	
	/**
	 * 안내장 등록 기능
	 * 
	 * @param invitation
	 * @return
	 */
	@PostMapping("regist/file")
	@ResponseBody 
	public ResponseEntity<?> regist(Invitation invitation) {
		
		invitation.setType(InvitationType.대기);
		
		List<InvitationFile> uploadedFiles = new ArrayList<>();
		for (MultipartFile file : invitation.getImages()) {
			String fileName = file.getOriginalFilename();
			if (!fileName.isEmpty()) {
				CommonFile commonFile = fileUploadService.restore(file, CommonFile.CLASS_PATH);
				InvitationFile uploadedFile = new InvitationFile(commonFile);
				uploadedFile.setInvitation(invitation);
				
				uploadedFiles.add(uploadedFile);
			}
		}
		
		invitation.setUploadedFiles(uploadedFiles);
		
		if (invitationService.regist(invitation)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 안내장 수정
	 * 
	 * @param invitation
	 * @return
	 */
	@PutMapping("update/file")
	@ResponseBody 
	public ResponseEntity<?> update(Invitation invitation) {
		Invitation result = invitationService.get(invitation.getId());
		result.setName(invitation.getName());
		result.setCity(invitation.getCity());
		result.setDeadlineDate(invitation.getDeadlineDate());
		result.setDescription(invitation.getDescription());
		result.setType(invitation.getType());
		
		List<InvitationFile> uploadedFiles = new ArrayList<>();
		for (MultipartFile file : invitation.getImages()) {
			String fileName = file.getOriginalFilename();
			if (!fileName.isEmpty()) {
				CommonFile commonFile = fileUploadService.restore(file, CommonFile.INVITATION_PATH);
				InvitationFile uploadedFile = new InvitationFile(commonFile);
				uploadedFile.setInvitation(result);
				
				uploadedFiles.add(uploadedFile);
			}
		}
		
		List<InvitationFile> invitationFiles = null;
		if (uploadedFiles.size() > 0) {
			result.setUploadedFiles(uploadedFiles);
			invitationFiles = invitationFileRepository.findByInvitationId(invitation.getId());
		}
		
		if (invitationService.update(result)) {
			if (invitationFiles != null) {
				invitationFileRepository.deleteInBatch(invitationFiles);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 안내장 삭제
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<?> delete(int id) {
		
		List<Subject> subjects = subjectService.getList(id);
		if (subjects.size() > 0) {
			return new ResponseEntity<>("먼저 안내장의 과목을 삭제해주세요.", HttpStatus.BAD_REQUEST);
		}
		
		if (invitationService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
