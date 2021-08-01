package com.ysc.afterschool.admin.service;

import com.ysc.afterschool.admin.domain.db.Survey;
import com.ysc.afterschool.admin.domain.param.SurveySearchParam;

public interface SurveyService extends CRUDService<Survey, SurveySearchParam, Long> {

	boolean deleteBySubjectId(Integer subjectId);

}
