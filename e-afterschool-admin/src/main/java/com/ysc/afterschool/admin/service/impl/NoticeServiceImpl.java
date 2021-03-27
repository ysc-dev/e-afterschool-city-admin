package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Notice;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam;
import com.ysc.afterschool.admin.domain.param.NoticeSearchParam.NoticeSearchType;
import com.ysc.afterschool.admin.repository.NoticeRepository;
import com.ysc.afterschool.admin.service.NoticeService;

/**
 * 공지사항 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;

	@Transactional(readOnly = true)
	@Override
	public Notice get(Integer id) {
		return noticeRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Notice> getList() {
		return noticeRepository.findAll();
	}

	@Override
	public boolean regist(Notice domain) {
		if (isNew(domain)) {
			return noticeRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Notice domain) {
		if (!isNew(domain)) {
			return noticeRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		noticeRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Notice> getList(NoticeSearchParam param) {
		NoticeSearchType searchType = param.getSearchType();
		if (searchType == NoticeSearchType.전체) {
			return noticeRepository.OrderByCreateDateDesc();
		} else {
			if (!param.getContent().isEmpty()) {
				if (searchType == NoticeSearchType.제목) {
					return noticeRepository.findByTitleContainingOrderByCreateDateDesc(param.getContent());
				} else if (searchType == NoticeSearchType.작성자) {
					return noticeRepository.findByUserNameContainingOrderByCreateDateDesc(param.getContent());
				} else if (searchType == NoticeSearchType.내용) {
					return noticeRepository.findByContentContainingOrderByCreateDateDesc(param.getContent());
				}
			}
		}
		
		return null;
	}

	private boolean isNew(Notice domain) {
		return !noticeRepository.existsById(domain.getId());
	}
}
