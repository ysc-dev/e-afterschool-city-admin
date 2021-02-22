package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;

import lombok.Data;

/**
 * 학교 검색 조건 클래스
 * 
 * @author hgko
 *
 */
@Data
public class SchoolSearchParam implements DomainParam {

	private String city;
	
	private String schoolType;
	
	private String name;
}
