package com.ysc.afterschool.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Apply;
import com.ysc.afterschool.admin.domain.param.ApplySearchParam;
import com.ysc.afterschool.admin.repository.ApplyRepository;
import com.ysc.afterschool.admin.service.ApplyService;

@Transactional
@Service
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	private ApplyRepository applyRepository;

	@Override
	public Apply get(Integer id) {
		return applyRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Cacheable("apply.list")
	@Override
	public List<Apply> getList() {
		return applyRepository.findAll();
	}

	@Override
	public boolean regist(Apply domain) {
		if (isNew(domain)) {
			return applyRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Apply domain) {
		if (!isNew(domain)) {
			return applyRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		applyRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Apply> getList(ApplySearchParam param) {
		String subjectId = param.getSubjectId();
		String school = param.getSchool();
		String grade = param.getGrade();
		
		List<Apply> applies = null;
		if (!subjectId.isEmpty()) {
			applies = applyRepository.findBySubjectId(Integer.parseInt(subjectId));
		} else {
			applies = applyRepository.findByInvitationId(param.getInvitationId());
		}
		
		if (!school.isEmpty() && !grade.isEmpty()) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getSchool().equals(param.getSchool()) 
							&& data.getStudent().getGrade() == Integer.parseInt(param.getGrade());})
					.collect(Collectors.toList());
		} else if (!school.isEmpty() && grade.isEmpty()) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getSchool().equals(param.getSchool());})
					.collect(Collectors.toList());
		} else if (school.isEmpty() && !grade.isEmpty()) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getGrade() == Integer.parseInt(param.getGrade());})
					.collect(Collectors.toList());
		} else {
			return applies;
		}
	}

	private boolean isNew(Apply domain) {
		return !applyRepository.existsById(domain.getId());
	}
}