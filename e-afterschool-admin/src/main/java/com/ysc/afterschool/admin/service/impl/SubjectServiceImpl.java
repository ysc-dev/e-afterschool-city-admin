package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.domain.param.SubjectSearchParam;
import com.ysc.afterschool.admin.repository.SubjectRepository;
import com.ysc.afterschool.admin.service.SubjectService;

@Transactional
@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public Subject get(Integer id) {
		return subjectRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Cacheable("Subject.list")
	@Override
	public List<Subject> getList() {
		return subjectRepository.findAll();
	}

	@Override
	public boolean regist(Subject domain) {
		if (isNew(domain)) {
			return subjectRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Subject domain) {
		if (!isNew(domain)) {
			return subjectRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		subjectRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Subject> getList(SubjectSearchParam param) {
		if (param.getName().isEmpty()) {
			if (param.getSubjectGroupId() != 0 && param.getInvitationId() != 0) {
				return subjectRepository.findBySubjectGroupIdAndInvitationId(param.getSubjectGroupId(), param.getInvitationId());
			} else if (param.getSubjectGroupId() != 0 && param.getInvitationId() == 0) {
				return subjectRepository.findBySubjectGroupId(param.getInvitationId());
			} else if (param.getSubjectGroupId() == 0 && param.getInvitationId() != 0) {
				return subjectRepository.findByInvitationId(param.getInvitationId());
			} else {
				return getList();
			}
		} else {
			if (param.getSubjectGroupId() != 0 && param.getInvitationId() != 0) {
				return subjectRepository.findBySubjectGroupIdAndInvitationIdAndNameContaining(param.getSubjectGroupId(), 
						param.getInvitationId(), param.getName());
			} else if (param.getSubjectGroupId() != 0 && param.getInvitationId() == 0) {
				return subjectRepository.findBySubjectGroupIdAndNameContaining(param.getInvitationId(), param.getName());
			} else if (param.getSubjectGroupId() == 0 && param.getInvitationId() != 0) {
				return subjectRepository.findByInvitationIdAndNameContaining(param.getInvitationId(), param.getName());
			} else {
				return subjectRepository.findByNameContaining(param.getName());
			}
		}
	}

	private boolean isNew(Subject domain) {
		return !subjectRepository.existsById(domain.getId());
	}
}