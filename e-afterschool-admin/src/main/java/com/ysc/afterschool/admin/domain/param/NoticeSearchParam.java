package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;

import lombok.Data;

/**
 * 공지사항 검색 조건 클래스
 * 
 * @author hgko
 *
 */
@Data
public class NoticeSearchParam implements DomainParam {

	private NoticeSearchType searchType;
	
	private String content;
	
	private int subjectId;
	
	/**
	 * 공지사항 검색조건 
	 */
	public enum NoticeSearchType {
		전체, 제목, 내용, 작성자
	}
}
