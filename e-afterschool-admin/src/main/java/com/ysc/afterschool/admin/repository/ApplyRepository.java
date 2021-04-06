package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Apply;

public interface ApplyRepository extends DefaultRepository<Apply, Integer> {

	List<Apply> findBySubjectId(int subjectId);

	List<Apply> findByInvitationId(int invitationId);

	List<Apply> findByStudentId(int studentId);

	void deleteBySubjectId(int subjectId);

	List<Apply> findByInvitationIdAndStudentId(int infoId, int studentId);

}
