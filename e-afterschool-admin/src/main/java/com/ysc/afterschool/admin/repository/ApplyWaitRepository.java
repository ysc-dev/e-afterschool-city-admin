package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.ApplyWait;

public interface ApplyWaitRepository extends DefaultRepository<ApplyWait, Integer> {

	List<ApplyWait> findBySubjectId(int subjectId);

	List<ApplyWait> findByInvitationId(int invitationId);

	void deleteByStudentId(int studentId);

	void deleteBySubjectId(int subjectId);

}
