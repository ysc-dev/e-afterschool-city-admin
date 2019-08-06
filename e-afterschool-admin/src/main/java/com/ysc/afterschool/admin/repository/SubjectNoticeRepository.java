package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.SubjectNotice;

public interface SubjectNoticeRepository extends DefaultRepository<SubjectNotice, Integer> {

	List<SubjectNotice> findBySubjectId(int subjectId);

	List<SubjectNotice> findBySubjectIdAndTitleContaining(int subjectId, String content);

	List<SubjectNotice> findBySubjectIdAndUserNameContaining(int subjectId, String content);

	List<SubjectNotice> findBySubjectIdAndContentContaining(int subjectId, String content);

	void deleteBySubjectId(int subjectId);

}
