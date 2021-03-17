package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Survey;

public interface SurveyRepository extends DefaultRepository<Survey, Long> {

	List<Survey> findBySubjectId(int subjectId);

}
