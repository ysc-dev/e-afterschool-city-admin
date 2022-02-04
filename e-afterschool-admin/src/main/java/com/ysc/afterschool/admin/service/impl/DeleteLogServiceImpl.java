package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.DeleteLog;
import com.ysc.afterschool.admin.domain.param.SearchParam;
import com.ysc.afterschool.admin.repository.DeleteLogRepository;
import com.ysc.afterschool.admin.service.DeleteLogService;

/**
 * 댓글 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class DeleteLogServiceImpl implements DeleteLogService {

	@Autowired
	private DeleteLogRepository deleteLogRepository;

	@Transactional(readOnly = true)
	@Override
	public DeleteLog get(Integer id) {
		return deleteLogRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<DeleteLog> getList() {
		return deleteLogRepository.findAll();
	}

	@Override
	public boolean regist(DeleteLog domain) {
		if (isNew(domain)) {
			return deleteLogRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(DeleteLog domain) {
		if (!isNew(domain)) {
			return deleteLogRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		deleteLogRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<DeleteLog> getList(SearchParam param) {
		return getList();
	}

	private boolean isNew(DeleteLog domain) {
		return !deleteLogRepository.existsById(domain.getId());
	}
}
