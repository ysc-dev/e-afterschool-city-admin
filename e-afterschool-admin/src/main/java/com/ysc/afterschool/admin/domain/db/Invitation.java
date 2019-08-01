package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 안내장 목록 테이블 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_invitation")
@Data
@EqualsAndHashCode(callSuper = false)
public class Invitation extends AbstractDomain {

	@Column(nullable = false, length = 255)
	private String name;
	
	private String deadlineDate;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
	private InvitationType type;
	
	@Getter
	public enum InvitationType {
		수강신청("수강신청하기"),
		마감("마감되었습니다."),
		지남("지난 모집 공고 입니다.");
		
		private String name;
		
		private InvitationType(String name) {
			this.name = name;
		}
	}
}
