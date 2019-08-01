package com.ysc.afterschool.admin.domain.param;

import com.ysc.afterschool.admin.domain.DomainParam;

import lombok.Data;

/**
 * 안내장 관리 검색 조건 클래스
 * 
 * @author hgko
 *
 */
@Data
public class InvitationSearchParam implements DomainParam {

	private int cityId;
}
