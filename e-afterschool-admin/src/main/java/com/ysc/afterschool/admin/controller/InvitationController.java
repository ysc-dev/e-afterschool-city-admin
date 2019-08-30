package com.ysc.afterschool.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ysc.afterschool.admin.domain.db.Invitation;
import com.ysc.afterschool.admin.domain.db.Invitation.InvitationType;
import com.ysc.afterschool.admin.domain.db.InvitationFile;
import com.ysc.afterschool.admin.domain.param.InvitationSearchParam;
import com.ysc.afterschool.admin.repository.CityRepository;
import com.ysc.afterschool.admin.service.CRUDService;
import com.ysc.afterschool.admin.service.InvitationFileService;
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
	private InvitationFileService invitationFileService;
	
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
	 * 조회
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
	@PostMapping("regist/file")
	@ResponseBody 
	public ResponseEntity<?> regist(Invitation invitation) {
		invitation.setType(InvitationType.대기);
		
		Invitation result = invitationService.registDomain(invitation);
		if (result != null) {
			for (MultipartFile file : invitation.getImages()) {
				String fileName = file.getOriginalFilename();
				if (!fileName.isEmpty()) {
					try {
						InvitationFile uploadedFile = new InvitationFile();
						uploadedFile.setFileName(fileName);
						uploadedFile.setContent(file.getBytes());
						uploadedFile.setContentType(file.getContentType());
						uploadedFile.setInvitationId(result.getId());
						
						invitationFileService.regist(uploadedFile);
					} catch (IOException e) {
						e.printStackTrace();
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
				}
			}
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 안내장 수정
	 * @param subject
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
		
		if (invitationService.update(result)) {
			if (invitationFileService.delete(invitationFileService.getList(result.getId()))) {
				List<InvitationFile> uploadedFiles = new ArrayList<>();
				for (MultipartFile file : invitation.getImages()) {
					String fileName = file.getOriginalFilename();
					if (!fileName.isEmpty()) {
						try {
							InvitationFile uploadedFile = new InvitationFile();
							uploadedFile.setFileName(fileName);
							uploadedFile.setContent(file.getBytes());
							uploadedFile.setContentType(file.getContentType());
							uploadedFile.setInvitationId(result.getId());
							
							invitationFileService.regist(uploadedFile);
							
							uploadedFiles.add(uploadedFile);
						} catch (IOException e) {
							e.printStackTrace();
							return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
						}
					}
				}
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 안내장 첨부파일 가져오기
	 * @param id
	 * @return
	 */
	@GetMapping("file/get")
	public ResponseEntity<?> getFile(int id) {
		return new ResponseEntity<>(invitationFileService.getList(id), HttpStatus.OK);
	}

}
