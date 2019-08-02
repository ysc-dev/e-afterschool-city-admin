package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Subject;

public interface SubjectRepository extends DefaultRepository<Subject, Integer> {

	List<Subject> findBySubjectGroupIdAndInvitationId(int subjectGroupId, int invitationId);
	
	List<Subject> findBySubjectGroupId(int groupId);

	List<Subject> findByInvitationId(int invitationId);

	List<Subject> findBySubjectGroupIdAndInvitationIdAndNameContaining(int subjectGroupId, int invitationId,
			String name);

	List<Subject> findBySubjectGroupIdAndNameContaining(int invitationId, String name);

	List<Subject> findByInvitationIdAndNameContaining(int invitationId, String name);

	List<Subject> findByNameContaining(String name);

}
