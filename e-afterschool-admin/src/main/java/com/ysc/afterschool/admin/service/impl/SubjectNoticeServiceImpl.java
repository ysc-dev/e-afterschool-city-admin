package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.DeleteLog;
import com.ysc.afterschool.admin.domain.db.DeleteLog.DeleteType;
import com.ysc.afterschool.admin.domain.db.SubjectNotice;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam.NoticeSearchType;
import com.ysc.afterschool.admin.repository.SubjectNoticeRepository;
import com.ysc.afterschool.admin.service.DeleteLogService;
import com.ysc.afterschool.admin.service.SubjectNoticeService;

/**
 * 과목 공지사항 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class SubjectNoticeServiceImpl implements SubjectNoticeService {

	@Autowired
	private SubjectNoticeRepository subjectNoticeRepository;
	
	@Autowired
	private DeleteLogService deleteLogService;

	@Transactional(readOnly = true)
	@Override
	public SubjectNotice get(Integer id) {
		return subjectNoticeRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubjectNotice> getList() {
		return subjectNoticeRepository.findAll();
	}

	@Override
	public boolean regist(SubjectNotice domain) {
		if (isNew(domain)) {
			return subjectNoticeRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(SubjectNotice domain) {
		if (!isNew(domain)) {
			return subjectNoticeRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		subjectNoticeRepository.deleteById(id);
		deleteLogService.regist(new DeleteLog(id, DeleteType.SubjectNotice));
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubjectNotice> getList(NoticeSearchParam param) {
		NoticeSearchType searchType = param.getSearchType();
		if (searchType == NoticeSearchType.전체) {
			return subjectNoticeRepository.findBySubjectIdOrderByImportantDescCreateDateDesc(param.getSubjectId());
		} else {
			if (!param.getContent().isEmpty()) {
				if (searchType == NoticeSearchType.제목) {
					return subjectNoticeRepository.findBySubjectIdAndTitleContainingOrderByImportantDescCreateDateDesc(
							param.getSubjectId(), param.getContent());
				} else if (searchType == NoticeSearchType.작성자) {
					return subjectNoticeRepository
							.findBySubjectIdAndUserNameContainingOrderByImportantDescCreateDateDesc(
									param.getSubjectId(), param.getContent());
				} else if (searchType == NoticeSearchType.내용) {
					return subjectNoticeRepository
							.findBySubjectIdAndContentContainingOrderByImportantDescCreateDateDesc(param.getSubjectId(),
									param.getContent());
				}
			}
		}

		return null;
	}

	private boolean isNew(SubjectNotice domain) {
		return !subjectNoticeRepository.existsById(domain.getId());
	}
}
