package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Subject;

public interface SubjectRepository extends DefaultRepository<Subject, Integer> {

	List<Subject> findByInvitationId(int invitationId);
	
	List<Subject> findBySubjectGroupId(int subjectGroupId);

	void deleteBySubjectGroupId(int subjectGroupId);

}
