package com.ysc.afterschool.admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ysc.afterschool.admin.domain.db.Notice;
import com.ysc.afterschool.admin.domain.db.Notice.PostStatus;
import com.ysc.afterschool.admin.domain.db.UploadedFile;
import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam.NoticeSearchType;
import com.ysc.afterschool.admin.repository.CityRepository;
import com.ysc.afterschool.admin.service.NoticeService;
import com.ysc.afterschool.admin.service.UploadedFileService;

/**
 * 공지사항 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("notice")
public class NoticeController {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UploadedFileService uploadedFileService;

	/**
	 * 공지사항 조회 화면
	 * @param model
	 */
	@GetMapping("list")
	public void notice(Model model) {
		model.addAttribute("cities", cityRepository.findAll());
		model.addAttribute("searchTypes", NoticeSearchType.values());
	}
	
	/**
	 * 조회
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public ResponseEntity<?> search(@RequestBody NoticeSearchParam param) {
		return new ResponseEntity<>(noticeService.getList(param), HttpStatus.OK);
	}
	
	/**
	 * 공지사항 등록 화면
	 */
	@GetMapping("regist")
	public void regist(Model model) {
		model.addAttribute("cities", cityRepository.findAll());
	}
	
	/**
	 * 공지사항 등록
	 * @param notice
	 * @param file
	 * @param authentication
	 * @return
	 */
	@PostMapping("regist")
	@ResponseBody
	public ResponseEntity<?> regist(Notice notice, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		notice.setUserId(user.getUserId());
		notice.setUserName(user.getName());
		notice.setStatus(PostStatus.Y);
		
		List<UploadedFile> uploadedFiles = new ArrayList<>();
		
		for (MultipartFile file : notice.getImages()) {
			String fileName = file.getOriginalFilename();
			if (!fileName.isEmpty()) {
				try {
					UploadedFile uploadedFile = new UploadedFile();
					uploadedFile.setFileName(fileName);
					uploadedFile.setContent(file.getBytes());
					uploadedFile.setContentType(file.getContentType());
					uploadedFile.setNotice(notice);
					
					uploadedFiles.add(uploadedFile);
				} catch (IOException e) {
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		notice.setUploadedFiles(uploadedFiles);
		
		if (noticeService.regist(notice)) {
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
		Notice notice = noticeService.get(id);
		model.addAttribute("notice", notice);
		model.addAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss"));
		
		notice.setHit(notice.getHit() + 1);
		noticeService.update(notice);
		
		return "notice/detail";
	}
	
	/**
	 * 공지사항 수정 화면
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("update/{id}")
	public String update(@PathVariable int id, Model model) {
		model.addAttribute("notice", noticeService.get(id));
		return "notice/update";
	}
	
	/**
	 * 공지사항 정보 수정
	 * @param notice
	 * @param file
	 * @param authentication
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody
	public ResponseEntity<?> update(Notice notice) {
		Notice temp = noticeService.get(notice.getId());
		temp.setTitle(notice.getTitle());
		temp.setContent(notice.getContent());
		
		List<UploadedFile> uploadedFiles = new ArrayList<>();
		
		for (MultipartFile file : notice.getImages()) {
			String fileName = file.getOriginalFilename();
			if (!fileName.isEmpty()) {
				try {
					UploadedFile uploadedFile = new UploadedFile();
					uploadedFile.setFileName(fileName);
					uploadedFile.setContent(file.getBytes());
					uploadedFile.setContentType(file.getContentType());
					uploadedFile.setNotice(notice);
					
					uploadedFiles.add(uploadedFile);
				} catch (IOException e) {
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		if (uploadedFiles.size() > 0) {
			if (uploadedFileService.delete(temp.getUploadedFiles())) {
				temp.setUploadedFiles(uploadedFiles);
			}
		}
		
		if (noticeService.update(temp)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 정보 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<?> delete(int id) {
		if (noticeService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 공지사항 첨부파일 가져오기
	 * @param id
	 * @return
	 */
	@GetMapping("file/get")
	public ResponseEntity<?> getFile(int id) {
		return new ResponseEntity<>(noticeService.get(id), HttpStatus.OK);
	}
}
