package com.ysc.afterschool.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ysc.afterschool.admin.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 강사 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_teacher")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Teacher extends AbstractDomain {
	
	/** 대표강사 */
	@Column(nullable = false, length = 255)
	private String name;
	
	/** 강사들 */
	@Column(length = 255)
	private String content;
	
	/** 연락처 */
	@Column(length = 20)
	private String tel;
	
	/** 이메일 */
	@Column(length = 45)
	private String email;
	
	/** 성별 */
	@Enumerated(EnumType.ORDINAL)
	private Sex sex;
	
	/** 과목 ID */
	private int subjectId;
	
	public enum Sex {
		남성, 여성;
	}
	
	public Teacher(User user) {
		this.name = user.getName();
		this.tel = user.getTel();
	}

}
