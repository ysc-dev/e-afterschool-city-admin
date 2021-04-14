package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Teacher;
import com.ysc.afterschool.admin.domain.param.TeacherSearchParam;
import com.ysc.afterschool.admin.repository.TeacherRepository;
import com.ysc.afterschool.admin.service.TeacherService;

/**
 * 강사 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher get(Integer id) {
		return teacherRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Teacher> getList() {
		return teacherRepository.findAll();
	}

	@Override
	public boolean regist(Teacher domain) {
		if (isNew(domain)) {
			return teacherRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Teacher domain) {
		if (!isNew(domain)) {
			return teacherRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		teacherRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Teacher> getList(TeacherSearchParam param) {
		if (!param.getName().isEmpty()) {
			return teacherRepository.findByNameContaining(param.getName());
		}
		
		return getList();
	}

	private boolean isNew(Teacher domain) {
		return !teacherRepository.existsById(domain.getId());
	}

	@Override
	public boolean deleteByUserId(int userId) {
		return teacherRepository.deleteByUserId(userId) > 0;
	}
}
