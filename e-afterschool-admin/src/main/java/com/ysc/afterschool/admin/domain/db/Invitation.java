package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@Column(nullable = false, length = 45)
	private String deadlineDate;
	
	@Column(length = 255)
	private String description;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
	private InvitationType type;
	
	@OneToOne
    @JoinColumn(name = "city_id")
	private City city;
	
	@Getter
	public enum InvitationType {
		수강신청("수강신청하기"),
		수강마강("마감되었습니다."),
		지난모집공고("지난 모집 공고 입니다.");
		
		public String name;
		
		private InvitationType(String name) {
			this.name = name;
		}
	}
}
