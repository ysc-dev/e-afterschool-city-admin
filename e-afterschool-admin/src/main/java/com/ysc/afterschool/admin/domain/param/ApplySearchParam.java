package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;

import lombok.Data;

/**
 * 수강 신청 목록 검색 조건
 * 
 * @author hgko
 *
 */
@Data
public class ApplySearchParam implements DomainParam {
	
	private int invitationId;
	
	private String subjectId;

	private String school;
	
	private String grade;
}
