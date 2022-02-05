package com.ysc.afterschool.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.ysc.afterschool.admin.domain.db.DeleteLog;
import com.ysc.afterschool.admin.domain.db.QSubject;
import com.ysc.afterschool.admin.domain.db.Subject;
import com.ysc.afterschool.admin.domain.db.DeleteLog.DeleteType;
import com.ysc.afterschool.admin.domain.param.SubjectSearchParam;
import com.ysc.afterschool.admin.repository.ApplyCancelRepository;
import com.ysc.afterschool.admin.repository.ApplyRepository;
import com.ysc.afterschool.admin.repository.ApplyWaitRepository;
import com.ysc.afterschool.admin.repository.ClassContentsRepository;
import com.ysc.afterschool.admin.repository.SubjectNoticeRepository;
import com.ysc.afterschool.admin.repository.SubjectRepository;
import com.ysc.afterschool.admin.service.DeleteLogService;
import com.ysc.afterschool.admin.service.SubjectService;
import com.ysc.afterschool.admin.service.SurveyService;

/**
 * 과목 관리 서비스
 * 
 * @author hgko
 *
 */
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

	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private DeleteLogService deleteLogService;

	@Transactional(readOnly = true)
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
		surveyService.deleteBySubjectId(id);

		subjectRepository.deleteById(id);
		deleteLogService.regist(new DeleteLog(id, DeleteType.Subject));
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Subject> getList(SubjectSearchParam param) {

		QSubject subject = QSubject.subject;

		BooleanBuilder builder = new BooleanBuilder();

		if (param.getSubjectGroupId() != 0) {
			builder.and(subject.subjectGroup.id.eq(param.getSubjectGroupId()));
		}
		if (param.getInvitationId() != 0) {
			builder.and(subject.invitation.id.eq(param.getInvitationId()));
		}

		builder.and(subject.name.contains(param.getName()));

		return (List<Subject>) subjectRepository.findAll(builder);
	}

	private boolean isNew(Subject domain) {
		return !subjectRepository.existsById(domain.getId());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Subject> getList(int invitationId) {
		return subjectRepository.findByInvitationId(invitationId);
	}

	@Override
	public boolean deleteBySubjectGroup(int subjectGroupId) {
		subjectRepository.deleteBySubjectGroupId(subjectGroupId);
		return true;
	}
}
