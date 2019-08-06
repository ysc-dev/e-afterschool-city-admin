package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;

import lombok.Data;

/**
 * 횟수별 수업내용 검색 조건
 * 
 * @author hgko
 *
 */
@Data
public class ClassContentsSearchParam implements DomainParam {

	private int invitationId;
	
	private int subjectId;
}
