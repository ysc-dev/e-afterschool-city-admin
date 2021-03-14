package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.domain.param.SubjectSearchParam;
import com.ysc.afterschool.admin.repository.ApplyCancelRepository;
import com.ysc.afterschool.admin.repository.ApplyRepository;
import com.ysc.afterschool.admin.repository.ApplyWaitRepository;
import com.ysc.afterschool.admin.repository.ClassContentsRepository;
import com.ysc.afterschool.admin.repository.SubjectNoticeRepository;
import com.ysc.afterschool.admin.repository.SubjectRepository;
import com.ysc.afterschool.admin.service.SubjectService;

@Transactional
@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SubjectNoticeRepository subjectNoticeRepository;
	
	@Autowired
	private ClassContentsRepository classContentsRepository;
	
	@Autowired
	private ApplyRepository applyRepository;
	
	@Autowired
	private ApplyWaitRepository applyWaitRepository;
	
	@Autowired
	private ApplyCancelRepository applyCancelRepository;

	@Override
	public Subject get(Integer id) {
		return subjectRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
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
		subjectNoticeRepository.deleteBySubjectId(id);
		classContentsRepository.deleteBySubjectId(id);
		applyRepository.deleteBySubjectId(id);
		applyWaitRepository.deleteBySubjectId(id);
		applyCancelRepository.deleteBySubjectId(id);
		
		subjectRepository.deleteById(id);
		return true;
	}

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	@Override
	public List<Subject> getList(int invitationId) {
		return subjectRepository.findByInvitationId(invitationId);
	}
}
