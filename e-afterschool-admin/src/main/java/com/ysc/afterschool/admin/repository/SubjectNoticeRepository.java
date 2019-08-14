package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.SubjectNotice;

public interface SubjectNoticeRepository extends DefaultRepository<SubjectNotice, Integer> {
	
	List<SubjectNotice> findAllByOrderByImportantDescCreateDateDesc();

	List<SubjectNotice> findBySubjectIdOrderByImportantDescCreateDateDesc(int subjectId);

	List<SubjectNotice> findBySubjectIdAndTitleContainingOrderByImportantDescCreateDateDesc(int subjectId, String content);

	List<SubjectNotice> findBySubjectIdAndUserNameContainingOrderByImportantDescCreateDateDesc(int subjectId, String content);

	List<SubjectNotice> findBySubjectIdAndContentContainingOrderByImportantDescCreateDateDesc(int subjectId, String content);

	void deleteBySubjectId(int subjectId);

}
