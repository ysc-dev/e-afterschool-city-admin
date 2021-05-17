package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;
import com.ysc.afterschool.admin.domain.db.Survey.SurveyType;

import lombok.Data;

/**
 * 만족도 및 설문조사 검색 조건 클래스
 * 
 * @author hgko
 *
 */
@Data
public class SurveySearchParam implements DomainParam {

	private int cityId;
	
	private int subjectId;
	
	private SurveyType surveyType;
}
