package com.ysc.afterschool.admin.domain.db;

import com.ysc.afterschool.admin.domain.Domain;

import lombok.Data;

/**
 * SMS 정보
 * 
 * @author hgko
 *
 */
@Data
public class SmsInfo implements Domain {

	private String content;
	
	private int subjectId;
}
