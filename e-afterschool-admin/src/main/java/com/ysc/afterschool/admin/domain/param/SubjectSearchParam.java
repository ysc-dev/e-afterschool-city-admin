package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;

import lombok.Data;

/**
 * 과목 검색 조건
 * 
 * @author hgko
 *
 */
@Data
public class SubjectSearchParam implements DomainParam {
	
	private int invitationId;

	private int subjectGroupId;
	
	private String name;
}
