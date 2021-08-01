package com.ysc.afterschool.admin.repository;

import java.util.List;

import com.ysc.afterschool.admin.domain.db.Survey;
import com.ysc.afterschool.admin.domain.db.Survey.SurveyType;

public interface SurveyRepository extends DefaultRepository<Survey, Long> {

	List<Survey> findBySubjectIdAndSurveyType(int subjectId, SurveyType surveyType);

	List<Survey> findBySurveyType(SurveyType surveyType);

	List<Survey> findByCityIdAndSurveyType(int cityId, SurveyType surveyType);

	void deleteBySubjectId(Integer subjectId);

}
