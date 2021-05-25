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

	/** 내용 */
	private String content;
	
	/** 안내장 ID */
	private int invitationId;
	
	/** 과목 ID */
	private int subjectId;
}
