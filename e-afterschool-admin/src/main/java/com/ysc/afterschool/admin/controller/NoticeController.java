package com.ysc.afterschool.admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ysc.afterschool.admin.domain.db.Notice;
import com.ysc.afterschool.admin.domain.db.Notice.PostStatus;
import com.ysc.afterschool.admin.domain.db.UploadedFile;
import com.ysc.afterschool.admin.domain.db.User;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam.NoticeSearchType;
import com.ysc.afterschool.admin.service.CRUDService;
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
public class NoticeController extends AbstractController<Notice, NoticeSearchParam, Integer>{
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UploadedFileService uploadedFileService;
	
	public NoticeController(CRUDService<Notice, NoticeSearchParam, Integer> crudService) {
		super(crudService);
	}

	/**
	 * 공지사항 조회 화면
	 * @param model
	 */
	@GetMapping("list")
	public void notice(Model model) {
		model.addAttribute("searchTypes", NoticeSearchType.values());
	}
	
	/**
	 * 공지사항 등록 화면
	 */
	@GetMapping("regist")
	public void regist() {
	}
	
	/**
	 * 공지사항 등록
	 * @param notice
	 * @param file
	 * @param authentication
	 * @return
	 */
	@PostMapping("regist")
	public ResponseEntity<?> regist(Notice notice, MultipartFile file, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		notice.setUserId(user.getUserId());
		notice.setUserName(user.getName());
		notice.setStatus(PostStatus.Y);
		
		try {
			UploadedFile uploadedFile = new UploadedFile();
			uploadedFile.setFileName(file.getOriginalFilename());
			uploadedFile.setContent(file.getBytes());
			uploadedFile.setContentType(file.getContentType());
			uploadedFileService.regist(uploadedFile);
			
			notice.setUploadedFile(uploadedFile);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
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
}
