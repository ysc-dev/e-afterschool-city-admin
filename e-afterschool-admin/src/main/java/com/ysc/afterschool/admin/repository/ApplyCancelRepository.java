package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.ApplyCancel;

public interface ApplyCancelRepository extends DefaultRepository<ApplyCancel, Integer> {

	List<ApplyCancel> findBySubjectId(int subjectId);

	List<ApplyCancel> findByInvitationId(int invitationId);

	void deleteByStudentId(int studentId);

	void deleteBySubjectId(int subjectId);

}
