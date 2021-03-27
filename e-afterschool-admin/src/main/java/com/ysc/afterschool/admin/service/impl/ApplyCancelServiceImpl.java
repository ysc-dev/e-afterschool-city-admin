package com.ysc.afterschool.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.ApplyCancel;
import com.ysc.afterschool.admin.domain.param.ApplySearchParam;
import com.ysc.afterschool.admin.repository.ApplyCancelRepository;
import com.ysc.afterschool.admin.service.ApplyCancelService;

/**
 * 수강 취소 관리
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class ApplyCancelServiceImpl implements ApplyCancelService {

	@Autowired
	private ApplyCancelRepository applyCancelRepository;

	@Transactional(readOnly = true)
	@Override
	public ApplyCancel get(Integer id) {
		return applyCancelRepository.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<ApplyCancel> getList() {
		return applyCancelRepository.findAll();
	}

	@Override
	public boolean regist(ApplyCancel domain) {
		if (isNew(domain)) {
			return applyCancelRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(ApplyCancel domain) {
		if (!isNew(domain)) {
			return applyCancelRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		applyCancelRepository.deleteById(id);
		return true;
	}

	private boolean isNew(ApplyCancel domain) {
		return !applyCancelRepository.existsById(domain.getId());
	}

	@Transactional(readOnly = true)
	@Override
	public List<ApplyCancel> getList(ApplySearchParam param) {
		String subjectId = param.getSubjectId();
		String school = param.getSchool();
		String grade = param.getGrade();
		
		List<ApplyCancel> applies = null;
		if (!subjectId.isEmpty()) {
			applies = applyCancelRepository.findBySubjectId(Integer.parseInt(subjectId));
		} else {
			applies = applyCancelRepository.findByInvitationId(param.getInvitationId());
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

}
