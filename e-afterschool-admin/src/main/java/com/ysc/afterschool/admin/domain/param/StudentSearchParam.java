package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;

import lombok.Data;

/**
 * 학생 검색 조건 클래스
 * 
 * @author hgko
 *
 */
@Data
public class StudentSearchParam implements DomainParam {
	
	private String city;

	private String school;
	
	private String grade;
	
	private String name;
}
