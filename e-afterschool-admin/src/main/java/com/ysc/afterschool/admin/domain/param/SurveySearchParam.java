package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;

import lombok.Data;

/**
 * 만족도 및 설문조사 검색 조건 클래스
 * 
 * @author hgko
 *
 */
@Data
public class SurveySearchParam implements DomainParam {

	private String subjectId;
}
