package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.ClassContents;
import com.ysc.afterschool.admin.domain.db.DeleteLog;
import com.ysc.afterschool.admin.domain.db.DeleteLog.DeleteType;
import com.ysc.afterschool.admin.domain.param.ClassContentsSearchParam;
import com.ysc.afterschool.admin.repository.ClassContentsRepository;
import com.ysc.afterschool.admin.service.ClassContentsService;
import com.ysc.afterschool.admin.service.DeleteLogService;

/**
 * 횟부별 수업 관리
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class ClassContentsServiceImpl implements ClassContentsService {

	@Autowired
	private ClassContentsRepository classContentsRepository;
	
	@Autowired
	private DeleteLogService deleteLogService;

	@Transactional(readOnly = true)
	@Override
	public ClassContents get(Integer id) {
		return classContentsRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<ClassContents> getList() {
		return classContentsRepository.findAll();
	}

	@Override
	public boolean regist(ClassContents domain) {
		if (isNew(domain)) {
			return classContentsRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(ClassContents domain) {
		if (!isNew(domain)) {
			return classContentsRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		classContentsRepository.deleteById(id);
		deleteLogService.regist(new DeleteLog(id, DeleteType.ClassContents));
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ClassContents> getList(ClassContentsSearchParam param) {
		if (param.getSubjectId() != 0) {
			return classContentsRepository.findBySubjectId(param.getSubjectId());
		}
		return null;
	}

	private boolean isNew(ClassContents domain) {
		return !classContentsRepository.existsById(domain.getId());
	}
}
