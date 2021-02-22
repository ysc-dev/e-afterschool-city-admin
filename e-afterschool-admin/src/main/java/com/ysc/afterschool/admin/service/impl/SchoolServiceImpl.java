package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.School;
import com.ysc.afterschool.admin.domain.db.School.SchoolType;
import com.ysc.afterschool.admin.domain.param.SchoolSearchParam;
import com.ysc.afterschool.admin.repository.SchoolRepository;
import com.ysc.afterschool.admin.service.SchoolService;

@Transactional
@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolRepository schoolRepository;

	@Override
	public School get(Integer id) {
		return schoolRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<School> getList() {
		return schoolRepository.findAll();
	}

	@Override
	public boolean regist(School domain) {
		if (isNew(domain)) {
			return schoolRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(School domain) {
		if (!isNew(domain)) {
			return schoolRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		schoolRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<School> getList(SchoolSearchParam param) {
		if (!param.getSchoolType().equals("NONE") && param.getName().isEmpty()) {
			return schoolRepository.findBySchoolType(SchoolType.valueOf(param.getSchoolType()));
		} else if (!param.getSchoolType().equals("NONE") && !param.getName().isEmpty()) {
			return schoolRepository.findBySchoolTypeAndNameContaining(SchoolType.valueOf(param.getSchoolType()), param.getName());
		} else if (param.getSchoolType().equals("NONE") && !param.getName().isEmpty()) {
			return schoolRepository.findByNameContaining(param.getName());
		}
		return getList();
	}

	private boolean isNew(School domain) {
		return !schoolRepository.existsById(domain.getId());
	}
}
