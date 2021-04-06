package com.ysc.afterschool.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.ApplyWait;
import com.ysc.afterschool.admin.domain.param.ApplySearchParam;
import com.ysc.afterschool.admin.repository.ApplyWaitRepository;
import com.ysc.afterschool.admin.service.ApplyWaitService;

/**
 * 수강 대기 관리
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class ApplyWaitServiceImpl implements ApplyWaitService {
	
	@Autowired
	private ApplyWaitRepository applyWaitRepository;

	@Transactional(readOnly = true)
	@Override
	public ApplyWait get(Integer id) {
		return applyWaitRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<ApplyWait> getList() {
		return applyWaitRepository.findAll();
	}

	@Override
	public boolean regist(ApplyWait domain) {
		if (isNew(domain)) {
			return applyWaitRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(ApplyWait domain) {
		if (!isNew(domain)) {
			return applyWaitRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		applyWaitRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ApplyWait> getList(ApplySearchParam param) {
		String subjectId = param.getSubjectId();
		String school = param.getSchool();
		String grade = param.getGrade();
		
		List<ApplyWait> applies = null;
		if (!subjectId.isEmpty()) {
			applies = applyWaitRepository.findBySubjectId(Integer.parseInt(subjectId));
		} else {
			applies = applyWaitRepository.findByInvitationId(param.getInvitationId());
		}
		
		if (!school.isEmpty() && !grade.equals("0")) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getSchool().equals(param.getSchool()) 
							&& data.getStudent().getGrade() == Integer.parseInt(param.getGrade());})
					.collect(Collectors.toList());
		} else if (!school.isEmpty() && grade.equals("0")) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getSchool().equals(param.getSchool());})
					.collect(Collectors.toList());
		} else if (school.isEmpty() && !grade.equals("0")) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getGrade() == Integer.parseInt(param.getGrade());})
					.collect(Collectors.toList());
		} else {
			return applies;
		}
	}

	private boolean isNew(ApplyWait domain) {
		return !applyWaitRepository.existsById(domain.getId());
	}

	@Override
	public List<ApplyWait> getList(int subjectId) {
		return applyWaitRepository.findBySubjectId(subjectId);
	}
}
