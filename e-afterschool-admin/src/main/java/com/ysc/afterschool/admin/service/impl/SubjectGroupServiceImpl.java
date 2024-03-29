package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.DeleteLog;
import com.ysc.afterschool.admin.domain.db.DeleteLog.DeleteType;
import com.ysc.afterschool.admin.domain.db.SubjectGroup;
import com.ysc.afterschool.admin.domain.param.SearchParam;
import com.ysc.afterschool.admin.repository.SubjectGroupRepository;
import com.ysc.afterschool.admin.service.DeleteLogService;
import com.ysc.afterschool.admin.service.SubjectGroupService;
import com.ysc.afterschool.admin.service.SubjectService;

/**
 * 과목 그룹 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class SubjectGroupServiceImpl implements SubjectGroupService {

	@Autowired
	private SubjectGroupRepository subjectGroupRepository;

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private DeleteLogService deleteLogService;

	@Transactional(readOnly = true)
	@Override
	public SubjectGroup get(Integer id) {
		return subjectGroupRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubjectGroup> getList() {
		return subjectGroupRepository.findAll();
	}

	@Override
	public boolean regist(SubjectGroup domain) {
		if (isNew(domain)) {
			return subjectGroupRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(SubjectGroup domain) {
		if (!isNew(domain)) {
			return subjectGroupRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		subjectService.deleteBySubjectGroup(id);
		subjectGroupRepository.deleteById(id);
		deleteLogService.regist(new DeleteLog(id, DeleteType.SubjectGroup));
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubjectGroup> getList(SearchParam param) {
		return getList();
	}

	private boolean isNew(SubjectGroup domain) {
		return !subjectGroupRepository.existsById(domain.getId());
	}
}
