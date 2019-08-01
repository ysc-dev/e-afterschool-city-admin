package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.SubjectGroup;
import com.ysc.afterschool.admin.domain.param.SearchParam;
import com.ysc.afterschool.admin.repository.SubjectGroupRepository;
import com.ysc.afterschool.admin.service.SubjectGroupService;

@Transactional
@Service
public class SubjectGroupServiceImpl implements SubjectGroupService {
	
	@Autowired
	private SubjectGroupRepository subjectGroupRepository;

	@Override
	public SubjectGroup get(Integer id) {
		return subjectGroupRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Cacheable("subjectGroup.list")
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
		subjectGroupRepository.deleteById(id);
		return true;
	}

	@Override
	public List<SubjectGroup> getList(SearchParam param) {
		return getList();
	}

	private boolean isNew(SubjectGroup domain) {
		return !subjectGroupRepository.existsById(domain.getId());
	}
}
